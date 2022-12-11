package io.github.anonymous123_code.turing_potions.api.operator;

import io.github.anonymous123_code.turing_potions.api.data_type.Data;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
public sealed abstract class Operator<B> permits ArgumentExecutingOperator, RawArgumentOperator{
	protected abstract Identifier getIdentifier();
	protected abstract Data<?> getResult(OperatorExecutionContext<B> params);
	public MutableText getTranslatableText(){
		return Text.translatable("turing_potions.operator." + getIdentifier().getNamespace() + "." + String.join(".", getIdentifier().getPath().split("/")));
	}
}
