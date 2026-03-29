package me.testing.testing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.HashMap;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    public static HashMap<UUID, Integer> blocksBroken = new HashMap<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        //Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();

        // Increment counter
        blocksBroken.put(uuid, blocksBroken.getOrDefault(uuid, 0) + 1);

        // Update their scoreboard
        GameScoreboard.updateScoreboard(event.getPlayer());
    }
}