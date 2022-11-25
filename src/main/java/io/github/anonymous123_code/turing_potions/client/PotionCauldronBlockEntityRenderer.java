package io.github.anonymous123_code.turing_potions.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.anonymous123_code.turing_potions.block.PotionCauldronBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class PotionCauldronBlockEntityRenderer implements BlockEntityRenderer<PotionCauldronBlockEntity> {
	private static final ItemStack stack = new ItemStack(Items.JUKEBOX, 1);
	private final SpriteIdentifier potionSpriteIdentifier;

	public PotionCauldronBlockEntityRenderer(BlockEntityRendererFactory.Context ctx, ModContainer mod) {
		potionSpriteIdentifier = new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, new Identifier(mod.metadata().id(),"block/potion_cauldron_potion"));
	}

	@Override
	public void render(PotionCauldronBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		matrices.push();

		// Fabric BER tutorial TODO: remove
		matrices.push();
		double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
		matrices.translate(0.5, 1.25 + offset, 0.5);

		matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((entity.getWorld().getTime() + tickDelta) * 4));

		MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, light, overlay, matrices, vertexConsumers, 0);
		matrices.pop();

		// Render potion
		matrices.push();
		Sprite potionSprite =  potionSpriteIdentifier.getSprite();
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getTranslucent());

		vertexConsumer
				.vertex(matrices.peek().getModel(), 0, 0, 0)
				.color(1f, 1f, 1f, 1f)
				.uv(potionSprite.getMinU(), potionSprite.getMinV())
				.light(0,240)
				.normal(0,1,0)
				.next();

		vertexConsumer
				.vertex(matrices.peek().getModel(), 1, 0, 0)
				.color(1f, 1f, 1f, 1f)
				.uv(potionSprite.getMaxU(), potionSprite.getMinV())
				.light(0,240)
				.normal(0,1,0).next();

		vertexConsumer
				.vertex(matrices.peek().getModel(), 1, 0, 1)
				.color(1f, 1f, 1f, 1f)
				.uv(potionSprite.getMaxU(), potionSprite.getMaxV())
				.light(0,240)
				.normal(0,1,0).next();

		vertexConsumer
				.vertex(matrices.peek().getModel(), 0, 0, 1)
				.color(1f, 1f, 1f, 1f)
				.uv(potionSprite.getMinU(), potionSprite.getMaxV())
				.light(0,240)
				.normal(0,1,0).next();

		matrices.pop();
		matrices.pop();
	}
}
