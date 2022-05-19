package io.github.anonymous123_code.potion_mod.api.data_type;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author anonymous123-code
 */
public class DataTypeRegistry implements Iterable<DataType> {
	private static final Map<Identifier, DataType> types = new HashMap<>();

	public static void register(DataType dataType) {
		types.put(dataType.getIdentifier(), dataType);
	}

	@NotNull
	@Override
	public Iterator<DataType> iterator() {
		return types.values().iterator();
	}

	public static DataType get(Identifier identifier) {
		return types.get(identifier);
	}
}
