package io.github.anonymous123_code.turing_potions.block;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import io.github.anonymous123_code.turing_potions.item.PotionItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtElement;
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
				(state, world, pos, player, hand, stack) -> takeFromCauldron(
						state,
						world,
						pos,
						player,
						hand,
						stack,
						new ItemStack(TuringPotionsMod.POTION_ITEM),
						statex -> statex.get(LeveledCauldronBlock.LEVEL) > 0,
						SoundEvents.ITEM_BOTTLE_FILL
				)
		);
		POTION_CAULDRON_BEHAVIOR.put(TuringPotionsMod.POTION_ITEM, PotionCauldronBehavior::emptyPotionOnCauldron);

		CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(TuringPotionsMod.POTION_ITEM, PotionCauldronBehavior::emptyPotionOnEmptyCauldron);
	}

	static ActionResult takeFromCauldron(
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

				output.getOrCreateNbt().put("Potion", world.getBlockEntity(pos, TuringPotionsMod.POTION_CAULDRON_BLOCK_ENTITY_TYPE).get().takeTop());
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));

				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));
				if (state.get(LeveledCauldronBlock.LEVEL) > 1) {
					world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, state.get(LeveledCauldronBlock.LEVEL) - 1));
				} else {
					world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
				}
				world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}

			return ActionResult.success(world.isClient);
		}
	}

	static ActionResult emptyPotionOnEmptyCauldron(
			BlockState state,
			World world,
			BlockPos pos,
			PlayerEntity player,
			Hand hand,
			ItemStack stack
	)  {
		Item item = stack.getItem();
		if (!(stack.getItem() instanceof PotionItem)) return ActionResult.PASS;
		if (!stack.hasNbt()) return ActionResult.PASS;
		if (!stack.getNbt().contains("Potion", NbtElement.COMPOUND_TYPE)) return ActionResult.PASS;


		if (!world.isClient) {
			player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
			player.incrementStat(Stats.USE_CAULDRON);
			player.incrementStat(Stats.USED.getOrCreateStat(item));
			world.setBlockState(pos, TuringPotionsMod.POTION_CAULDRON_BLOCK.getDefaultState());

			PotionCauldronBlockEntity blockEntity = world.getBlockEntity(pos, TuringPotionsMod.POTION_CAULDRON_BLOCK_ENTITY_TYPE).get();

			blockEntity.push(stack.getNbt().getCompound("Potion"));

			world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
			world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
		}

		return ActionResult.success(world.isClient);
	}

	static ActionResult emptyPotionOnCauldron(
			BlockState state,
			World world,
			BlockPos pos,
			PlayerEntity player,
			Hand hand,
			ItemStack stack
	)  {
		Item item = stack.getItem();
		if (!(stack.getItem() instanceof PotionItem)) return ActionResult.PASS;
		if (!stack.hasNbt()) return ActionResult.PASS;
		if (!stack.getNbt().contains("Potion", NbtElement.COMPOUND_TYPE)) return ActionResult.PASS;


		if (!world.isClient) {
			PotionCauldronBlockEntity blockEntity = world.getBlockEntity(pos, TuringPotionsMod.POTION_CAULDRON_BLOCK_ENTITY_TYPE).get();
			if(blockEntity.tryPush(stack.getNbt().getCompound("Potion"))) {
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));

				world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, blockEntity.getLength()));


				world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
			}
		}

		return ActionResult.success(world.isClient);
	}
}
