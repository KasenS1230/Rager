package me.testing.testing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class InstantBreakListener implements Listener {

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();

        if (!GameManager.gamePlayers.contains(player)) return;
        if (itemInHand.getType() != Material.STONE_AXE) return;

        event.setInstaBreak(true);
    }
}