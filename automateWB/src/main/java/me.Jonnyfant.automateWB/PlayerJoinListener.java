package me.Jonnyfant.automateWB;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;

public class PlayerJoinListener implements Listener {
    automateWB plugin;
    public PlayerJoinListener(automateWB in) {
        plugin=in;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent)
    {
        //Bukkit.broadcastMessage("Player Joined");
        plugin.reloadConfig();
        if(plugin.getConfig().getBoolean("active"))
        {
            //Bukkit.broadcastMessage("The world generation was active");
            plugin.getConfig().set("active", false);
            plugin.saveConfig();
            //deactivate
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"wb fill pause");
            Bukkit.broadcastMessage("Player joined. Stopping generating more Chunks now. Lag should disappear in 10 seconds.");
        }
    }
}
