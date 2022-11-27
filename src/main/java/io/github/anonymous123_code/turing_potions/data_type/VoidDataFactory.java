package io.github.anonymous123_code.turing_potions.data_type;

import io.github.anonymous123_code.turing_potions.api.data_type.DataFactory;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class VoidDataFactory extends DataFactory<Object> {

	VoidDataFactory(Identifier identifier) {
		super(identifier);
	}

	@Override
	public VoidData create(Object param) {
		return new VoidData(this.getIdentifier(), param);
	}

}
