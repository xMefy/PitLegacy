package me.stitchie.legacypit.world;

import me.stitchie.legacypit.player.PlayerCache;
import me.stitchie.legacypit.player.data.PlayerData;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class CoinPickup implements Listener {

	private final Map<Player, PlayerCache> playerData = PlayerData.INSTANCE.getPlayerData();

	@EventHandler
	private void onCoinPickup(final PlayerPickupItemEvent event) {

		final ItemStack item = event.getItem().getItemStack();

		if(item.getType().equals(Material.GOLD_INGOT)) {
			final Player player = event.getPlayer();
			final PlayerCache playerCache = playerData.get(player);
			final int amount = item.getAmount();
			final int goldamount = amount * 5;
			playerCache.addGold(goldamount);
			player.sendMessage("§6§lGOLD PICKUP! §7from the ground §6" + goldamount + "§6.00g");
			event.getItem().remove();
			event.setCancelled(true);
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 10);
		}
	}
}
