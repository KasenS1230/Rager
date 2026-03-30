package me.testing.testing;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Test extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        GameManager.init(this);
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("join").setExecutor(new JoinCommand());
        getCommand("start").setExecutor(new StartCommand());
        getCommand("end").setExecutor(new EndCommand());
        getCommand("setlobbyspawn").setExecutor(new SetLobbySpawnCommand());
        getServer().getPluginManager().registerEvents(new InstantBreakListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
