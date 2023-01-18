package me.stitchie.legacypit.world.spawn;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.HashMap;
import java.util.UUID;

public enum NPCSpawner {

	INSTANCE;

	NPCSpawner() {
	}

	private final World world = Bukkit.getWorld("LegacyPit");
	private final Location itemShopNPCLocation = new Location(world, 2.5, 125, 13.5, 180, 0);
	private final Location upgradeShopNPCLocation = new Location(world, - 1.5, 125, 13.5, 180, 0);
	private final Location contractNPCLocation = new Location(world, 1.5, 4, 24.5, 0, 0);
	private final Location prestigeNPCLocation = new Location(world, 0.5, 126, - 14.5, 0, 0);
	private final HashMap<UUID, NPCType> npcUUIDs = new HashMap<>();

	public void spawnNPCs() {

		final HashMap<UUID, NPCType> npcMap = new HashMap<>();

		System.out.println("Spawned Villagers.");

		itemShopNPCLocation.getChunk().load();
		upgradeShopNPCLocation.getChunk().load();
		contractNPCLocation.getChunk().load();
		prestigeNPCLocation.getChunk().load();

		final Villager itemShopNPC = (Villager) world.spawnEntity(itemShopNPCLocation, EntityType.VILLAGER);
		itemShopNPC.setProfession(Villager.Profession.BLACKSMITH);
		setVillagerNBT(itemShopNPC);
		npcMap.put(itemShopNPC.getUniqueId(), NPCType.ITEMSHOP);

		final Villager upgradeShopNPC = (Villager) world.spawnEntity(upgradeShopNPCLocation, EntityType.VILLAGER);
		upgradeShopNPC.setProfession(Villager.Profession.FARMER);
		setVillagerNBT(upgradeShopNPC);
		npcMap.put(upgradeShopNPC.getUniqueId(), NPCType.UPGRADESHOP);

		final Villager contractShopNPC = (Villager) world.spawnEntity(contractNPCLocation, EntityType.VILLAGER);
		setVillagerNBT(contractShopNPC);
		npcMap.put(contractShopNPC.getUniqueId(), NPCType.CONTRACTS);

		final Villager prestigeShopNPC = (Villager) world.spawnEntity(prestigeNPCLocation, EntityType.VILLAGER);
		prestigeShopNPC.setProfession(Villager.Profession.PRIEST);
		setVillagerNBT(prestigeShopNPC);
		npcMap.put(prestigeShopNPC.getUniqueId(), NPCType.PRESTIGE);

		itemShopNPCLocation.getChunk().unload(true);
		upgradeShopNPCLocation.getChunk().unload(true);
		contractNPCLocation.getChunk().unload(true);
		prestigeNPCLocation.getChunk().unload(true);
	}

	public HashMap<UUID, NPCType> getNPCUUIDs() {
		return npcUUIDs;
	}

	private void setVillagerNBT(final Villager villager) {
		final net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) villager).getHandle();
		nmsEntity.b(true);
		final NBTTagCompound tag = new NBTTagCompound();
		nmsEntity.c(tag);
		tag.setBoolean("NoAI", true);
		tag.setBoolean("PersistenceRequired", true);
		final EntityLiving entityLiving = (EntityLiving) nmsEntity;
		entityLiving.a(tag);
	}
}
