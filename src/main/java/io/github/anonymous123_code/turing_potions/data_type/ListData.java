package io.github.anonymous123_code.turing_potions.data_type;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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
		MutableText arrayDescriptor = new LiteralText("");
		for (Data<?> val :
				value) {
			arrayDescriptor.append(val.toText()).append(",");
		}
		return new TranslatableText(((TranslatableText) super.toText()).getKey(), arrayDescriptor);
	}
}
