package me.testing.testing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    public static List<Location> spawnPoints = new ArrayList<>();
    private static JavaPlugin plugin;
    public static List<Player> gamePlayers = new ArrayList<>();
    public static boolean gameRunning = false;

    public static void init(JavaPlugin p) {
        plugin = p;
        loadSpawnPoints();
    }

    public static void addSpawnPoint(Location location) {
        spawnPoints.add(location);
        saveSpawnPoints();
    }

    public static void saveSpawnPoints() {
        FileConfiguration config = plugin.getConfig();

        // Clear old spawn points
        config.set("spawnpoints", null);

        for (int i = 0; i < spawnPoints.size(); i++) {
            Location loc = spawnPoints.get(i);
            String path = "spawnpoints." + i + ".";

            config.set(path + "world", loc.getWorld().getName());
            config.set(path + "x", loc.getX());
            config.set(path + "y", loc.getY());
            config.set(path + "z", loc.getZ());
            config.set(path + "yaw", loc.getYaw());
            config.set(path + "pitch", loc.getPitch());
        }

        plugin.saveConfig();
    }

    public static void loadSpawnPoints() {
        FileConfiguration config = plugin.getConfig();
        spawnPoints.clear();

        if (!config.contains("spawnpoints")) return;

        for (String key : config.getConfigurationSection("spawnpoints").getKeys(false)) {
            String path = "spawnpoints." + key + ".";

            World world = Bukkit.getWorld(config.getString(path + "world"));
            double x = config.getDouble(path + "x");
            double y = config.getDouble(path + "y");
            double z = config.getDouble(path + "z");
            float yaw = (float) config.getDouble(path + "yaw");
            float pitch = (float) config.getDouble(path + "pitch");

            spawnPoints.add(new Location(world, x, y, z, yaw, pitch));
        }
    }


    public static void joinGame(Player player) {
        gamePlayers.add(player);
        player.sendMessage(ChatColor.GREEN + "You joined the game!");
    }

    public static void startGame() {
        gameRunning = true;

        for (int i = 0; i < gamePlayers.size(); i++) {
            gamePlayers.get(i).teleport(spawnPoints.get(i));
            GameScoreboard.setScoreboard(gamePlayers.get(i));
        }

        Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "The game has started!");
    }

    public static void endGame() {
        gameRunning = false;
        gamePlayers.clear();
        BlockBreakListener.blocksBroken.clear();
        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "The game has ended!");
    }

}