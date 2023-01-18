package me.stitchie.legacypit.player;

public enum Attributes {

	ATTACK_DAMAGE("generic.attackDamage"),
	MAX_HEALTH("generic.maxHealth"),
	MOVEMENT_SPEED("generic.movementSpeed"),
	ARMOR("generic.armor");

	private final String name;

	Attributes(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}