package net.starly.chatitem;

import net.starly.chatitem.command.ChatItemCmd;
import net.starly.chatitem.command.tabcomplete.ChatItemTab;
import net.starly.chatitem.context.MessageContent;
import net.starly.chatitem.listener.AsyncPlayerChatListener;
import net.starly.core.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ChatItemMain extends JavaPlugin {

    private static ChatItemMain instance;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("ST-Core") == null) {
            Bukkit.getLogger().warning("[" + getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            Bukkit.getLogger().warning("[" + getName() + "] 다운로드 링크 : http://starly.kr/discord");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;
        new Metrics(this, 12345); // TODO: 수정

        if (!new File("config.yml").exists()) saveDefaultConfig();
        MessageContent.getInstance().initializing(getConfig());

        Bukkit.getPluginCommand("chatitem").setExecutor(new ChatItemCmd());
        Bukkit.getPluginCommand("chatitem").setTabCompleter(new ChatItemTab());

        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
    }

    public static ChatItemMain getInstance() {
        return instance;
    }
}
