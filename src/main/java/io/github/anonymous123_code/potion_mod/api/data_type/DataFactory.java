package io.github.anonymous123_code.potion_mod.api.data_type;

import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public abstract class DataFactory<A> {
	private final Identifier identifier;

	public DataFactory (Identifier identifier) {
		this.identifier = identifier;
	}

	public abstract Data<A> create(A param);

	public Identifier getIdentifier() {
		return this.identifier;
	}
	public abstract class Data<B> {
		private final Identifier identifier;
		public Data (Identifier identifier, B param) {
			this.identifier = identifier;
		}

		public Identifier getIdentifier() {
			return this.identifier;
		}
		public abstract A getValue();
	}
}
