package me.KlukCZ.VIPReporter;

import me.KlukCZ.VIPReporter.Configurations.ConfigManager;
import me.KlukCZ.VIPReporter.UI.AdminReport;
import me.KlukCZ.VIPReporter.UI.ReportConfirm;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    public static final String vipreporter = "vipreporter";
    public static final String report = "report";
    public static final String checkreport = "checkreport";

    public static String reason;
    public static String name;
    public static Player reporter;
    public static Player configSelect;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        try{
            if (cmd.getName().equalsIgnoreCase(vipreporter)){
                if (args[0].equalsIgnoreCase("info")){
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage("§6§lVIPReporter §9§lInfo");
                    sender.sendMessage(" ");
                    sender.sendMessage("§6§l Author> §9§l KlukCZ");
                    sender.sendMessage("§6§l Version> §9§l 1.3.5");
                    sender.sendMessage("§6§l Release> §a§lSTABLE");
                    return true;
                }

                if (args[0].equalsIgnoreCase("help")){
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage("§6§lVIPReporter §9§lHelp");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/vipreporter help");
                    sender.sendMessage("§6§l> §7Shows all commands of the plugin");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/vipreporter info");
                    sender.sendMessage("§6§l> §7Shows info about the plugin");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/report §9§l[Player] §2§l<Reason>");
                    sender.sendMessage("§6§l> §7Reports selected player");
                    return true;
                }
            }

            if (cmd.getName().equalsIgnoreCase(report)){
                Player player = (Player) sender;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i < args.length; i++){
                    stringBuilder.append(args[i] + " ");
                }
                reason = String.valueOf(stringBuilder);
                name = args[0];
                reporter = (Player) sender;

                player.openInventory(ReportConfirm.GUI(player));
                return true;
            }

            if (cmd.getName().equalsIgnoreCase(checkreport)){
                    ConfigManager configManager = new ConfigManager();
                    Player selected = Bukkit.getPlayer(args[0]);
                    Player player = (Player) sender;
                    configSelect = selected;
                    player.openInventory(AdminReport.GUI(player));
                    return true;
            }

        }catch (Exception e){
            sender.sendMessage("§6§lVIPReporter> §9§l/vipreporter help §7for help");
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
