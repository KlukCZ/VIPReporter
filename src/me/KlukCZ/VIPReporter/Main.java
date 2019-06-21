package me.KlukCZ.VIPReporter;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Commands commands = new Commands();

    public void onEnable() {
        getCommand(Commands.vipreporter).setExecutor(commands);
        getCommand(Commands.report).setExecutor(commands);
        getServer().getConsoleSender().sendMessage("§6§lVIPReporter> §7VIPReporter has been successfully Enabled!");
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§6§lVIPReporter> §7VIPReporter has been successfully Disabled!");
    }
}
