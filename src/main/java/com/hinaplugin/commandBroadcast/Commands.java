package com.hinaplugin.commandBroadcast;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            final Player player = (Player) commandSender;
            if (player.hasPermission("broadcast.command")){
                if (strings.length == 1){
                    final boolean set = strings[0].equalsIgnoreCase("true");
                    CommandBroadcast.config.set("players." + player.getName(), set);
                    CommandBroadcast.plugin.save();
                    if (set){
                        player.sendMessage(Component.text("コマンド通知を有効に設定しました．").color(TextColor.color(75, 255, 75)));
                    }else {
                        player.sendMessage(Component.text("コマンド通知を無効に設定しました．").color(TextColor.color(75, 255, 75)));
                    }
                }else {
                    player.sendMessage(Component.text("usage: /broadcast <true|false>").color(TextColor.color(255, 75, 75)));
                }
            }else {
                player.sendMessage(Component.text("コマンドの実行権限がありません．").color(TextColor.color(255, 75, 75)));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        final List<String> complete = Lists.newArrayList();
        if (commandSender instanceof Player){
            final Player player = (Player) commandSender;
            if (player.hasPermission("broadcast.command")){
                if (strings.length == 0){
                    complete.add("true");
                    complete.add("false");
                }else {
                    if (strings[0].isEmpty()){
                        complete.add("true");
                        complete.add("false");
                    }else if ("true".startsWith(strings[0])){
                        complete.add("true");
                    }else if ("false".startsWith(strings[0])){
                        complete.add("false");
                    }
                }
            }
        }
        return complete;
    }
}
