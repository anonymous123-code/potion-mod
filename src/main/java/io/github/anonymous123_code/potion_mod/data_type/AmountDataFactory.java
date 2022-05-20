package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.DataFactory;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class AmountDataFactory extends DataFactory<Double> {
	private static AmountDataFactory INSTANCE = null;

	private AmountDataFactory(Identifier identifier) {
		super(identifier);
	}

	public static void setUp(Identifier identifier) {
		INSTANCE = new AmountDataFactory(identifier);
	}

	public static AmountDataFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public AmountData create(Double param) {
		return new AmountData(this.getIdentifier(), param);
	}

}
