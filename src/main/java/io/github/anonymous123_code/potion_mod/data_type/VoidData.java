package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class VoidData extends Data<Object> {
	VoidData(Identifier identifier, Object param) {
		super(identifier, param);
	}

	@Override
	public Object getValue() {
		return null;
	}
}
