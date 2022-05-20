package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class AmountData extends Data<Double> {
	private final Double value;

	AmountData(Identifier identifier, Double param) {
		super(identifier, param);
		value = param;
	}

	@Override
	public String toString() {
		return super.toString() + "[" + this.value + "]";
	}

	@Override
	public Double getValue() {
		return this.value;
	}
}
