package io.github.anonymous123_code.turing_potions.potion.data_type;

import io.github.anonymous123_code.turing_potions.api.data_type.DataFactory;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class AmountDataFactory extends DataFactory<Double> {

	AmountDataFactory(Identifier identifier) {
		super(identifier);
	}

	@Override
	public AmountData create(Double param) {
		return new AmountData(this.getIdentifier(), param);
	}

}
