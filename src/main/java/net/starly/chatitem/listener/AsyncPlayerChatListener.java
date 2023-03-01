package net.starly.chatitem.listener;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.starly.chatitem.context.MessageContent;
import net.starly.core.jb.util.ItemStackNameUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (message.contains("[i]")) {

            if (itemStack == null || itemStack.getType().equals(Material.AIR)) {

                event.setMessage(message.replace("[i]", MessageContent.getInstance().getMessage(MessageContent.MessageType.NORMAL, "noItemInHand")
                        .replace("{item}", ItemStackNameUtil.getItemName(itemStack))
                        .replace("{player}", player.getDisplayName())));

                return;
            }
            event.setMessage(message.replace("[i]", MessageContent.getInstance().getMessage(MessageContent.MessageType.NORMAL, "itemInHand")
                    .replace("{item}", ItemStackNameUtil.getItemName(itemStack))));
        }
    }
}
