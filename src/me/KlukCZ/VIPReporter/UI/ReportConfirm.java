package me.KlukCZ.VIPReporter.UI;

import me.KlukCZ.VIPReporter.Commands;
import me.KlukCZ.VIPReporter.Configurations.ConfigManager;
import me.KlukCZ.VIPReporter.Utils.GUIConstructor;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportConfirm {

    public static Inventory inventory;
    public static String inventory_name;
    public static int rows = 6;
    public static int size = rows * 9;

    public static void init(){
        inventory_name = GUIConstructor.chat("§9§lReport log");

        inventory = Bukkit.createInventory(null, size);
    }

    public static Inventory GUI (Player player){
        Inventory toReturn = Bukkit.createInventory(null, size, inventory_name);
        Player skullOwner = Bukkit.getPlayer(Commands.name);

        GUIConstructor.createItem(inventory, "GREEN_TERRACOTTA", 1, 39, "&2&lConfirm", " ");
        GUIConstructor.createItem(inventory, "RED_TERRACOTTA", 1, 43, "&c&lCancel", " ");
        GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 23, "&9&lReason", "&2&l" + Commands.reason);
        GUIConstructor.createItemSkull(inventory, "PLAYER_HEAD", skullOwner.getName(), 1, 5, "§9§l" + Commands.name, "");

        toReturn.setContents(inventory.getContents());
        return toReturn;
    }

    public static void clicked(Player player, int slot, ItemStack clicked, Inventory inventory){
        ConfigManager configManager = new ConfigManager();

        try {
            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(GUIConstructor.chat("&2&lConfirm"))) {
                Player reported = Bukkit.getPlayer(Commands.name);
                /*if (reported.hasPermission("vipreporter.admin")){
                    player.sendMessage("§6§lVIPReporter> §9§l" + Commands.name + "§7 cannot be reported!");
                    player.closeInventory();
                    return;
                }*/
                player.sendMessage("§6§lVIPReporter> §9§l" + Commands.name + "§7 has been reported successfully!");
                player.closeInventory();
                for (Player online : Bukkit.getOnlinePlayers()){
                    if (online.hasPermission("vipreporter.admin")){
                        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{\"text\":\"&6&lVIPReporter> &7New report has been submitted! &9&lClick here to show!\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/checkreport %PLAYER%\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"&5&lClick here to show the latest report log.\"}]}}}").replace("%PLAYER%", reported.getName()));
                        PacketPlayOutChat chat = new PacketPlayOutChat(component);
                        ((CraftPlayer) online).getHandle().playerConnection.sendPacket(chat);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm.dd.yy-hh:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        Player reporter = Bukkit.getPlayer(Commands.name);

                        configManager.setup();
                        configManager.getDatabase().set(reported.getUniqueId() + ".Name", reported.getName());
                        configManager.getDatabase().set(reported.getUniqueId() + ".UUID", reported.getUniqueId().toString());
                        configManager.getDatabase().set(reported.getUniqueId() + ".Reporter", Commands.reporter.getName());
                        configManager.getDatabase().set(reported.getUniqueId() + ".Server", Commands.reporter.getServer().getServerName());
                        configManager.getDatabase().set(reported.getUniqueId() + ".Reason", Commands.reason);
                        configManager.getDatabase().set(reported.getUniqueId() + ".World", Commands.reporter.getWorld().getName());
                        configManager.getDatabase().set(reported.getUniqueId() + ".Time", formatter.format(now));
                        if (reporter.isOp()){
                            configManager.getDatabase().set(reported.getUniqueId() + ".Opped", true);
                        }else{
                            configManager.getDatabase().set(reported.getUniqueId() + ".Opped", false);
                        }
                        configManager.loadDatabase();
                    }
                }
            }

            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(GUIConstructor.chat("&c&lCancel"))) {
                player.sendMessage("§6§lVIPReporter> §9§l" + Commands.name + "'s§7 report has been canceled!");
                player.closeInventory();
            }
        }catch (Exception e){

        }
    }
}
