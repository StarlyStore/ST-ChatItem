package net.starly.chatitem.command;

import net.starly.chatitem.ChatItemMain;
import net.starly.chatitem.context.MessageContent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ChatItemCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!(sender instanceof Player)) return false;

        if (args.length == 0) {
            player.sendMessage(MessageContent.getInstance().getMessageAfterPrefix(MessageContent.MessageType.ERROR, "wrongCommand"));
            return false;
        }

        if (args[0].equals("리로드")) {

            if (!player.hasPermission("starly.chatitem.reload")) {
                player.sendMessage(MessageContent.getInstance().getMessageAfterPrefix(MessageContent.MessageType.ERROR, "noPermission"));
                return false;
            }

            if (args.length != 1) {
                player.sendMessage(MessageContent.getInstance().getMessageAfterPrefix(MessageContent.MessageType.ERROR, "wrongCommand"));
                return false;
            }

            Plugin plugin = ChatItemMain.getInstance();
            plugin.reloadConfig();
            MessageContent.getInstance().initializing(plugin.getConfig());
            player.sendMessage(MessageContent.getInstance().getMessageAfterPrefix(MessageContent.MessageType.NORMAL, "reloadConfig"));
            return true;
        }
        return false;
    }
}
