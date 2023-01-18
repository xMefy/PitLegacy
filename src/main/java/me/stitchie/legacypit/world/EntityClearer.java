package me.stitchie.legacypit.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class EntityClearer {

	private final World world = Bukkit.getServer().getWorld("LegacyPit");
	private final ArrayList<EntityType> dontClearList = new ArrayList<>();

	public EntityClearer() {
		dontClearList.add(EntityType.ARMOR_STAND);
		dontClearList.add(EntityType.ITEM_FRAME);
		dontClearList.add(EntityType.MINECART);
		dontClearList.add(EntityType.MINECART_FURNACE);
		dontClearList.add(EntityType.MINECART_CHEST);
		dontClearList.add(EntityType.MINECART_TNT);
		dontClearList.add(EntityType.PAINTING);
	}

	public void removeAllEntitys() {

		int amount = 0;

		for (final Entity entity : world.getEntities()) {

			if (!dontClearList.contains(entity.getType())) {
				entity.remove();
				amount++;
			}
		}
		System.out.println("[PitLegacy] Cleared " + amount + " entitys.");
	}
}
