package io.github.anonymous123_code.turing_potions_test;

import java.util.function.Supplier;

public class ValueExpectationErrorMessageSupplier<T> implements Supplier<String> {
	private final String valueName;
	private T foundValue;
	private T expectedValue;

	public ValueExpectationErrorMessageSupplier(String valueName) {
		this.valueName = valueName;
	}

	@Override
	public String get() {
		return "Expected " + valueName + " to be `" + expectedValue + "` was `" + foundValue + "`";
	}

	public void setFoundValue(T foundValue) {
		this.foundValue = foundValue;
	}

	public void setExpectedValue(T expectedValue) {
		this.expectedValue = expectedValue;
	}
}
