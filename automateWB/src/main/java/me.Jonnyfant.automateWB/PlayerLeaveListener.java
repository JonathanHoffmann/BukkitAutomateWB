package me.Jonnyfant.automateWB;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerLeaveListener implements Listener {
    automateWB plugin;
    public PlayerLeaveListener(automateWB in) {
        plugin=in;
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent playerQuitEvent) throws InterruptedException {
        final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.size()==1)
        {
            plugin.reloadConfig();
            if (plugin.getConfig().getBoolean("active") == false) {
                plugin.getConfig().set("active", true);
                plugin.saveConfig();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb fill pause");
                //start
                Bukkit.broadcastMessage("Nobody is left on the server. Rendering more Chunks now. Lag expected.");
            }
        }
        Boolean allafk=true;
        for(Player p:players)
        {
            if(p.getPlayerListName().contains("[AFK]") || p.getName().equals(playerQuitEvent.getPlayer().getName()))
            {
            }
            else
            {
                allafk = false;
            }
            if(allafk) {
                if (plugin.getConfig().getBoolean("active") == false) {
                    plugin.getConfig().set("active", true);
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb fill pause");
                    Bukkit.broadcastMessage("All remaining players are AFK. Generating more Chunks now. Hold on, boys, this is goin' to be laggy. It will stop when somebody is not AFK.");
                    //start
                }
            }
        }
    }
}
