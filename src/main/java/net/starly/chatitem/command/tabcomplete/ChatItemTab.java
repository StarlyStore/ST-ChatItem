package net.starly.chatitem.command.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChatItemTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1) {
            if(player.hasPermission("starly.chatitem.reload")) return Arrays.asList("리로드");
        }
        return Collections.emptyList();
    }
}
