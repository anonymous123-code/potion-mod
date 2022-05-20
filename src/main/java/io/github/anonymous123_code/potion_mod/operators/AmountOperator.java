package io.github.anonymous123_code.potion_mod.operators;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import io.github.anonymous123_code.potion_mod.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.potion_mod.data_type.AmountDataFactory;
import io.github.anonymous123_code.potion_mod.data_type.VoidDataFactory;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author anonymous123-code
 */
public class AmountOperator extends ArgumentExecutingOperator {
	public AmountOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data getResult(List<Data> parameters) {
		if (parameters.isEmpty()) {
			return AmountDataFactory.getInstance().create(1.0);
		} else if (parameters.size() == 1) {
			Data arg = parameters.get(0);
			if (arg instanceof AmountDataFactory.AmountData) {
				return AmountDataFactory.getInstance().create(((AmountDataFactory.AmountData) arg).getValue() + 1);
			} else {
				return VoidDataFactory.getInstance().create(null);
			}
		} else {
			double result = 0;
			for (Data arg : parameters) {
				if (arg instanceof AmountDataFactory.AmountData) {
					result += ((AmountDataFactory.AmountData) arg).getValue();
				} else {
					return VoidDataFactory.getInstance().create(null);
				}
			}
			return AmountDataFactory.getInstance().create(result);
		}
	}
}
