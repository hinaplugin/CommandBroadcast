package com.hinaplugin.commandBroadcast;

import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class CommandBroadcast extends JavaPlugin {

    public static CommandBroadcast plugin;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            plugin = this;

            final File configFile = new File(this.getDataFolder(), "config.yml");
            if (!configFile.exists()){
                this.saveDefaultConfig();
            }

            config = this.getConfig();

            this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
            this.getServer().getPluginManager().registerEvents(new PlayerSendCommandListener(), this);

            final PluginCommand command = this.getCommand("broadcast");
            if (command != null){
                command.setExecutor(new Commands());
            }
        }catch (Exception exception){
            exception.printStackTrace(new PrintWriter(new StringWriter()));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void save(){
        this.saveConfig();
        this.reloadConfig();
        config = this.getConfig();
    }
}
