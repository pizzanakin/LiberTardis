package net.libercraft.libertardis;

import org.bukkit.ChatColor;

import net.libercraft.libercore.interfaces.Module;

public class LiberTardis extends Module {
	private static LiberTardis instance;
	
	private TardisDatabase td;
	private TardisManager tm;

	@Override
	public ChatColor colour() {
		return null;
	}

	@Override
	public void onActivate() {
		instance = this;
		
		td = new TardisDatabase();
		tm = new TardisManager();
		
		getCommand("tardis").setExecutor(new Command());
	}

	@Override
	public void onClose() {
	}
	
	public static LiberTardis get() {
		return instance;
	}
	
	public static TardisDatabase getTD() {
		return instance.td;
	}
	
	public static TardisManager getTM() {
		return instance.tm;
	}

}
