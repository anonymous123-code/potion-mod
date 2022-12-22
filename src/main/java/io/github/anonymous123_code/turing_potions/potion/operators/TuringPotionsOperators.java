package io.github.anonymous123_code.turing_potions.potion.operators;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorRegistry;

public class TuringPotionsOperators {
	public static final AmountOperator AMOUNT = new AmountOperator(TuringPotionsMod.identifier("amount"));
	public static final NegativityOperator NEGATIVITY = new NegativityOperator(TuringPotionsMod.identifier( "negativity"));
	public static final SelectionOperator SELECT = new SelectionOperator(TuringPotionsMod.identifier("select"));
	public static final EscapeOperator ESCAPE = new EscapeOperator(TuringPotionsMod.identifier("escape"));
	public static final EvaluationOperator EVALUATE = new EvaluationOperator(TuringPotionsMod.identifier("evaluate"));
	public static final StateOperator STATE = new StateOperator(TuringPotionsMod.identifier("state"));
	public static final ChatMessageOperator CHAT = new ChatMessageOperator(TuringPotionsMod.identifier("chat"));

	public static void register() {
		OperatorRegistry.register(AMOUNT);
		OperatorRegistry.register(NEGATIVITY);
		OperatorRegistry.register(SELECT);
		OperatorRegistry.register(ESCAPE);
		OperatorRegistry.register(EVALUATE);
		OperatorRegistry.register(STATE);
		OperatorRegistry.register(CHAT);
	}
}
