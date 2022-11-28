package io.github.anonymous123_code.turing_potions.data_type;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.data_type.DataFactory;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author anonymous123-code
 */
public class ListDataFactory extends DataFactory<List<Data<?>>> {
	ListDataFactory(Identifier identifier) {
		super(identifier);
	}

	@Override
	public Data<List<Data<?>>> create(List<Data<?>> param) {
		return new ListData(this.getIdentifier(), param);
	}
}
