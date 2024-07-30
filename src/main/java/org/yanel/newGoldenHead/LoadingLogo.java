package org.yanel.newGoldenHead;

import net.md_5.bungee.api.ChatColor;

public class LoadingLogo {
    public static String getLogo(boolean isUpToDate) {
        return ChatColor.GOLD + "                                                                              \n" +
                ChatColor.GOLD + "  _   _                _____       _     _            _    _                _ \n" +
                ChatColor.YELLOW + " | \\ | |              / ____|     | |   | |          | |  | |              | |\n" +
                ChatColor.GOLD + " |  \\| | _____      _| |  __  ___ | | __| | ___ _ __ | |__| | ___  __ _  __| |\n" +
                ChatColor.YELLOW + " | . ` |/ _ \\ \\ /\\ / / | |_ |/ _ \\| |/ _` |/ _ \\ '_ \\|  __  |/ _ \\/ _` |/ _` |\n" +
                ChatColor.GOLD + " | |\\  |  __/\\ V  V /| |__| | (_) | | (_| |  __/ | | | |  | |  __/ (_| | (_| |\n" +
                ChatColor.YELLOW + " |_| \\_|\\___| \\_/\\_/  \\_____|\\___/|_|\\__,_|\\___|_| |_|_|  |_|\\___|\\__,_|\\__,_|\n" +
                ChatColor.RESET + "                                                                              \n" +
                ChatColor.RESET + "                                                                              \n" +
                (isUpToDate ? ChatColor.GREEN + "                         NewGoldenHead Plugin is up to date!    " : ChatColor.RED + "                         NewGoldenHead Plugin is outdated!    ");
    }
}
