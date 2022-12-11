package io.github.anonymous123_code.turing_potions;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.*;
import io.github.anonymous123_code.turing_potions.data_type.TuringPotionsDataFactories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anonymous123-code
 */
public class PotionUtility {
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
