package me.KlukCZ.VIPReporter.UI;

import me.KlukCZ.VIPReporter.Commands;
import me.KlukCZ.VIPReporter.Configurations.ConfigManager;
import me.KlukCZ.VIPReporter.Utils.GUIConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminReport {

    public static Inventory inventory;
    public static String inventory_name;
    public static int rows = 6;
    public static int size = rows * 9;

    public static void init(){
        inventory_name = GUIConstructor.chat("§9§lReport log");

        inventory = Bukkit.createInventory(null, size);
    }

    public static Inventory GUI (Player player){
        ConfigManager configManager = new ConfigManager();
        configManager.setup();
        Inventory toReturn = Bukkit.createInventory(null, size, inventory_name);
        Player skullOwner = Bukkit.getPlayer(configManager.getDatabase().getString(Commands.configSelect.getUniqueId().toString() + ".Name"));

        GUIConstructor.createItemSkull(inventory, "PLAYER_HEAD", skullOwner.getName(), 1, 5, "§9§l" + skullOwner.getName());
        GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 20, "&9&lName", "&2&l" + configManager.getDatabase().get(Commands.configSelect.getUniqueId() + ".Name"));
        GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 22, "&9&lUUID", "&2&lClick to show UUID");
        GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 24, "&9&lReporter", "&2&l" + configManager.getDatabase().get(Commands.configSelect.getUniqueId() + ".Reporter"));
        GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 26, "&9&lServer", "&2&l" + configManager.getDatabase().get(Commands.configSelect.getUniqueId() + ".Server"));
        GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 29, "&9&lReason", "&2&l" + configManager.getDatabase().get(Commands.configSelect.getUniqueId() + ".Reason"));
        GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 31, "&9&lWorld", "&2&l" + configManager.getDatabase().get(Commands.configSelect.getUniqueId() + ".World"));
        GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 33, "&9&lTime", "&2&l" + configManager.getDatabase().get(Commands.configSelect.getUniqueId() + ".Time"));
        if (skullOwner.isOp()) {
            GUIConstructor.createItem(inventory, "LIME_DYE", 1, 35, "&9&lOpped", "&2&lTrue");
        }else{
            GUIConstructor.createItem(inventory, "GRAY_DYE", 1, 35, "&9&lOpped", "&2&lFalse");
        }
        GUIConstructor.createItem(inventory, "GREEN_TERRACOTTA", 1, 48, "&2&lInvestigate", " ");
        GUIConstructor.createItem(inventory, "YELLOW_TERRACOTTA", 1, 50, "&e&lIgnore", " ");
        GUIConstructor.createItem(inventory, "RED_TERRACOTTA", 1, 52, "&c&lClose", " ");

        toReturn.setContents(inventory.getContents());
        return toReturn;
    }

    public static void clicked(Player player, int slot, ItemStack clicked, Inventory inventory){
        try{
            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(GUIConstructor.chat("&9&lUUID"))) {
                ConfigManager configManager = new ConfigManager();
                configManager.setup();

                player.sendMessage("§9§l" + configManager.getDatabase().get(Commands.configSelect.getUniqueId() + ".UUID"));
            }

            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(GUIConstructor.chat("&2&lInvestigate"))) {
                ConfigManager configManager = new ConfigManager();

                for (Player online : Bukkit.getOnlinePlayers()){
                    if (online.hasPermission("vipreporter.admin")){
                        online.sendMessage("§6§lVIPReporter> §9§l" + player.getName() + "§7 is investigating " + Commands.configSelect.getName() + "'s §7report!");
                        online.closeInventory();
                    }
                }
                configManager.setup();
                configManager.getDatabase().set(Commands.configSelect.getUniqueId().toString(), null);
                configManager.loadDatabase();
            }

            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(GUIConstructor.chat("&e&lIgnore"))) {
                player.closeInventory();
            }

            if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase(GUIConstructor.chat("&c&lClose"))) {
                player.closeInventory();
            }
        }catch (Exception e){

        }
    }
}
