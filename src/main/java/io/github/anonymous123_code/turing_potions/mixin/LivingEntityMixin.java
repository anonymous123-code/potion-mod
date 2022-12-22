package io.github.anonymous123_code.turing_potions.mixin;

import io.github.anonymous123_code.turing_potions.access.LivingEntityMixinAccess;
import io.github.anonymous123_code.turing_potions.potion.PotionEvaluator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityMixinAccess {
	private ArrayList<NbtCompound> turingPotions$activePotions;

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
		throw new AssertionError();
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	public void turingPotions$constructorEnd(EntityType entityType, World world, CallbackInfo ci) {
		this.turingPotions$activePotions = new ArrayList<>();
	}

	@Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getX()D"))
	public void turingPotions$afterTickMovement(CallbackInfo ci) {
		if (this.getWorld().isClient()) return;
		this.getWorld().getServer().execute(() -> PotionEvaluator.potionTick((LivingEntity) (Object) this));
	}

	@Override
	public List<NbtCompound> getTuringPotions$activePotions() {
		return turingPotions$activePotions;
	}
}
