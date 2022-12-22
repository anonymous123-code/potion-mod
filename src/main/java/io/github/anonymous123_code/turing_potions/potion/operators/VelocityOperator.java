package io.github.anonymous123_code.turing_potions.potion.operators;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorExecutionContext;
import io.github.anonymous123_code.turing_potions.potion.data_type.AmountData;
import io.github.anonymous123_code.turing_potions.potion.data_type.TuringPotionsDataFactories;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class VelocityOperator extends ArgumentExecutingOperator {
	public VelocityOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(OperatorExecutionContext<List<Data<?>>> context) {
		List<Data<?>> data = context.getParameter();
		Entity evaluatingEntity = context.getEvaluatingEntity();
		Vec3d velocity = evaluatingEntity.getVelocity();
		if (data.size() == 0) {
			return TuringPotionsDataFactories.LIST.create(List.of(
					TuringPotionsDataFactories.AMOUNT.create(velocity.x),
					TuringPotionsDataFactories.AMOUNT.create(velocity.y),
					TuringPotionsDataFactories.AMOUNT.create(velocity.z)
			));
		} else if (data.size() == 1 && data.get(0) instanceof AmountData) {
			double value = (double) data.get(0).getValue();

			if (value == 1d) {
				return TuringPotionsDataFactories.AMOUNT.create(velocity.x);
			}

			if (value == 2d) {
				return TuringPotionsDataFactories.AMOUNT.create(velocity.y);
			}

			if (value == 3d) {
				return TuringPotionsDataFactories.AMOUNT.create(velocity.z);
			}
			return TuringPotionsDataFactories.VOID.create(null);
		} else if (data.size() == 2 && data.get(0) instanceof AmountData && data.get(1) instanceof AmountData) {
			double value = (double) data.get(0).getValue();

			if (value == 1d) {
				addVelocityToEntity(evaluatingEntity, ((AmountData) data.get(1)).getValue(), 0, 0);
				return TuringPotionsDataFactories.AMOUNT.create(evaluatingEntity.getVelocity().x);
			}

			if (value == 2d) {
				addVelocityToEntity(evaluatingEntity, 0, ((AmountData) data.get(1)).getValue(), 0);
				return TuringPotionsDataFactories.AMOUNT.create(evaluatingEntity.getVelocity().y);
			}

			if (value == 3d) {
				addVelocityToEntity(evaluatingEntity, 0, 0, ((AmountData) data.get(1)).getValue());
				return TuringPotionsDataFactories.AMOUNT.create(evaluatingEntity.getVelocity().z);
			}
			return TuringPotionsDataFactories.VOID.create(null);
		} else if (data.size() == 3 && data.get(0) instanceof AmountData && data.get(1) instanceof AmountData && data.get(2) instanceof AmountData) {
			addVelocityToEntity(evaluatingEntity, ((AmountData) data.get(0)).getValue(), ((AmountData) data.get(1)).getValue(), ((AmountData) data.get(2)).getValue());
			return TuringPotionsDataFactories.LIST.create(List.of(
					TuringPotionsDataFactories.AMOUNT.create(evaluatingEntity.getVelocity().x),
					TuringPotionsDataFactories.AMOUNT.create(evaluatingEntity.getVelocity().y),
					TuringPotionsDataFactories.AMOUNT.create(evaluatingEntity.getVelocity().z)
			));
		}
		return TuringPotionsDataFactories.VOID.create(null);
	}

	private static void addVelocityToEntity(Entity entity, double x, double y, double z) {
		entity.addVelocity(x,y,z);
		if (entity instanceof ServerPlayerEntity) {
			((ServerPlayerEntity) entity).networkHandler.sendPacket(new ExplosionS2CPacket(entity.getX()-4096, 0, 0, 0, List.of(), new Vec3d(x,y,z)));
		}
	}
}
