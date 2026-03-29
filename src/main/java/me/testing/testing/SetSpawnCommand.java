package me.testing.testing;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        GameManager.addSpawnPoint(player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Spawn point #" + GameManager.spawnPoints.size() + " set!");
        return true;
    }
}