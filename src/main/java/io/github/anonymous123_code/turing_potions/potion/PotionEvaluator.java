package io.github.anonymous123_code.turing_potions.potion;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import io.github.anonymous123_code.turing_potions.access.LivingEntityMixinAccess;
import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.*;
import io.github.anonymous123_code.turing_potions.potion.data_type.TuringPotionsDataFactories;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class PotionEvaluator {
	public static void potionTick(LivingEntity entity) {
		for (NbtCompound potion:
				((LivingEntityMixinAccess) entity).getTuringPotions$activePotions()) {
			Data<?> result = evaluatePotion(potion, new OperatorExecutionContext<>(entity.getWorld(), entity, null));

			TuringPotionsMod.LOGGER.info(result.toText().getString());
		}
		// TODO: change for potions longer than 1 tick
		((LivingEntityMixinAccess) entity).getTuringPotions$activePotions().clear();
	}

	public static Data<?> evaluatePotion(NbtCompound potion, OperatorExecutionContext<?> context) {
		// get the operator of the top-level potion
		Operator<?> operator = OperatorRegistry.get(new Identifier(potion.getString("operator")));

		if (operator instanceof ArgumentExecutingOperator) {

			// retrieve parameters by evaluating all child potions recursively
			List<Data<?>> params = new ArrayList<>();
			for (NbtElement element : potion.getList("parameters", NbtElement.COMPOUND_TYPE)) {
				params.add(evaluatePotion((NbtCompound) element, context.withAddedRecursionDepth(1)));
			}

			// run main potion with parameters and return result
			return ((ArgumentExecutingOperator) operator).getResult(context.with(params));

		} else if (operator instanceof RawArgumentOperator) {

			// run main potion with raw parameter and return result
			return ((RawArgumentOperator) operator).getResult(context.with(potion.getList("parameters", NbtElement.COMPOUND_TYPE)));

		} else {
			// This should never happen, sealed is used
			return TuringPotionsDataFactories.VOID.create(null);
		}
	}
}
