package io.github.anonymous123_code.turing_potions.client;

import io.github.anonymous123_code.turing_potions.block.PotionCauldronBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3f;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class PotionCauldronBlockEntityRenderer implements BlockEntityRenderer<PotionCauldronBlockEntity> {
	private static final ItemStack stack = new ItemStack(Items.JUKEBOX, 1);

	public PotionCauldronBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

	}

	@Override
	public void render(PotionCauldronBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		matrices.push();

		double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;

		matrices.translate(0.5, 1.25 + offset, 0.5);

		matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((entity.getWorld().getTime() + tickDelta) * 4));

		MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, light, overlay, matrices, vertexConsumers, 0);

		matrices.pop();
	}
}
