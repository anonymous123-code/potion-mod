package io.github.anonymous123_code.potion_mod.api.operator;

import io.github.anonymous123_code.potion_mod.api.data_type.DataType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author anonymous123-code
 */
public class OperatorRegistry implements Iterable<Operator> {
	private static final Map<Identifier, Operator> types = new HashMap<>();

	public static void register(Operator dataType) {
		types.put(dataType.getIdentifier(), dataType);
	}

	@NotNull
	@Override
	public Iterator<Operator> iterator() {
		return types.values().iterator();
	}

	public static Operator get(Identifier identifier) {
		return types.get(identifier);
	}
}
