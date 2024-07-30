package org.yanel.newGoldenHead.modern;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ModernHandler {
    private final JavaPlugin plugin;

    public ModernHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        plugin.getCommand("newgoldenhead").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Only players can use this command.");
                    return true;
                }

                Player player = (Player) sender;
                if (args.length == 1 && args[0].equalsIgnoreCase("give")) {
                    ItemStack goldenHead = createGoldenHead();
                    player.getInventory().addItem(goldenHead);
                    player.sendMessage("You have been given a Golden Head.");
                }
                return true;
            }
        });
    }

    private ItemStack createGoldenHead() {
        // Create the golden head item using modern methods
        return null; // Add your item creation logic here
    }
}
