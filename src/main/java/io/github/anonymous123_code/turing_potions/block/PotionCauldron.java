package io.github.anonymous123_code.turing_potions.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.BlockPos;
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
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<? super E> checkType(
			BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker
	) {
		return expectedType == givenType ? ticker : null;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new PotionCauldronBlockEntity(pos, state, LeveledCauldronBlock.MAX_FILL_LEVEL);
	}
}
