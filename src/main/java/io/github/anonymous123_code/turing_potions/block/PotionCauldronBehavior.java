package io.github.anonymous123_code.turing_potions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;
import java.util.function.Predicate;

import static net.minecraft.block.cauldron.CauldronBehavior.createMap;

/**
 * @author anonymous123-code
 */
public class PotionCauldronBehavior {
	public final Map<Item, CauldronBehavior> POTION_CAULDRON_BEHAVIOR = createMap();
	public PotionCauldronBehavior() {
		POTION_CAULDRON_BEHAVIOR.put(
				Items.GLASS_BOTTLE,
				(state, world, pos, player, hand, stack) -> emptyCauldron(
						state,
						world,
						pos,
						player,
						hand,
						stack,
						new ItemStack(Items.WATER_BUCKET),
						statex -> statex.get(LeveledCauldronBlock.LEVEL) == 3,
						SoundEvents.ITEM_BUCKET_FILL
				)
		);
	}

	static ActionResult emptyCauldron(
			BlockState state,
			World world,
			BlockPos pos,
			PlayerEntity player,
			Hand hand,
			ItemStack stack,
			ItemStack output,
			Predicate<BlockState> predicate,
			SoundEvent soundEvent
	) {
		if (!predicate.test(state)) {
			return ActionResult.PASS;
		} else {
			if (!world.isClient) {
				Item item = stack.getItem();
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));
				world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
				world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}

			return ActionResult.success(world.isClient);
		}
	}
}
