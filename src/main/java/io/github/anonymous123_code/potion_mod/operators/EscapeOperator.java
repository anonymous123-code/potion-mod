package io.github.anonymous123_code.potion_mod.operators;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import io.github.anonymous123_code.potion_mod.api.operator.RawArgumentOperator;
import io.github.anonymous123_code.potion_mod.data_type.PotionDataFactory;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class EscapeOperator extends RawArgumentOperator {
	public EscapeOperator(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<?> getResult(NbtList params) {
		return PotionDataFactory.getInstance().create(params);
	}
}
