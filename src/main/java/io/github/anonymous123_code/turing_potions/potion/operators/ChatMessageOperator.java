package io.github.anonymous123_code.turing_potions.potion.operators;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.ArgumentExecutingOperator;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorExecutionContext;
import io.github.anonymous123_code.turing_potions.potion.data_type.TuringPotionsDataFactories;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

public class ChatMessageOperator extends ArgumentExecutingOperator {
	public ChatMessageOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(OperatorExecutionContext<List<Data<?>>> context) {
		if (context.getEvaluatingEntity() instanceof ServerPlayerEntity) {
			context.getParameter().forEach(data -> ((ServerPlayerEntity) context.getEvaluatingEntity()).sendMessage(data.toText(), false));
		}
		return TuringPotionsDataFactories.VOID.create(null);
	}
}
