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
public class SelectionOperator extends ArgumentExecutingOperator {
	public SelectionOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(OperatorExecutionContext<List<Data<?>>> context) {
		List<Data<?>> params = context.getParameter();
		if (params.size() < 2 || !(params.get(0) instanceof AmountData)) {
			return TuringPotionsDataFactories.VOID.create(null);
		} else {
			return params.get((int) Math.max(
							Math.min(
									((AmountData) params.get(0)).getValue() + 1,
									params.size() - 1),
							1));
		}
	}
}
