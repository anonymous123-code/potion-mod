package io.github.anonymous123_code.turing_potions.data_type;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
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

	@Override
	public Text toText() {
		return Text.literal(this.value.toString());
	}
}
