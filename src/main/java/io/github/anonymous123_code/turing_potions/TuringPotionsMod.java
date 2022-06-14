package io.github.anonymous123_code.turing_potions;

import io.github.anonymous123_code.turing_potions.api.operator.OperatorRegistry;
import io.github.anonymous123_code.turing_potions.block.PotionCauldron;
import io.github.anonymous123_code.turing_potions.block.PotionCauldronBlockEntity;
import io.github.anonymous123_code.turing_potions.data_type.AmountDataFactory;
import io.github.anonymous123_code.turing_potions.data_type.ListDataFactory;
import io.github.anonymous123_code.turing_potions.data_type.PotionDataFactory;
import io.github.anonymous123_code.turing_potions.data_type.VoidDataFactory;
import io.github.anonymous123_code.turing_potions.item.PotionItem;
import io.github.anonymous123_code.turing_potions.operators.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TuringPotionsMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Potion Mod");
	public static final PotionItem POTION_ITEM = new PotionItem(new QuiltItemSettings().maxCount(1));
	public static BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON_BLOCK_ENTITY_TYPE;
	public static final PotionCauldron POTION_CAULDRON_BLOCK = new PotionCauldron(QuiltBlockSettings.copyOf(AbstractBlock.Settings.copy(Blocks.CAULDRON)), PotionCauldron.POTION_CAULDRON_BEHAVIOR);

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		Registry.register(Registry.ITEM, new Identifier(mod.metadata().id(), "potion"), POTION_ITEM);

		OperatorRegistry.register(new AmountOperator(new Identifier(mod.metadata().id(), "amount")));
		OperatorRegistry.register(new NegativityOperator(new Identifier(mod.metadata().id(), "negativity")));
		OperatorRegistry.register(new SelectionOperator(new Identifier(mod.metadata().id(), "select")));
		OperatorRegistry.register(new EscapeOperator(new Identifier(mod.metadata().id(), "escape")));
		OperatorRegistry.register(new EvaluationOperator(new Identifier(mod.metadata().id(), "evaluate")));
		OperatorRegistry.register(new StateOperator(new Identifier(mod.metadata().id(), "state")));

		VoidDataFactory.setUp(new Identifier(mod.metadata().id(), "void"));
		AmountDataFactory.setUp(new Identifier(mod.metadata().id(), "amount"));
		PotionDataFactory.setUp(new Identifier(mod.metadata().id(), "potion"));
		ListDataFactory.setUp(new Identifier(mod.metadata().id(), "list"));


		Registry.register(Registry.BLOCK, new Identifier(mod.metadata().id(), "potion_cauldron"), POTION_CAULDRON_BLOCK);
		POTION_CAULDRON_BLOCK_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(mod.metadata().id(), "potion_cauldron"), FabricBlockEntityTypeBuilder.create(PotionCauldronBlockEntity::new, POTION_CAULDRON_BLOCK).build(null));
	}
}
