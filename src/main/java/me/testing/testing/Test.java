package me.testing.testing;

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
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
