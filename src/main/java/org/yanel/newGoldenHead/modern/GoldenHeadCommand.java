package org.yanel.newGoldenHead.modern;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.yanel.newGoldenHead.ConfigManager;
import org.yanel.newGoldenHead.HelpMessage;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class GoldenHeadCommand implements CommandExecutor, Listener {
    private final JavaPlugin plugin;
    private final ConfigManager configManager;

    public GoldenHeadCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configManager = new ConfigManager(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(HelpMessage.getHelpMessage(true));
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!(sender instanceof Player) || sender.hasPermission("newgoldenhead.reload")) {
                plugin.reloadConfig();
                configManager.reloadMessagesConfig();
                sender.sendMessage(ChatColor.of("#00FF00") + "Configuration reloaded successfully.");
            } else {
                sender.sendMessage(ChatColor.of("#FF0000") + "You do not have permission to use this command.");
            }
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.of("#FF0000") + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (args[0].equalsIgnoreCase("give")) {
            int amount = 1;
            if (args.length == 2) {
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Invalid amount. Please enter a number.");
                    return true;
                }
            }
            ItemStack goldenHead = createGoldenHead(amount);
            player.getInventory().addItem(goldenHead);
            player.sendMessage(ChatColor.of("#FFFF00") + "You have been given " + amount + " Golden Head(s).");
        }
        return true;
    }

    private ItemStack createGoldenHead(int amount) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setDisplayName("Golden Head");
        meta.setLore(Arrays.asList("A magical golden head"));

        GameProfile profile = new GameProfile(UUID.randomUUID(), "GoldenHead");
        String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGFiZDcwM2U1YjhjODhkNGIxZmNmYTk0YTkzNmEwZDZhNGY2YWJhNDQ1Njk2NjNkMzM5MWQ0ODgzMjIzYzUifX19";
        profile.getProperties().put("textures", new Property("textures", base64));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        head.setItemMeta(meta);
        return head;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand != null && itemInHand.getType() == Material.PLAYER_HEAD) {
            SkullMeta meta = (SkullMeta) itemInHand.getItemMeta();
            if (meta != null && "Golden Head".equals(meta.getDisplayName())) {
                int healSpeed = plugin.getConfig().getInt("healing-speed", 1);
                int healTime = plugin.getConfig().getInt("healing-time", 10);
                boolean enableSpeedEffect = plugin.getConfig().getBoolean("enable-speed-effect", true);

                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, healTime * 20, healSpeed - 1));
                if (enableSpeedEffect) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, healTime * 20, 1)); // Speed level 2 (index 1)
                }
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1.0F, 1.0F);

                // Decrease the item amount by 1
                if (itemInHand.getAmount() > 1) {
                    itemInHand.setAmount(itemInHand.getAmount() - 1);
                } else {
                    player.getInventory().setItemInMainHand(null); // Remove the item after use if only one is left
                }
            }
        }
    }
}
