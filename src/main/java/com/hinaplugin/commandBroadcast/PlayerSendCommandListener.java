package com.hinaplugin.commandBroadcast;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerSendCommandListener implements Listener {

    @EventHandler
    public void onPlayerSendCommand(PlayerCommandPreprocessEvent event){
        final Player player = event.getPlayer();
        final String command = event.getMessage();

        for (final Player target : CommandBroadcast.plugin.getServer().getOnlinePlayers()){
            if (target.hasPermission("broadcast.send")){
                if (CommandBroadcast.config.getBoolean("players." + target.getName(), false)){
                    target.sendMessage(Component.text("[SendCommand] " + player.getName() + ": " + command).color(TextColor.color(150, 150, 150)).decorate(TextDecoration.ITALIC));
                }
            }
        }
    }
}
