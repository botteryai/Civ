package com.untamedears.JukeAlert.command.commands;

import static com.untamedears.JukeAlert.util.Utility.findTargetedOwnedSnitch;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.untamedears.JukeAlert.command.PlayerCommand;
import com.untamedears.JukeAlert.model.Snitch;
import com.untamedears.JukeAlert.tasks.GetSnitchInfoPlayerTask;

public class InfoCommand extends PlayerCommand {

    public InfoCommand() {
        super("Info");
        setDescription("Displays information from a Snitch");
        setUsage("/jainfo <page number>");
        setArgumentRange(0, 1);
        setIdentifier("jainfo");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            int offset = 1;
            if (args.length > 0) {
                offset = Integer.parseInt(args[0]);
            }
            if (offset < 1) {
                offset = 1;
            }
            Snitch snitch = findTargetedOwnedSnitch((Player) sender);
            if (snitch != null) {
                sendLog(sender, snitch, offset);
            } else {
                sender.sendMessage(ChatColor.RED + " You do not own any snitches nearby!");
            }
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + " You do not own any snitches nearby!");
            return false;
        }
    }

    private void sendLog(CommandSender sender, Snitch snitch, int offset) {
        Player player = (Player) sender;
        GetSnitchInfoPlayerTask task = new GetSnitchInfoPlayerTask(plugin, snitch.getId(), offset, player);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, task);

    }
}
