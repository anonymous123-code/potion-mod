package io.github.anonymous123_code.potion_mod;

import io.github.anonymous123_code.potion_mod.api.operator.OperatorRegistry;
import io.github.anonymous123_code.potion_mod.data_type.AmountDataFactory;
import io.github.anonymous123_code.potion_mod.data_type.ListDataFactory;
import io.github.anonymous123_code.potion_mod.data_type.PotionDataFactory;
import io.github.anonymous123_code.potion_mod.data_type.VoidDataFactory;
import io.github.anonymous123_code.potion_mod.item.PotionItem;
import io.github.anonymous123_code.potion_mod.operators.AmountOperator;
import io.github.anonymous123_code.potion_mod.operators.EscapeOperator;
import io.github.anonymous123_code.potion_mod.operators.NegativityOperator;
import io.github.anonymous123_code.potion_mod.operators.SelectionOperator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PotionMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Potion Mod");
	public static final PotionItem POTION_ITEM = new PotionItem(new QuiltItemSettings().maxCount(1));

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "potion"), POTION_ITEM);

		OperatorRegistry.register(new AmountOperator(new Identifier(mod.metadata().id(), "amount")));
		OperatorRegistry.register(new NegativityOperator(new Identifier(mod.metadata().id(), "negativity")));
		OperatorRegistry.register(new SelectionOperator(new Identifier(mod.metadata().id(), "selection")));
		OperatorRegistry.register(new EscapeOperator(new Identifier(mod.metadata().id(), "escape")));

		VoidDataFactory.setUp(new Identifier(mod.metadata().id(), "void"));
		AmountDataFactory.setUp(new Identifier(mod.metadata().id(), "amount"));
		PotionDataFactory.setUp(new Identifier(mod.metadata().id(), "potion"));
		ListDataFactory.setUp(new Identifier(mod.metadata().id(), "list"));
	}
}
