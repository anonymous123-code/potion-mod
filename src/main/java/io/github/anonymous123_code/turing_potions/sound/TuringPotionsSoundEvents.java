package io.github.anonymous123_code.turing_potions.sound;

import io.github.anonymous123_code.turing_potions.TuringPotionsMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class TuringPotionsSoundEvents {
	public static SoundEvent POTION_CAULDRON_EVAPORATE = new SoundEvent(TuringPotionsMod.identifier("block.potion_cauldron.evaporate"));

	public static void register() {
		Registry.register(Registry.SOUND_EVENT, POTION_CAULDRON_EVAPORATE.getId(), POTION_CAULDRON_EVAPORATE);
	}
}
