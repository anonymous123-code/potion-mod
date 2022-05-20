package io.github.anonymous123_code.potion_mod.mixin;

import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author anonymous123-code
 */
@Mixin(PotionUtil.class)
public interface PotionUtilAccessor {
	@Accessor
	static Text getNONE_TEXT(){
		throw new AssertionError();
	}
}
