package org.yanel.newGoldenHead;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class NewGoldenHead extends JavaPlugin {
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Display the loading logo
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        // Save the default config files if they don't exist
        saveDefaultConfig();
        saveResource("messages.yml", false);

        // Initialize ConfigManager
        configManager = new ConfigManager(this);

        // Plugin startup logic
        String version = Bukkit.getBukkitVersion();
        if (version.startsWith("1.21")) {
            getLogger().info("Running on a modern version (1.21+), loading ModernHandler.");
            getCommand("newgoldenhead").setExecutor(new org.yanel.newGoldenHead.modern.GoldenHeadCommand(this));
        } else {
            getLogger().info("Running on a legacy version (1.20.6 and below), loading LegacyHandler.");
            getCommand("newgoldenhead").setExecutor(new org.yanel.newGoldenHead.legacy.GoldenHeadCommand(this));
        }

        // Check for updates
        UpdateChecker.init(this, 118488).requestUpdateCheck().whenComplete((result, e) -> {
            if (result.requiresUpdate()) {
                String updateMessage = ChatColor.RED + "An update is available! New version: " + result.getNewestVersion();
                console.sendMessage(updateMessage);
            }

            // Log the loading logo after checking the version
            console.sendMessage(LoadingLogo.getLogo(!result.requiresUpdate()));
        });
    }

    @Override
    public void onDisable() {
        // Save the config and messages files
        saveConfig();
        try {
            configManager.getMessagesConfig().save(configManager.getMessagesFile());
        } catch (IOException e) {
            getLogger().severe("Could not save messages.yml: " + e.getMessage());
        }
    }
}
