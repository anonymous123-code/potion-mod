package io.github.anonymous123_code.turing_potions.api.operator;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;


/**
 * @author anonymous123-code
 */
public abstract non-sealed class RawArgumentOperator extends Operator<NbtList> {
	private final Identifier identifier;

	public RawArgumentOperator(Identifier identifier) {
		this.identifier = identifier;
	}

	@Override
	public Identifier getIdentifier() {
		return this.identifier;
	}

	@Override
	public abstract Data<?> getResult(OperatorExecutionContext<NbtList> params);
}
