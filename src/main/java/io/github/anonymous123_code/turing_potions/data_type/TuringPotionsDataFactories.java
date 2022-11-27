package io.github.anonymous123_code.turing_potions.data_type;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;

public class TuringPotionsDataFactories {
	public static final AmountDataFactory AMOUNT = new AmountDataFactory(TuringPotionsMod.identifier("amount"));
	public static final VoidDataFactory VOID = new VoidDataFactory(TuringPotionsMod.identifier("void"));
	public static final PotionDataFactory POTION = new PotionDataFactory(TuringPotionsMod.identifier("potion"));
	public static final ListDataFactory LIST = new ListDataFactory(TuringPotionsMod.identifier("list"));
}
