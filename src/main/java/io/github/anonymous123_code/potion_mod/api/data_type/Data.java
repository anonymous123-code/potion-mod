package io.github.anonymous123_code.potion_mod.api.data_type;

import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public abstract class Data<B> {
	private final Identifier identifier;

	public Data(Identifier identifier, B param) {
		this.identifier = identifier;
	}

	public Identifier getIdentifier() {
		return this.identifier;
	}

	public abstract B getValue();
}
