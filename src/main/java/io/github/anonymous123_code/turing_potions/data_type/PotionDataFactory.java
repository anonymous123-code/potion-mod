package io.github.anonymous123_code.turing_potions.data_type;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import io.github.anonymous123_code.turing_potions.api.data_type.DataFactory;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public class PotionDataFactory extends DataFactory<NbtList> {

	PotionDataFactory(Identifier identifier) {
		super(identifier);
	}
	@Override
	public Data<NbtList> create(NbtList param) {
		return new PotionData(this.getIdentifier(), param);
	}
}
