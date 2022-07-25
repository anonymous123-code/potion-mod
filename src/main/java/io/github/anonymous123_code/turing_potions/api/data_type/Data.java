package io.github.anonymous123_code.turing_potions.api.data_type;

import net.minecraft.text.Text;
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

	public Text toText() {
		return Text.translatable("turing_potions.data_type." + getIdentifier().getNamespace() + "." + String.join(".", getIdentifier().getPath().split("/")));
	}
}
