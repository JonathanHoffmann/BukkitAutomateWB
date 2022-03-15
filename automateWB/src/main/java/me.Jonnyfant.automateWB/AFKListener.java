package me.Jonnyfant.automateWB;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.BroadcastMessageEvent;

import java.util.Collection;

public class AFKListener implements Listener {
    automateWB plugin;
    public AFKListener(automateWB in) {
        plugin=in;
    }
    @EventHandler
    public void onAFK(BroadcastMessageEvent broadcastMessageEvent) {
        String searchAFK = "is now AFK";
        String searchStopAFK = "but is back now";
        if (broadcastMessageEvent.getMessage().contains(searchAFK)) {
            final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            boolean allAFK = true;
            for (Player p : players) {
                if (p.getPlayerListName().contains("[AFK]")) {

                } else {
                    allAFK = false;
                }
            }
            if (allAFK) {
                if (plugin.getConfig().getBoolean("active") == false) {
                    plugin.getConfig().set("active", true);
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb fill pause");
                    Bukkit.broadcastMessage("All Players are AFK. Generating more Chunks now. Hold on, boys, this is goin' to be laggy. It will stop when somebody is not AFK.");
                    //start
                }
            }
        } else {
            if (broadcastMessageEvent.getMessage().contains(searchStopAFK)) {
                if (plugin.getConfig().getBoolean("active")) {
                    plugin.getConfig().set("active", false);
                    plugin.saveConfig();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb fill pause");
                    Bukkit.broadcastMessage("Player came back from AFK. Stopping generating more Chunks now. Lag should disappear in 10 seconds.");
                    //stop
                }
            }
        }
    }
}
