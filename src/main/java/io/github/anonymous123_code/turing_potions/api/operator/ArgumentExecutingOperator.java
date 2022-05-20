package io.github.anonymous123_code.turing_potions.api.operator;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author anonymous123-code
 */
public abstract non-sealed class ArgumentExecutingOperator extends Operator<List<Data<?>>> {
	private final Identifier identifier;

	public ArgumentExecutingOperator(Identifier identifier) {
		this.identifier = identifier;
	}

	@Override
	public Identifier getIdentifier() {
		return this.identifier;
	}

	public abstract Data<?> getResult(List<Data<?>> parameters);
}
