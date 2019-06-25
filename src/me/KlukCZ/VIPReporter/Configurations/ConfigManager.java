package me.KlukCZ.VIPReporter.Configurations;

import me.KlukCZ.VIPReporter.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ConfigManager {

    private Main plugin = Main.getPlugin(Main.class);

    //File & Files
    private FileConfiguration config;
    private File file;

    public void setup(){
        if (!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(), "database.yml");

        if (!file.exists()){
            try {
                file.createNewFile();
                loadDefaultDatabase();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe("VIPBans was unable to create database.yml folder!");
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getDatabase(){
        return config;
    }

    public void loadDatabase(){
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe("VIPBans was unable to save database.yml folder!");
        }
    }

    public void reloadDatabase(){
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void loadDefaultDatabase(){
        try(InputStream in = plugin.getResource("database.yml"); FileOutputStream out = new FileOutputStream(file)) {
            int readen;
            do{
                readen = in.read();
                if (readen != -1){
                    out.write(readen);
                }
            }while (readen != -1);
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}