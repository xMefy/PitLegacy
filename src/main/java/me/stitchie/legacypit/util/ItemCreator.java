package me.stitchie.legacypit.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;

public final class ItemCreator {

	private final String displayName;
	private final ArrayList<String> lore;
	private final Material material;
	private final byte materialType;

	public ItemCreator(final Material material, final byte materialType, final String displayName, final String... lore) {
		this.displayName = displayName;
		this.lore = new ArrayList<>(Arrays.asList(lore));
		this.material = material;
		this.materialType = materialType;
	}

	public ItemCreator(final Material material, final byte materialType, final String displayName) {
		this.displayName = displayName;
		this.lore = null;
		this.material = material;
		this.materialType = materialType;
	}

	public ItemCreator(final Material material, final String displayName, final String... lore) {
		this.displayName = displayName;
		this.lore = new ArrayList<>(Arrays.asList(lore));
		this.material = material;
		this.materialType = 0;
	}

	public ItemCreator(final Material material, final String displayName, final ArrayList<String> lore) {
		this.displayName = displayName;
		this.lore = lore;
		this.material = material;
		this.materialType = 0;
	}

	public ItemCreator(final Material material, final String displayName) {
		this.displayName = displayName;
		this.lore = null;
		this.material = material;
		this.materialType = 0;
	}

	public ItemCreator(final Material material, final byte materialType) {
		this.displayName = null;
		this.lore = null;
		this.material = material;
		this.materialType = materialType;
	}

	public ItemCreator(final Material material) {
		this.displayName = null;
		this.lore = null;
		this.material = material;
		this.materialType = 0;
	}

	public ItemStack createItem(final boolean unbreakable, final boolean glowing) {
		ItemStack item = new ItemStack(material);
		if (materialType != 0) {
			final MaterialData data = item.getData();
			data.setData(materialType);
			item = data.toItemStack(1);
		}
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		if (unbreakable) {
			itemMeta.spigot().setUnbreakable(true);
			itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		}
		if (glowing) {
			itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (displayName != null) {
			itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		}
		if (lore != null) {
			for (int i = 0; i < lore.size(); i++) {
				lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
			}
			itemMeta.setLore(lore);
		}
		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack createItem(final boolean unbreakable, final boolean glowing, final int amount) {
		ItemStack item = new ItemStack(material);
		item.setAmount(amount);
		if (materialType != 0) {
			final MaterialData data = item.getData();
			data.setData(materialType);
			item = data.toItemStack(amount);
		}
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		if (unbreakable) {
			itemMeta.spigot().setUnbreakable(true);
			itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		}
		if (glowing) {
			itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (displayName != null) {
			itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		}
		if (lore != null) {
			for (int i = 0; i < lore.size(); i++) {
				lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
			}
			itemMeta.setLore(lore);
		}
		item.setItemMeta(itemMeta);
		return item;
	}

	public static ItemStack createFromItem(final ItemStack item, final String displayName, final ArrayList<String> lore, final boolean unbreakable, final boolean glowing) {
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		if (unbreakable) {
			itemMeta.spigot().setUnbreakable(true);
			itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		}
		if (glowing) {
			itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (displayName != null) {
			itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		}
		if (lore != null) {
			for (int i = 0; i < lore.size(); i++) {
				lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
			}
			itemMeta.setLore(lore);
		}
		item.setItemMeta(itemMeta);
		return item;
	}

	public static ItemStack createFromItem(final ItemStack item, final String displayName, final ArrayList<String> lore, final boolean unbreakable, final boolean glowing, final int amount) {
		final ItemMeta itemMeta = item.getItemMeta();
		item.setAmount(amount);
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		if (unbreakable) {
			itemMeta.spigot().setUnbreakable(true);
			itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		}
		if (glowing) {
			itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (displayName != null) {
			itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		}
		if (lore != null) {
			for (int i = 0; i < lore.size(); i++) {
				lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
			}
			itemMeta.setLore(lore);
		}
		item.setItemMeta(itemMeta);
		return item;
	}
}
