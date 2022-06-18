package io.github.anonymous123_code.turing_potions.block;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;

/**
 * @author anonymous123-code
 */
public class PotionCauldronBlockEntity extends BlockEntity {
	public NbtList potionStack = new NbtList();
	private final int MAX_STACK_LENGTH;
	public PotionCauldronBlockEntity(BlockPos blockPos, BlockState blockState, int maxStackLength) {
		super(TuringPotionsMod.POTION_CAULDRON_BLOCK_ENTITY_TYPE, blockPos, blockState);
		MAX_STACK_LENGTH = maxStackLength;
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
		this.potionStack.add(compound);
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
			this.markDirty();
			return true;
		}
	}

	public int getLength() {
		return this.potionStack.size();
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		nbt.put("potions", potionStack);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		potionStack = nbt.getList("potions", NbtElement.COMPOUND_TYPE);
	}
}
