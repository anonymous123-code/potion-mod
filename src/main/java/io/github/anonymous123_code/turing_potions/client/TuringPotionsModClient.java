package io.github.anonymous123_code.turing_potions.client;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

@ClientOnly
public class TuringPotionsModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		BlockEntityRendererRegistry.register(TuringPotionsMod.POTION_CAULDRON_BLOCK_ENTITY_TYPE, PotionCauldronBlockEntityRenderer::new);
	}
}
