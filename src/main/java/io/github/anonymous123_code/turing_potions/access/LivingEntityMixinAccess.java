package io.github.anonymous123_code.turing_potions.access;

import net.minecraft.nbt.NbtCompound;

import java.util.List;

public interface LivingEntityMixinAccess {
	List<NbtCompound> getTuringPotions$activePotions();
}
