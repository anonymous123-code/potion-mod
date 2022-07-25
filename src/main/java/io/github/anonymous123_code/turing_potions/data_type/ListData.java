package io.github.anonymous123_code.turing_potions.data_type;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.component.TranslatableComponent;
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

	@Override
	public Text toText() {
		MutableText arrayDescriptor = Text.literal("");
		for (Data<?> val :
				value) {
			arrayDescriptor.append(val.toText()).append(",");
		}
		return Text.translatable(((TranslatableComponent) super.toText().asComponent()).getKey(), arrayDescriptor);
	}
}
