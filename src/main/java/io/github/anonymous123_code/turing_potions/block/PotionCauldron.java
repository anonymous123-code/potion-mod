package io.github.anonymous123_code.turing_potions.block;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

/**
 * @author anonymous123-code
 */
public class PotionCauldron extends LeveledCauldronBlock implements BlockEntityProvider {
	public static final PotionCauldronBehavior POTION_CAULDRON_BEHAVIOR = new PotionCauldronBehavior();

	public PotionCauldron(Settings settings, PotionCauldronBehavior map) {
		super(settings, precipitation -> false, map.POTION_CAULDRON_BEHAVIOR);
	}

	@Override
	public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
	}

	@Override
	protected boolean canBeFilledByDripstone(Fluid fluid) {
		return false;
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
	}

	@Override
	public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
		super.onSyncedBlockEvent(state, world, pos, type, data);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.onSyncedBlockEvent(type, data);
	}

	@Nullable
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType(
			BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<A> ticker
	) {
		return expectedType == givenType ? ticker : null;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new PotionCauldronBlockEntity(pos, state);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random) {
		PotionCauldronBlockEntity blockEntity = world.getBlockEntity(pos, TuringPotionsMod.POTION_CAULDRON_BLOCK_ENTITY_TYPE).orElseThrow();
		if (blockEntity.getTemperature() >= 32000) {
			if (random.nextInt(3) == 0) doBubbleEffect(world, pos, random, blockEntity);
		}
		if (blockEntity.getTemperature() >= 48000) {
			if (random.nextBoolean()) doBubbleEffect(world, pos, random, blockEntity);
		}
		if (blockEntity.getTemperature() >= 72000) {
			doBubbleEffect(world, pos, random, blockEntity);
			if (random.nextBoolean()) world.addImportantParticle(ParticleTypes.BUBBLE_POP, true, pos.getX()+0.25+random.nextFloat()/2, pos.getY()+.5625f + (blockEntity.getLength()-1)*3/16f+random.nextFloat()/8, pos.getZ()+0.25+random.nextFloat()/2, 0,0,0);
		}
	}

	private void doBubbleEffect(World world, BlockPos pos, RandomGenerator random, PotionCauldronBlockEntity blockEntity) {
		world.addParticle(ParticleTypes.BUBBLE_POP, true, pos.getX()+0.25+random.nextFloat()/2, pos.getY()+.5625f + (blockEntity.getLength()-1)*3/16f+random.nextFloat()/8, pos.getZ()+0.25+random.nextFloat()/2, 0,0,0);
		world.playSound(pos.getX()+0.5f, pos.getY()+0.5f, pos.getZ()+0.5f, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 3 + 2 * random.nextFloat(), 0.9f + 0.2f * random.nextFloat(), true);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, TuringPotionsMod.POTION_CAULDRON_BLOCK_ENTITY_TYPE, (world2, pos, state2, be) -> PotionCauldronBlockEntity.tick(world2, pos, state2, (PotionCauldronBlockEntity) be));
	}
}
