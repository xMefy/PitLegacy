package me.stitchie.legacypit.items;

import lombok.Getter;
import me.stitchie.legacypit.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

@Getter
public final class RespawnItems {

	private final ItemStack ironsword;
	private final ItemStack bow;
	private final ItemStack arrow;

	private final ItemStack ironchestplate;
	private final ItemStack ironleggings;
	private final ItemStack ironboots;

	private final ItemStack chainchestplate;
	private final ItemStack chainleggings;
	private final ItemStack chainboots;

	private final List<Material> itemsLostonDeath;

	public RespawnItems() {
		this.ironsword = new ItemCreator(Material.IRON_SWORD).createItem(true, false);
		this.bow = new ItemCreator(Material.BOW).createItem(true, false);
		this.arrow = new ItemCreator(Material.ARROW).createItem(false, false);

		this.ironchestplate = new ItemCreator(Material.IRON_CHESTPLATE).createItem(true, false);
		this.ironleggings = new ItemCreator(Material.IRON_LEGGINGS).createItem(true, false);
		this.ironboots = new ItemCreator(Material.IRON_BOOTS).createItem(true, false);

		this.chainchestplate = new ItemCreator(Material.CHAINMAIL_CHESTPLATE).createItem(true, false);
		this.chainleggings = new ItemCreator(Material.CHAINMAIL_LEGGINGS).createItem(true, false);
		this.chainboots = new ItemCreator(Material.CHAINMAIL_BOOTS).createItem(true, false);

		this.itemsLostonDeath = Arrays.asList(Material.IRON_SWORD, Material.ARROW, Material.BOW,
				Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_LEGGINGS,
				Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.DIAMOND_SWORD,
				Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
				Material.OBSIDIAN, Material.DIAMOND_PICKAXE, Material.IRON_AXE, Material.FISHING_ROD, Material.COBBLESTONE);
	}
}
