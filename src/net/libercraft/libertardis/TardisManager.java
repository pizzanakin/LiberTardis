package net.libercraft.libertardis;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.libercraft.libercore.interfaces.Loadable;
import net.libercraft.libercore.interfaces.Updatable;

public class TardisManager implements Loadable, Updatable {
	public static List<Tardis> tardises;
	
	public TardisManager() {
		registerLoadable();
	}
	
	@Override
	public void load() {
		tardises = LiberTardis.getTD().getTardises();
	}

	@Override
	public void close() {
		for (Tardis t:tardises)
			LiberTardis.getTD().saveTardis(t);
	}

	@Override
	public void update() {
		for (Tardis t:tardises)
			t.update();
	}
	
	public boolean buildTardis(Player player) {
		for (Tardis t:tardises)
			if (t.ownerUUID.equals(player.getUniqueId()))
				return false;
		
		Location loc = player.getLocation();
		
		loc.clone().add(new Vector(0, -1, 0)).getBlock().setType(Material.DIAMOND_BLOCK);
		loc.clone().add(new Vector(0, 2, 0)).getBlock().setType(Material.DIAMOND_BLOCK);
		
		Tardis t = new Tardis(player.getUniqueId());
		t.location = player.getLocation();
		tardises.add(t);
		return true;
	}
	
	public static Tardis getFromUUID(UUID uuid) {
		for (Tardis t:tardises)
			if (t.ownerUUID == uuid)
				return t;
		return null;
	}

	
	public static class Tardis {
		public UUID ownerUUID;
		public Location location;
		
		public Tardis(UUID ownerUUID) {
			this.ownerUUID = ownerUUID;
		}
		
		private void update() {
			
		}
		
		public void teleport(Location location) {
			this.location = location;
			// TODO move TARDIS display
		}
	}
}
