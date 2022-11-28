package io.github.anonymous123_code.turing_potions.item;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class TuringPotionsItems {
	public static final PotionItem POTION = new PotionItem(new QuiltItemSettings().maxCount(1));

	public static void register() {
		Registry.register(Registry.ITEM, TuringPotionsMod.identifier("potion"), POTION);

	}
}
