package io.github.anonymous123_code.turing_potions.item;

import io.github.anonymous123_code.turing_potions.PotionUtility;
import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorExecutionContext;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorRegistry;
import io.github.anonymous123_code.turing_potions.mixin.PotionUtilAccessor;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A TuringPotionsMod potion. The Vanilla one just isn't made for this type of system
 *
 * @author anonymous123-code
 */
public class PotionItem extends net.minecraft.item.PotionItem {

	public PotionItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
		if (this.isInGroup(group)) {
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
				Data<?> result = PotionUtility.evaluatePotion(compound, new OperatorExecutionContext<>(world, user, null));
				TuringPotionsMod.LOGGER.info(result.toText().getString());
			}
		}

		if (playerEntity != null) {
			playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
			if (!playerEntity.getAbilities().creativeMode) {
				return new ItemStack(Items.GLASS_BOTTLE);
			}
		}

		user.emitGameEvent(GameEvent.DRINK);
		return stack;
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return true;
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		return this.getTranslationKey();
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		if (stack.getNbt() == null) {
			tooltip.add(PotionUtilAccessor.getNONE_TEXT());
			return;
		}
		NbtCompound compound = stack.getNbt().getCompound("Potion");
		if (compound != null) {
			tooltip.add(getPotionTextOf(compound));
		} else {
			tooltip.add(PotionUtilAccessor.getNONE_TEXT());
		}
	}

	public Text getPotionTextOf(NbtCompound compound) {
		try {
			MutableText result = OperatorRegistry.get(new Identifier(compound.getString("operator"))).getTranslatableText().append("(");
			for (NbtElement element :
					compound.getList("parameters", NbtElement.COMPOUND_TYPE)) {
				NbtCompound nbtCompound = (NbtCompound) element;
				result.append(getPotionTextOf(nbtCompound)).append(",");
			}
			result.append(")");
			return result;
		} catch (Exception e) {
			return Text.of("...");
		}
	}

}
