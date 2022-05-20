package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class PotionData extends Data<NbtList> {
	private final NbtList value;

	PotionData(Identifier identifier, NbtList param) {
		super(identifier, param);
		value = param;
	}

	@Override
	public String toString() {
		return super.toString() + "[" + this.value + "]";
	}

	@Override
	public NbtList getValue() {
		return this.value;
	}
}
