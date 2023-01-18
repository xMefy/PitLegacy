package me.stitchie.legacypit.player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@Getter
public class BountyHologram {

	private final Hologram hologram;
	private final Vector locationOffset;
	private final String bounty;
	private final Player player;
	private int currentTime = 0;

	public BountyHologram(final Hologram hologram, final Vector locationOffset, final int bounty, final Player player) {
		this.hologram = hologram;
		this.locationOffset = locationOffset;
		this.bounty = ChatColor.GOLD + "" + ChatColor.BOLD + bounty + "g";
		this.player = player;
		this.hologram.appendTextLine(this.bounty);
	}

	public void increaseTimer() {
		this.currentTime++;
	}

	public void delete() {
		this.hologram.delete();
	}
}
