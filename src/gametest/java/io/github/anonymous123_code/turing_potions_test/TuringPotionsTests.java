package io.github.anonymous123_code.turing_potions_test;

import io.github.anonymous123_code.turing_potions.block.PotionCauldronBlockEntity;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.math.BlockPos;

public class TuringPotionsTests implements FabricGameTest {
	@GameTest
	public void heatingTest(TestContext context) {
		final ValueExpectationErrorMessageSupplier<Integer> supplier = new ValueExpectationErrorMessageSupplier<>("temperature");
		context.runAtTick(40, () -> {
			context.expectBlock(Blocks.CAMPFIRE, new BlockPos(2,2,2));
			// FIXME: This check fails when started from a command block instead of test server or player-input command
			context.checkBlockState(new BlockPos(2, 3, 2), (BlockState state) -> {
				PotionCauldronBlockEntity be = (PotionCauldronBlockEntity) context.getBlockEntity(new BlockPos(2,3,2));
				supplier.setExpectedValue((int) ((context.getTick() + 1) * 20));
				supplier.setFoundValue(be.getTemperature());
				return be.getTemperature() == (context.getTick() + 1) * 20;
			}, supplier);

			context.complete();
		});
	}
}
