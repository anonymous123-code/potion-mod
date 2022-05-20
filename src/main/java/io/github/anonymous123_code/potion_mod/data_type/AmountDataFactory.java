package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import io.github.anonymous123_code.potion_mod.api.data_type.DataFactory;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class AmountDataFactory extends DataFactory<Float> {
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
	public AmountData create(Float param) {
		return new AmountData(this.getIdentifier(), param);
	}

	public static class AmountData extends Data<Float> {
		private final Float value;
		private AmountData(Identifier identifier, Float param) {
			super(identifier, param);
			value = param;
		}

		@Override
		public Float getValue() {
			return this.value;
		}
	}
}
