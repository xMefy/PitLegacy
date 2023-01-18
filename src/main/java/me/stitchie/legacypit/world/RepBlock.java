package me.stitchie.legacypit.world;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Objects;

@Getter
public class RepBlock {

	private final Material material;
	private final Location location;

	public RepBlock(final Material material, final Location location) {
		this.material = material;
		this.location = location;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final RepBlock repBlock = (RepBlock) o;
		return location.equals(repBlock.location);
	}

	@Override
	public int hashCode() {
		return Objects.hash(location);
	}
}
