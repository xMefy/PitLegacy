package me.stitchie.legacypit.player;

import me.stitchie.legacypit.items.RespawnItems;
import me.stitchie.legacypit.util.PitUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.Random;

public class PlayerRespawnHandler implements Listener {

	private final Map<Player, PlayerCache> playerData;
	private final Map<Player, Integer> combatTagData;
	private final Vector noMotion = new Vector(0, 0, 0);
	private final RespawnItems respawnItems;
	private final Random random = new Random();

	public PlayerRespawnHandler(final Map<Player, PlayerCache> playerData, final Map<Player, Integer> combatTagData, final RespawnItems respawnItems) {
		this.playerData = playerData;
		this.combatTagData = combatTagData;
		this.respawnItems = respawnItems;
	}

	@EventHandler
	private void onRespawn(final PlayerDeathEvent event) {

		final Player player = event.getEntity();

		final PlayerCache killedData = playerData.get(player);

		// Resetting killstreak of player that died back to zero
		killedData.setKillstreak(0);

		combatTagData.remove(player);

		player.setHealth(player.getMaxHealth());
		player.setVelocity(noMotion);

		event.setDeathMessage(null);
		event.setKeepInventory(true);

		PitUtility.sendTitle(player, "§4§lYOU DIED");

		if (player.getKiller() != null) {
			player.sendMessage("§c§lDEATH! §7by " + player.getKiller().getDisplayName());
		} else {
			player.sendMessage("§c§lDEATH! §7by unknown causes");
		}

		// Remove items from inventory.
		removeItemsonRespawn(player);

		// Add base items back on respawn.
		setRespawnItems(player);

	}

	@EventHandler
	private void onErrorRespawn(final PlayerRespawnEvent event) {
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 99999999, 0), true);
	}

	private void setRespawnItems(final Player player) {

		final ItemStack[] ironPieces = {respawnItems.getIronchestplate(), respawnItems.getIronleggings(), respawnItems.getIronboots()};
		final ItemStack[] starterArmor = {respawnItems.getChainchestplate(), respawnItems.getChainleggings(), respawnItems.getChainboots()};

		final int randomslot = random.nextInt(3);

		starterArmor[randomslot] = ironPieces[randomslot];

		final PlayerInventory playerInventory = player.getInventory();

		playerInventory.setChestplate(starterArmor[0]);
		playerInventory.setLeggings(starterArmor[1]);
		playerInventory.setBoots(starterArmor[2]);

		playerInventory.addItem(respawnItems.getIronsword());

		playerInventory.addItem(respawnItems.getBow());

		final ItemStack arrows = respawnItems.getArrow();
		arrows.setAmount(32);

		if (playerInventory.getItem(8) == null) {
			playerInventory.setItem(8, arrows);
		} else {
			playerInventory.addItem(arrows);
		}

	}

	private void removeItemsonRespawn(final Player player) {

		for (final ItemStack item : player.getInventory()) {
			if (item != null && respawnItems.getItemsLostonDeath().contains(item.getType())) {
				player.getInventory().remove(item.getType());
			}
		}
	}
}
