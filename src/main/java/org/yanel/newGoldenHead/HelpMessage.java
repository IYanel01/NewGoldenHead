package org.yanel.newGoldenHead;

import net.md_5.bungee.api.ChatColor;

public class HelpMessage {

    public static String getHelpMessage(boolean isModern) {
        String version = "023 Beta"; // You can dynamically fetch this if needed
        String author = "Yanel";

        StringBuilder message = new StringBuilder();
        message.append(ChatColor.YELLOW).append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━").append("\n")
                .append("\n")
                .append(ChatColor.GOLD).append("                NewGoldenHead Help").append("\n")
                .append("\n")
                .append(ChatColor.GRAY).append("      goldenhead version: ").append(ChatColor.GREEN).append(version).append(ChatColor.GREEN).append(" ✔").append("\n")
                .append("\n")
                .append(ChatColor.GRAY).append("      Made With ").append(ChatColor.RED).append("❤").append(ChatColor.GRAY).append(" by ").append(author).append("\n")
                .append("\n")
                .append("\n")
                .append(ChatColor.GOLD).append("/ngh give - Gives you a Golden Head").append("\n")
                .append(ChatColor.GOLD).append("/ngh reload - Reloads the configuration").append("\n")
                .append("\n")
                .append("\n")
                .append(ChatColor.YELLOW).append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        return message.toString();
    }
}
