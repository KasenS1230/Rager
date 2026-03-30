package me.testing.testing;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    public static List<Location> spawnPoints = new ArrayList<>();
    public static List<Location> lobbySpawnPoints = new ArrayList<>();
    private static JavaPlugin plugin;
    public static List<Player> gamePlayers = new ArrayList<>();
    public static boolean gameRunning = false;

    public static void init(JavaPlugin p) {
        plugin = p;
        loadSpawnPoints();
        loadLobbySpawnPoints();
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

    // Lobby spawn points
    public static void addLobbySpawnPoint(Location location) {
        lobbySpawnPoints.add(location);
        saveLobbySpawnPoints();
    }

    public static void saveLobbySpawnPoints() {
        FileConfiguration config = plugin.getConfig();
        config.set("lobbyspawnpoints", null);

        for (int i = 0; i < lobbySpawnPoints.size(); i++) {
            Location loc = lobbySpawnPoints.get(i);
            String path = "lobbyspawnpoints." + i + ".";

            config.set(path + "world", loc.getWorld().getName());
            config.set(path + "x", loc.getX());
            config.set(path + "y", loc.getY());
            config.set(path + "z", loc.getZ());
            config.set(path + "yaw", loc.getYaw());
            config.set(path + "pitch", loc.getPitch());
        }

        plugin.saveConfig();
    }

    public static void loadLobbySpawnPoints() {
        FileConfiguration config = plugin.getConfig();
        lobbySpawnPoints.clear();

        if (!config.contains("lobbyspawnpoints")) return;

        for (String key : config.getConfigurationSection("lobbyspawnpoints").getKeys(false)) {
            String path = "lobbyspawnpoints." + key + ".";

            World world = Bukkit.getWorld(config.getString(path + "world"));
            double x = config.getDouble(path + "x");
            double y = config.getDouble(path + "y");
            double z = config.getDouble(path + "z");
            float yaw = (float) config.getDouble(path + "yaw");
            float pitch = (float) config.getDouble(path + "pitch");

            lobbySpawnPoints.add(new Location(world, x, y, z, yaw, pitch));
        }
    }



    public static void joinGame(Player player) {
        gamePlayers.add(player);
        player.sendMessage(ChatColor.GREEN + "You joined the game!");
    }

    public static void startGame() {
        gameRunning = true;



        for (int i = 0; i < gamePlayers.size(); i++) {
            gamePlayers.get(i).setGameMode(GameMode.SURVIVAL);
            gamePlayers.get(i).teleport(spawnPoints.get(i));
            GameScoreboard.setScoreboard(gamePlayers.get(i));

            ItemStack axe = new ItemStack(Material.STONE_AXE);



            ItemMeta meta = axe.getItemMeta();
            meta.setUnbreakable(true);
            axe.setItemMeta(meta);


            gamePlayers.get(i).getInventory().addItem(axe);







        }



        Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "The game has started!");
    }



    public static void endGame() {
        gameRunning = false;

        for (Player player : gamePlayers) {
            player.setGameMode(GameMode.ADVENTURE);
        }

        gamePlayers.clear();





        BlockBreakListener.blocksBroken.clear();
        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "The game has ended!");
    }

}