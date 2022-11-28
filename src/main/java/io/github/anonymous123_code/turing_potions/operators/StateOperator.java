package io.github.anonymous123_code.turing_potions.operators;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.turing_potions.data_type.AmountData;
import io.github.anonymous123_code.turing_potions.data_type.TuringPotionsDataFactories;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;

/**
 * @author anonymous123-code
 */
public class StateOperator extends ArgumentExecutingOperator {
	HashMap<Double, Data<?>> storage = new HashMap<>();

	public StateOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(List<Data<?>> parameters) {
		if (parameters.size() < 1) {
			return TuringPotionsDataFactories.VOID.create(null);
		}
		if (!(parameters.get(0) instanceof AmountData)){
			return TuringPotionsDataFactories.VOID.create(null);
		}
		if (parameters.size() == 1) {
				Data<?> result = storage.get(((AmountData) parameters.get(0)).getValue());
				if (result == null) {
					return TuringPotionsDataFactories.AMOUNT.create(0.);
				}
				return result;
		} else if (parameters.size() == 2) {
			storage.put(((AmountData) parameters.get(0)).getValue(), parameters.get(1));
			return parameters.get(1);
		} else {
			return TuringPotionsDataFactories.VOID.create(null);
		}
	}
}
