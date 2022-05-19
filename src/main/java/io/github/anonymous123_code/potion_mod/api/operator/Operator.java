package io.github.anonymous123_code.potion_mod.api.operator;

import io.github.anonymous123_code.potion_mod.api.data_type.DataFactory;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author anonymous123-code
 */
public sealed abstract class Operator<B> permits ArgumentExecutingOperator, RawArgumentOperator{
	protected abstract Identifier getIdentifier();
	protected abstract DataFactory.Data getResult(B params);
}
