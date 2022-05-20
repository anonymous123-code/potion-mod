package io.github.anonymous123_code.turing_potions.operators;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.turing_potions.data_type.AmountData;
import io.github.anonymous123_code.turing_potions.data_type.AmountDataFactory;
import io.github.anonymous123_code.turing_potions.data_type.VoidDataFactory;
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
	public Data<?> getResult(List<Data<?>> parameters) {
		if (parameters.isEmpty()) {
			return AmountDataFactory.getInstance().create(-1.0);
		} else if (parameters.size() == 1) {
			Data<?> arg = parameters.get(0);
			if (arg instanceof AmountData) {
				return AmountDataFactory.getInstance().create(-((AmountData) arg).getValue());
			} else {
				return VoidDataFactory.getInstance().create(null);
			}
		} else {
			Data<?> arg1 = parameters.get(0);
			double result = 0;
			if (arg1 instanceof AmountData) {
				result += 2 * ((AmountData) arg1).getValue();
			} else {
				return VoidDataFactory.getInstance().create(null);
			}
			for (Data<?> arg : parameters) {
				if (arg instanceof AmountData) {
					result -= ((AmountData) arg).getValue();
				} else {
					return VoidDataFactory.getInstance().create(null);
				}
			}
			return AmountDataFactory.getInstance().create(result);
		}
	}
}
