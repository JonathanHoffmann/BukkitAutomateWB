package me.Jonnyfant.automateWB;

import org.bukkit.plugin.java.JavaPlugin;

public class automateWB extends JavaPlugin {
    @Override
    public void onEnable() {
        loadConfig();
        getConfig().set("active", true);
        getServer().getPluginManager().registerEvents(new AFKListener(this),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this),this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this),this);
    }

    private void loadConfig() {
        getConfig().addDefault("active", true);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
