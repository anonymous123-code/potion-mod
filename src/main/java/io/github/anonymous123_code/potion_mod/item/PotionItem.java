package io.github.anonymous123_code.potion_mod.item;

import io.github.anonymous123_code.potion_mod.PotionMod;
import io.github.anonymous123_code.potion_mod.PotionUtility;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * A PotionMod potion. The Vanilla one just isn't made for this type of system
 *
 * @author anonymous123-code
 */
public class PotionItem extends net.minecraft.item.PotionItem {

	public PotionItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
		if (this.isIn(group)) {
			stacks.add(new ItemStack(this));
		}
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity) user : null;
		if (playerEntity instanceof ServerPlayerEntity) {
			Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity) playerEntity, stack);
		}

		if (!world.isClient && stack.hasNbt()) {
			NbtCompound compound = stack.getNbt().getCompound("Potion");
			if (compound != null) {
				PotionMod.LOGGER.info(PotionUtility.evaluatePotion(compound, 0).toString());
			}
		}

		if (playerEntity != null) {
			playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
			if (!playerEntity.getAbilities().creativeMode) {
				return new ItemStack(Items.GLASS_BOTTLE);
			}
		}

		world.emitGameEvent(user, GameEvent.DRINKING_FINISH, user.getCameraBlockPos());
		return stack;
	}
}
