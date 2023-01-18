package me.stitchie.legacypit.register;

import me.stitchie.legacypit.upgrades.*;

import java.util.EnumMap;
import java.util.Map;

public enum UpgradeManager {

	INSTANCE;

	private final Map<Upgrades, Upgrade> upgradeMap = new EnumMap<>(Upgrades.class);

	UpgradeManager() {
		upgradeMap.put(Upgrades.XPBOOST, new XPBoost());
		upgradeMap.put(Upgrades.GOLDBOOST, new GoldBoost());
		upgradeMap.put(Upgrades.MELEEDAMAGE, new MeleeDamage());
		upgradeMap.put(Upgrades.BOWDAMAGE, new BowDamage());
		upgradeMap.put(Upgrades.DAMAGEREDUCTION, new DamageReduction());
		upgradeMap.put(Upgrades.BUILDBATTLER, new BuildBattler());
	}

	public Map<Upgrades, Upgrade> getUpgradeList() {
		return upgradeMap;
	}
}
