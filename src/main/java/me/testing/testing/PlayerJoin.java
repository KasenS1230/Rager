package me.testing.testing;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public class PlayerJoin implements Listener {

    public static List<Player> waitPlayers = new ArrayList<>();


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        waitPlayers.add(player);
        int index = PlayerJoin.waitPlayers.size() % GameManager.lobbySpawnPoints.size();
        player.teleport(GameManager.lobbySpawnPoints.get(index));
        player.setGameMode(GameMode.ADVENTURE);



    }




}
