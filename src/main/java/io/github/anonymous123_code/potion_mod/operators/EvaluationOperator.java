package io.github.anonymous123_code.potion_mod.operators;

import io.github.anonymous123_code.potion_mod.PotionUtility;
import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import io.github.anonymous123_code.potion_mod.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.potion_mod.data_type.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anonymous123-code
 */
public class EvaluationOperator extends ArgumentExecutingOperator {
	public EvaluationOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(List<Data<?>> parameters) {
		List<Data<?>> result = new ArrayList<>();
		for (Data<?> param :
				parameters) {
			if (param instanceof PotionData) {
				List<Data<?>> resultNested = new ArrayList<>();
				for (NbtElement element : ((PotionData) param).getValue()) {
					resultNested.add(PotionUtility.evaluatePotion((NbtCompound) element, 1));
				}
				result.add(ListDataFactory.getInstance().create(resultNested));
			} else {
				result.add(VoidDataFactory.getInstance().create(null));
			}
		}
		if (result.size() == 1) {
			if (result.get(0) instanceof VoidData) {
				return result.get(0);
			} else {
				ListData potentialResult = (ListData) result.get(0);
				if (potentialResult.getValue().size() == 1) {
					return potentialResult.getValue().get(0);
				}
				return potentialResult;
			}
		}
		return ListDataFactory.getInstance().create(result);
	}
}
