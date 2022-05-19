package io.github.anonymous123_code.potion_mod.api.data_type;

import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public interface DataType<T> {
	T getValue();
	Identifier getIdentifier();
}
