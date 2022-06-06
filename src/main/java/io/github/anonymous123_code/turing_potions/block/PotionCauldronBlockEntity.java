package io.github.anonymous123_code.turing_potions.block;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

/**
 * @author anonymous123-code
 */
public class PotionCauldronBlockEntity extends BlockEntity {
	public PotionCauldronBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(TuringPotionsMod.POTION_CAULDRON_BLOCK_ENTITY_TYPE, blockPos, blockState);
	}
}
