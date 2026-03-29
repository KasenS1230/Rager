package me.testing.testing;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Customize the join message
        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + event.getPlayer().getName() + (" has joined the server!"));
        GameScoreboard.setScoreboard(event.getPlayer());
        // Or suppress it entirely
        // event.setJoinMessage(null);
    }
}