package io.github.anonymous123_code.turing_potions.block;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import io.github.anonymous123_code.turing_potions.sound.TuringPotionsSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraft.state.property.Properties;
import org.jetbrains.annotations.Nullable;

/**
 * @author anonymous123-code
 */
public class PotionCauldronBlockEntity extends BlockEntity {
	private NbtList potionStack = new NbtList();
	private int temperature;
	private final int MAX_STACK_LENGTH = LeveledCauldronBlock.MAX_FILL_LEVEL;
	public PotionCauldronBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(TuringPotionsBlocks.POTION_CAULDRON_BLOCK_ENTITY_TYPE, blockPos, blockState);
	}

	public NbtCompound takeTop() {
		if (this.getLength() == 0) return new NbtCompound();
		else {
			NbtCompound result = (NbtCompound) this.potionStack.remove(this.getLength() - 1);
			this.markDirty();
			return result;
		}
	}

	protected void push(NbtCompound compound) {
		this.potionStack.add(compound.copy());
		this.temperature *= (double) (this.potionStack.size() - 1) / this.potionStack.size();
		this.markDirty();
	}

	/**
	 * Similar to push, fails if the current length is smaller than the max length
	 * @param compound the potion's data to push
	 * @return true if success, false if try failed
	 */
	public boolean tryPush(NbtCompound compound) {
		if (this.getLength() < this.MAX_STACK_LENGTH) {
			this.push(compound);
			return true;
		}
		return false;
	}

	public boolean tryMergeBottom() {
		if (this.getLength() < 2) return false;
		else {
			NbtList params = ((NbtCompound) this.potionStack.get(0)).getList("parameters", NbtElement.COMPOUND_TYPE);
			params.add(this.potionStack.remove(1));
			temperature /= 2;
			this.markDirty();
			return true;
		}
	}

	public int getTemperature() {
		return this.temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
		this.markDirty();
	}

	public int getLength() {
		return this.potionStack.size();
	}

	public static void tick(World world, BlockPos pos, BlockState state, PotionCauldronBlockEntity be) {
        BlockPos posBelow = pos.add(0,-1, 0);
		BlockState blockStateBelow = world.getBlockState(posBelow);
		Block blockBelow = blockStateBelow.getBlock();
		int heat_rate = TuringPotionsMod.heatRateRegistryAttachment.get(blockBelow).get(); // Default is 0
		int heat_cap = TuringPotionsMod.heatCapRegistryAttachment.get(blockBelow).get(); // Default is 0
		if (heat_rate == 0 || be.getTemperature() >= heat_cap || (blockStateBelow.contains(Properties.LIT) && !blockStateBelow.get(Properties.LIT))) return;
		be.setTemperature(Math.min(Math.max(be.getTemperature() + heat_rate, 0), heat_cap));

		if (be.getTemperature() >= 48000 && be.getTemperature() <= 96000) {
			if (be.tryMergeBottom()) {
				if (state.get(LeveledCauldronBlock.LEVEL) > 1) {
					world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, state.get(LeveledCauldronBlock.LEVEL) - 1));
				} else {
					world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
				}
			}
		} else if (be.getTemperature() > 96000) {
			be.takeTop();
			if (world instanceof ServerWorld) {
				try {
					((ServerWorld) world).spawnParticles(ParticleTypes.DUST.getParametersFactory().read(ParticleTypes.DUST, new StringReader(" 0.8 0.5 1.0 1.5")), pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 3, 0.05, 0.05, 0.2, 1);
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
					throw new AssertionError();
				}
			}

			world.playSound(pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f, TuringPotionsSoundEvents.POTION_CAULDRON_EVAPORATE, SoundCategory.BLOCKS, 1, 1, true);

			if (state.get(LeveledCauldronBlock.LEVEL) > 1) {
				world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, state.get(LeveledCauldronBlock.LEVEL) - 1));
			} else {
				world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
			}
		}
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		nbt.put("Potions", potionStack.copy());
		nbt.putInt("Temperature", temperature);
	}

	@Nullable
	@Override
	public Packet<ClientPlayPacketListener> toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.of(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt() {
		return this.toNbt();
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		potionStack = nbt.getList("Potions", NbtElement.COMPOUND_TYPE).copy();
		temperature = nbt.getInt("Temperature");
	}
}
