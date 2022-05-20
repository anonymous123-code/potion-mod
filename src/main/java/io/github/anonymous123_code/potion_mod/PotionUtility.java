package io.github.anonymous123_code.potion_mod;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import io.github.anonymous123_code.potion_mod.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.potion_mod.api.operator.Operator;
import io.github.anonymous123_code.potion_mod.api.operator.OperatorRegistry;
import io.github.anonymous123_code.potion_mod.api.operator.RawArgumentOperator;
import io.github.anonymous123_code.potion_mod.data_type.VoidDataFactory;
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
			for (NbtElement element : potion.getList("parameters", 10)) {
				params.add(evaluatePotion((NbtCompound) element, rec_depth + 1));
			}
			return ((ArgumentExecutingOperator) operator).getResult(params);
		} else if (operator instanceof RawArgumentOperator) {
			return ((RawArgumentOperator) operator).getResult(potion.getList("parameters", 10));
		} else {
			// This should never happen, sealed is used
			return VoidDataFactory.getInstance().create(null);
		}
	}
}
