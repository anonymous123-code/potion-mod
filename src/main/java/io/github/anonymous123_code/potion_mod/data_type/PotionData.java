package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class PotionData extends Data<NbtCompound> {
	private final NbtCompound value;

	PotionData(Identifier identifier, NbtCompound param) {
		super(identifier, param);
		value = param;
	}

	@Override
	public String toString() {
		return super.toString() + "[" + this.value + "]";
	}

	@Override
	public NbtCompound getValue() {
		return this.value;
	}
}
