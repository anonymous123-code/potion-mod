package io.github.anonymous123_code.turing_potions.potion.operators;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.operator.OperatorExecutionContext;
import io.github.anonymous123_code.turing_potions.api.operator.RawArgumentOperator;
import io.github.anonymous123_code.turing_potions.potion.data_type.TuringPotionsDataFactories;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class EscapeOperator extends RawArgumentOperator {
	public EscapeOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(OperatorExecutionContext<NbtList> context) {
		return TuringPotionsDataFactories.POTION.create(context.getParameter());
	}
}
