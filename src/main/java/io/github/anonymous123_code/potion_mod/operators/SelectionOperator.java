package io.github.anonymous123_code.potion_mod.operators;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import io.github.anonymous123_code.potion_mod.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.potion_mod.data_type.AmountData;
import io.github.anonymous123_code.potion_mod.data_type.VoidDataFactory;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author anonymous123-code
 */
public class SelectionOperator extends ArgumentExecutingOperator {
	public SelectionOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(List<Data<?>> parameters) {
		if (parameters.size() < 2 || !(parameters.get(0) instanceof AmountData)) {
			return VoidDataFactory.getInstance().create(null);
		} else {
			return parameters.get((int) Math.max(
							Math.min(
									((AmountData) parameters.get(0)).getValue() + 1,
									parameters.size() - 1),
							1));
		}
	}
}
