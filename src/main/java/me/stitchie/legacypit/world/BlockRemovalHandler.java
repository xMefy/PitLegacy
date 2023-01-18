package me.stitchie.legacypit.world;

import io.netty.util.internal.ConcurrentSet;
import me.stitchie.legacypit.LegacyPit;
import me.stitchie.legacypit.player.PlayerCache;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockRemovalHandler implements Listener {

	private final LegacyPit plugin;
	private final World world = Bukkit.getWorld("LegacyPit");
	private final Map<Player, PlayerCache> playerData;
	private final ConcurrentHashMap<RepBlock, Long> blockMap = new ConcurrentHashMap<>();
	private final ConcurrentSet<RepBlock> blockRemovalSet = new ConcurrentSet<>();

	public BlockRemovalHandler(final LegacyPit plugin, final Map<Player, PlayerCache> playerData) {
		this.plugin = plugin;
		this.playerData = playerData;
		runAsyncScheduledBlockSort();
		removeBlocksSync();
	}

	@EventHandler(ignoreCancelled = true)
	private void onBlockPlace(final BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.OBSIDIAN) {
			if (event.getBlockReplacedState().getType() == Material.LONG_GRASS || event.getBlockReplacedState().getType() == Material.DOUBLE_PLANT) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Can't replace grass!");
				event.getPlayer().playSound(event.getBlock().getLocation(), Sound.VILLAGER_NO, 10, 1);
			} else {
				blockMap.put(new RepBlock(event.getBlockReplacedState().getType(), event.getBlock().getLocation()), System.currentTimeMillis() + 5000);
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	private void onBlockBreak(final BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.OBSIDIAN) {
			blockMap.remove(new RepBlock(event.getBlock().getType(), event.getBlock().getLocation()));
		}
	}

	private void runAsyncScheduledBlockSort() {
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this::sortBlockMap, 2L, 1L);
	}

	private void removeBlocksSync() {
		Bukkit.getScheduler().runTaskTimer(plugin, () -> {
			for (final RepBlock block : blockRemovalSet) {
				world.getBlockAt(block.getLocation()).setType(block.getMaterial());
				blockRemovalSet.remove(block);
			}
		}, 5L, 1L);
	}

	private void sortBlockMap() {
		final long currentTime = System.currentTimeMillis();
		for (final Map.Entry<RepBlock, Long> entry : blockMap.entrySet()) {
			final RepBlock repBlock = entry.getKey();
			final long time = entry.getValue();
			if (time < currentTime) {
				blockRemovalSet.add(repBlock);
				blockMap.remove(repBlock);
			}
		}
	}
}
