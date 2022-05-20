package io.github.anonymous123_code.potion_mod.data_type;

import io.github.anonymous123_code.potion_mod.api.data_type.Data;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author anonymous123-code
 */
public class ListData extends Data<List<Data<?>>> {
	private final List<Data<?>> value;

	ListData(Identifier identifier, List<Data<?>> param) {
		super(identifier, param);
		value = param;
	}

	@Override
	public String toString() {
		return super.toString() + "[" + this.value + "]";
	}

	@Override
	public List<Data<?>> getValue() {
		return this.value.stream().toList();
	}
}
