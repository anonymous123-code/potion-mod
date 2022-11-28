package io.github.anonymous123_code.turing_potions;

import io.github.anonymous123_code.turing_potions.block.TuringPotionsBlocks;
import io.github.anonymous123_code.turing_potions.item.TuringPotionsItems;
import io.github.anonymous123_code.turing_potions.operators.*;
import io.github.anonymous123_code.turing_potions.sound.TuringPotionsSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.registry.attachment.api.RegistryEntryAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TuringPotionsMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Turing Potions");
	public static RegistryEntryAttachment<Block, Integer> heatRateRegistryAttachment;
	public static RegistryEntryAttachment<Block, Integer> heatCapRegistryAttachment;

	private static String MOD_ID;

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		MOD_ID = mod.metadata().id();

		TuringPotionsItems.register();
		TuringPotionsBlocks.register();
		TuringPotionsOperators.register();
		TuringPotionsSoundEvents.register();

		heatRateRegistryAttachment = RegistryEntryAttachment.intBuilder(Registry.BLOCK, new Identifier(mod.metadata().id(), "heat_rate"))
				.defaultValue(0)
				.side(RegistryEntryAttachment.Side.SERVER)
				.build();

		heatCapRegistryAttachment = RegistryEntryAttachment.intBuilder(Registry.BLOCK, new Identifier(mod.metadata().id(), "heat_cap"))
				.defaultValue(0)
				.side(RegistryEntryAttachment.Side.SERVER)
				.build();

	}

	public static Identifier identifier(String name) {
		return new Identifier(MOD_ID, name);
	}

	public static String id() {
		return MOD_ID;
	}
}
