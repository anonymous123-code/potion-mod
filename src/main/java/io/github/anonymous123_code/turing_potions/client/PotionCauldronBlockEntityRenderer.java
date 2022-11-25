package io.github.anonymous123_code.turing_potions.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.anonymous123_code.turing_potions.block.PotionCauldronBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class PotionCauldronBlockEntityRenderer implements BlockEntityRenderer<PotionCauldronBlockEntity> {
	private static ModContainer mod;
	private static SpriteIdentifier potionSpriteIdentifier;

	public PotionCauldronBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
		potionSpriteIdentifier = new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, new Identifier("minecraft","block/water_still"));
	}

	public static void setModContainer(ModContainer mod) {
		PotionCauldronBlockEntityRenderer.mod = mod;
	}

	@Override
	public void render(PotionCauldronBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		matrices.push();

		// Render potion
		Sprite potionSprite =  potionSpriteIdentifier.getSprite();
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getTranslucent());

		vertexConsumer
				.vertex(matrices.peek().getModel(), 0.125f, .5625f + (entity.getLength()-1)*3/16f, 0.875f)
				.color(.7f, .5f, 1f, 1f)
				.uv(potionSprite.getMinU(), potionSprite.getMaxV())
				.light(0,240)
				.normal(0,1,0).next();

		vertexConsumer
				.vertex(matrices.peek().getModel(), 0.875f, .5625f + (entity.getLength()-1)*3/16f, 0.875f)
				.color(.7f, .5f, 1f, 1f)
				.uv(potionSprite.getMaxU(), potionSprite.getMaxV())
				.light(0,240)
				.normal(0,1,0).next();

		vertexConsumer
				.vertex(matrices.peek().getModel(), 0.875f, .5625f + (entity.getLength()-1)*3/16f, 0.125f)
				.color(.7f, .5f, 1f, 1f)
				.uv(potionSprite.getMaxU(), potionSprite.getMinV())
				.light(0,240)
				.normal(0,1,0).next();

		vertexConsumer
				.vertex(matrices.peek().getModel(), 0.125f, .5625f + (entity.getLength()-1)*3/16f, 0.125f)
				.color(.7f, .5f, 1f, 1f)
				.uv(potionSprite.getMinU(), potionSprite.getMinV())
				.light(0,240)
				.normal(0,1,0)
				.next();

		matrices.pop();
	}
}
