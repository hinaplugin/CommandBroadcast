package com.hinaplugin.commandBroadcast;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();

        if (CommandBroadcast.config.get("players." + player.getName()) == null){
            CommandBroadcast.config.set("players." + player.getName(), false);
            CommandBroadcast.plugin.save();
        }
    }
}
