package me.testing.testing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class GameScoreboard {

    public static void setScoreboard(Player player) {
        int blocks = BlockBreakListener.blocksBroken.getOrDefault(player.getUniqueId(), 0);

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective obj = board.registerNewObjective("game", "dummy", ChatColor.GOLD + "" + ChatColor.BOLD + "My Minigame");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore(ChatColor.WHITE + "Player: " + player.getName()).setScore(5);
        obj.getScore(ChatColor.WHITE + "Map: " + "MyMap").setScore(4);
        obj.getScore(" ").setScore(3);
        obj.getScore(ChatColor.WHITE + "Blocks Broken: " + blocks).setScore(2);
        obj.getScore("  ").setScore(1);
        obj.getScore(ChatColor.YELLOW + "mc.yourserver.com").setScore(0);

        player.setScoreboard(board);
    }

    public static void updateScoreboard(Player player) {
        // Just calls setScoreboard to rebuild with fresh values
        setScoreboard(player);
    }
}