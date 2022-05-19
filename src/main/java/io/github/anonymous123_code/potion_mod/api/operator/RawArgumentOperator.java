package io.github.anonymous123_code.potion_mod.api.operator;

import io.github.anonymous123_code.potion_mod.api.data_type.DataFactory;
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
	public abstract DataFactory.Data getResult(NbtList params);
}
