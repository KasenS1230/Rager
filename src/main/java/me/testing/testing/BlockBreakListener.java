package me.testing.testing;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    public static HashMap<UUID, Integer> blocksBroken = new HashMap<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        //Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();

        if (itemInHand.getType() != Material.STONE_AXE) return;

        if (!GameManager.gamePlayers.contains(event.getPlayer())) return;

        event.setDropItems(false);
        event.getBlock().setType(Material.AIR);



        // Increment counter
        blocksBroken.put(uuid, blocksBroken.getOrDefault(uuid, 0) + 1);

        // Update their scoreboard
        GameScoreboard.updateScoreboard(event.getPlayer());
    }
}