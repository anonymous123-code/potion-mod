package io.github.anonymous123_code.turing_potions.operators;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorExecutionContext;
import io.github.anonymous123_code.turing_potions.data_type.AmountData;
import io.github.anonymous123_code.turing_potions.data_type.TuringPotionsDataFactories;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author anonymous123-code
 */
public class NegativityOperator extends ArgumentExecutingOperator {

	public NegativityOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(OperatorExecutionContext<List<Data<?>>> context) {
		List<Data<?>> params = context.getParameter();
		if (params.isEmpty()) {
			return TuringPotionsDataFactories.AMOUNT.create(-1.0);
		} else if (params.size() == 1) {
			Data<?> arg = params.get(0);
			if (arg instanceof AmountData) {
				return TuringPotionsDataFactories.AMOUNT.create(-((AmountData) arg).getValue());
			} else {
				return TuringPotionsDataFactories.VOID.create(null);
			}
		} else {
			Data<?> arg1 = params.get(0);
			double result = 0;
			if (arg1 instanceof AmountData) {
				result += 2 * ((AmountData) arg1).getValue();
			} else {
				return TuringPotionsDataFactories.VOID.create(null);
			}
			for (Data<?> arg : params) {
				if (arg instanceof AmountData) {
					result -= ((AmountData) arg).getValue();
				} else {
					return TuringPotionsDataFactories.VOID.create(null);
				}
			}
			return TuringPotionsDataFactories.AMOUNT.create(result);
		}
	}
}
