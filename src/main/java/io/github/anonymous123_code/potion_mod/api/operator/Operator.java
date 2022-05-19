package io.github.anonymous123_code.potion_mod.api.operator;

import io.github.anonymous123_code.potion_mod.api.data_type.DataType;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author anonymous123-code
 */
public interface Operator{
	Identifier getIdentifier();
	DataType getResult(List<Operator> parameters);
}
