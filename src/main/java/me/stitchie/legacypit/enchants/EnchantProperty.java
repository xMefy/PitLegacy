package me.stitchie.legacypit.enchants;

public class EnchantProperty<T> {

	private final T[] values;

	@SafeVarargs
	public EnchantProperty(final T... values) {
		this.values = values;
	}

	public T getValueAtLevel(final int level) {
		return values[level - 1];
	}

}
