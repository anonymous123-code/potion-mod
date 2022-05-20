package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import io.github.anonymous123_code.potion_mod.api.data_type.DataFactory;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class VoidDataFactory extends DataFactory<Object> {
	private static VoidDataFactory INSTANCE = null;

	public VoidDataFactory(Identifier identifier) {
		super(identifier);
	}

	public static void setUp(Identifier identifier) {
		INSTANCE = new VoidDataFactory(identifier);
	}

	public static VoidDataFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public VoidData create(Object param) {
		return new VoidData(this.getIdentifier(), param);
	}

	public static class VoidData extends Data<Object> {
		private VoidData(Identifier identifier, Object param) {
			super(identifier, param);
		}

		@Override
		public Object getValue() {
			return null;
		}
	}
}
