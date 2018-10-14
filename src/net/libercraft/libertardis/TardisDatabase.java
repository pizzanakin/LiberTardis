package net.libercraft.libertardis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;

import net.libercraft.libercore.Database;
import net.libercraft.libertardis.TardisManager.Tardis;

public class TardisDatabase extends Database {
	
	public TardisDatabase() {
		super();
	}

	@Override
	public List<String> getTables() {
		List<String> tables = new ArrayList<>();
		tables.add("tardises");
		return tables;
	}

	@Override
	public List<String> getColumns(String table) {
		List<String> columns = new ArrayList<>();
		switch (table) {
		case "tardises":
			columns.add("id INTEGER PRIMARY KEY AUTOINCREMENT");
			columns.add("uuid TEXT NOT NULL");
			columns.add("loc TEXT NOT NULL");
			break;
		default:
			return null;
		}
		return columns;
	}
	
	public List<Tardis> getTardises() {
		List<UUID> uuids = new ArrayList<>();
		List<Object> r = getList(this, "tardises", "uuid");
		if (r != null)
			for (Object o:r)
				uuids.add((UUID)o);
		else
			return null;
		
		List<Location> locs = new ArrayList<>();
		r = getList(this, "tardises", "loc");
		if (r != null)
			for (Object o:r) {
				String loc = (String) o;
				String[] cs = loc.split("-");
				int x = Integer.parseInt(cs[0]);
				int y = Integer.parseInt(cs[1]);
				int z = Integer.parseInt(cs[2]);
				World world = LiberTardis.get().getServer().getWorld(cs[3]);
				locs.add(new Location(world, x, y, z));
			}
		else
			return null;
		
		List<Tardis> tardises = new ArrayList<>();
		for (int i = 0; i < uuids.size(); i++) {
			tardises.add(new Tardis(uuids.get(i)));
			Tardis t = tardises.get(i);
			t.location = locs.get(i).getBlock();
		}
		return tardises;		
	}

	public void saveTardis(Tardis t) {
		String locString = t.location.getX() + "-" + t.location.getY() + "-" + t.location.getZ() + "-" + t.location.getWorld();
		update(this, "tardises", "loc", locString, "uuid", t.ownerUUID);
	}
}
