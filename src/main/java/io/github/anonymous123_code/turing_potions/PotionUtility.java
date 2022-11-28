package io.github.anonymous123_code.turing_potions;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.turing_potions.api.operator.Operator;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorRegistry;
import io.github.anonymous123_code.turing_potions.api.operator.RawArgumentOperator;
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
	public static Data<?> evaluatePotion(NbtCompound potion, int rec_depth) {
		Operator<?> operator = OperatorRegistry.get(new Identifier(potion.getString("operator")));
		if (operator instanceof ArgumentExecutingOperator) {
			List<Data<?>> params = new ArrayList<>();
			for (NbtElement element : potion.getList("parameters", NbtElement.COMPOUND_TYPE)) {
				params.add(evaluatePotion((NbtCompound) element, rec_depth + 1));
			}
			return ((ArgumentExecutingOperator) operator).getResult(params);
		} else if (operator instanceof RawArgumentOperator) {
			return ((RawArgumentOperator) operator).getResult(potion.getList("parameters", NbtElement.COMPOUND_TYPE));
		} else {
			// This should never happen, sealed is used
			return TuringPotionsDataFactories.VOID.create(null);
		}
	}
}
