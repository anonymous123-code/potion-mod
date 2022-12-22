package io.github.anonymous123_code.turing_potions.potion.operators;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorExecutionContext;
import io.github.anonymous123_code.turing_potions.potion.data_type.AmountData;
import io.github.anonymous123_code.turing_potions.potion.data_type.TuringPotionsDataFactories;
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
	public Data<?> getResult(OperatorExecutionContext<List<Data<?>>> context) {
		List<Data<?>> params = context.getParameter();
		if (params.size() < 1) {
			return TuringPotionsDataFactories.VOID.create(null);
		}
		if (!(params.get(0) instanceof AmountData)){
			return TuringPotionsDataFactories.VOID.create(null);
		}
		if (params.size() == 1) {
				Data<?> result = storage.get(((AmountData) params.get(0)).getValue());
				if (result == null) {
					return TuringPotionsDataFactories.AMOUNT.create(0.);
				}
				return result;
		} else if (params.size() == 2) {
			storage.put(((AmountData) params.get(0)).getValue(), params.get(1));
			return params.get(1);
		} else {
			return TuringPotionsDataFactories.VOID.create(null);
		}
	}
}
