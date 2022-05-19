package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.DataType;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class AmountDataType implements DataType<Float> {
	private final Identifier identifier;

	public AmountDataType (Identifier identifier) {
		this.identifier = identifier;
	}

	@Override
	public Float getValue() {
		return null;
	}

	@Override
	public Identifier getIdentifier() {
		return this.identifier;
	}
}
