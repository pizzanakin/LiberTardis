package net.libercraft.libertardis;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.libercraft.libertardis.TardisManager.Tardis;

public class Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		Player player = (Player) sender;
		
		if (args.length < 0)
			return false;
		
		switch (args[0]) {
		case "create":
			if (LiberTardis.getTM().buildTardis(player))
				player.sendMessage("TARDIS created!");
			else
				player.sendMessage("You already have a TARDIS!");
			break;
		case "call":
			Tardis t = TardisManager.getFromUUID(player.getUniqueId());
			t.teleport(player.getLocation());
			break;
		case "find":
			t = TardisManager.getFromUUID(player.getUniqueId());
			player.teleport(t.location);
			break;
		default:
			return false;
		}
		
		return true;
	}

}
