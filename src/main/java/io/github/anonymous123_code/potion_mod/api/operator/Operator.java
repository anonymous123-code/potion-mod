package io.github.anonymous123_code.potion_mod.api.operator;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public sealed abstract class Operator<B> permits ArgumentExecutingOperator, RawArgumentOperator{
	protected abstract Identifier getIdentifier();
	protected abstract Data<?> getResult(B params);
}
