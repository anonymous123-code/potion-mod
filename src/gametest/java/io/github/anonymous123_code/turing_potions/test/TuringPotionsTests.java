package io.github.anonymous123_code.turing_potions.test;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.block.Blocks;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.math.BlockPos;

public class TuringPotionsTests implements FabricGameTest {
	@GameTest()
	public void funnyTest(TestContext context) {
		context.addInstantFinalTask(() -> {
			context.checkBlock(new BlockPos(2,2,2), (block) -> {
				System.out.println(block);
				return block == Blocks.CAMPFIRE;
			}, "Expect block to be campfire");
		});
	}
}
