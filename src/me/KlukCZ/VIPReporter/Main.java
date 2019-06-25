package me.KlukCZ.VIPReporter;

import me.KlukCZ.VIPReporter.Configurations.ConfigManager;
import me.KlukCZ.VIPReporter.Events.AdminReportListener;
import me.KlukCZ.VIPReporter.Events.ReportConfirmListener;
import me.KlukCZ.VIPReporter.UI.AdminReport;
import me.KlukCZ.VIPReporter.UI.ReportConfirm;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Commands commands = new Commands();

    public void onEnable() {
        loadConfigManager();
        getCommand(Commands.vipreporter).setExecutor(commands);
        getCommand(Commands.report).setExecutor(commands);
        getCommand(Commands.checkreport).setExecutor(commands);
        getServer().getPluginManager().registerEvents(new ReportConfirmListener(), this);
        getServer().getPluginManager().registerEvents(new AdminReportListener(), this);
        getServer().getConsoleSender().sendMessage("§6§lVIPReporter> §7VIPReporter has been successfully Enabled!");
        ReportConfirm.init();
        AdminReport.init();
    }

    public void loadConfigManager(){
        ConfigManager configManager = new ConfigManager();
        configManager.setup();
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§6§lVIPReporter> §7VIPReporter has been successfully Disabled!");
    }
}
