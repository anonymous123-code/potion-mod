package io.github.anonymous123_code.turing_potions.block;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

public class TuringPotionsBlocks {
	public static final PotionCauldron POTION_CAULDRON = new PotionCauldron(QuiltBlockSettings.copyOf(AbstractBlock.Settings.copy(Blocks.CAULDRON)), PotionCauldron.POTION_CAULDRON_BEHAVIOR);
	public static final BlockEntityType<PotionCauldronBlockEntity> POTION_CAULDRON_BLOCK_ENTITY_TYPE = QuiltBlockEntityTypeBuilder.create(PotionCauldronBlockEntity::new, POTION_CAULDRON).build(null);

	public static void register() {
		Registry.register(Registry.BLOCK, TuringPotionsMod.identifier("potion_cauldron"), POTION_CAULDRON);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, TuringPotionsMod.identifier("potion_cauldron"), POTION_CAULDRON_BLOCK_ENTITY_TYPE);
	}
}
