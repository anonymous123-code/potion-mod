package io.github.anonymous123_code.turing_potions.potion.operators;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorExecutionContext;
import io.github.anonymous123_code.turing_potions.potion.data_type.ListData;
import io.github.anonymous123_code.turing_potions.potion.data_type.TuringPotionsDataFactories;
import net.minecraft.util.Identifier;

import java.util.List;

public class ListOperator extends ArgumentExecutingOperator {
	public ListOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public ListData getResult(OperatorExecutionContext<List<Data<?>>> context) {
		return (ListData) TuringPotionsDataFactories.LIST.create(context.getParameter());
	}
}
