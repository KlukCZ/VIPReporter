package me.KlukCZ.VIPReporter.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIConstructor {

    public static String chat(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static ItemStack createItem(Inventory inventory, String materialID, int amount, int inventorySlot, String displayName, String... lores){
        ItemStack item;
        List<String> lore = new ArrayList<>();

        item = new ItemStack(Material.getMaterial(materialID), amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(GUIConstructor.chat(displayName));
        for (String s : lores){
            lore.add(GUIConstructor.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(inventorySlot - 1, item);
        return item;
    }

    public static ItemStack createItemByte(Inventory inventory, String materialID, int byteID, int amount, int inventorySlot, String displayName, String... lores){
        ItemStack item;
        List<String> lore = new ArrayList<>();

        item = new ItemStack(Material.getMaterial(materialID), amount, (short) byteID);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(GUIConstructor.chat(displayName));
        for (String s : lores){
            lore.add(GUIConstructor.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(inventorySlot - 1, item);
        return item;
    }

    public static ItemStack createItemSkull(Inventory inventory, String materialID, String owner, int amount, int inventorySlot, String displayName, String... lores){
        ItemStack item;
        List<String> lore = new ArrayList<>();

        item = new ItemStack(Material.getMaterial(materialID), amount, (byte) SkullType.PLAYER.ordinal());

        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        meta.setDisplayName(GUIConstructor.chat(displayName));
        meta.setOwner(owner);
        for (String s : lores){
            lore.add(GUIConstructor.chat(s));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(inventorySlot - 1, item);
        return item;
    }
}
