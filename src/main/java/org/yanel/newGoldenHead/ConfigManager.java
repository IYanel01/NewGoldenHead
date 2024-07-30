package org.yanel.newGoldenHead;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private final JavaPlugin plugin;
    private FileConfiguration messagesConfig;
    private File messagesFile;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        saveDefaultMessagesConfig();
    }

    public void reloadMessagesConfig() {
        if (messagesFile == null) {
            messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getMessagesConfig() {
        if (messagesConfig == null) {
            reloadMessagesConfig();
        }
        return messagesConfig;
    }

    public void saveDefaultMessagesConfig() {
        if (messagesFile == null) {
            messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        }
        if (!messagesFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }
    }

    public File getMessagesFile() {
        if (messagesFile == null) {
            messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        }
        return messagesFile;
    }
}
