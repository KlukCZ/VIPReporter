package me.KlukCZ.VIPReporter;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Commands implements CommandExecutor {

    public static final String vipreporter = "vipreporter";
    public static final String report = "report";

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
                    sender.sendMessage("§6§l Version> §9§l 1.0");
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
                    sender.sendMessage("§6§l> §7Shows info about the plguin");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/report §9§l[Player] §2§l<Reason>");
                    sender.sendMessage("§6§l> §7Shows info about the plguin");
                    return true;
                }
            }

            if (cmd.getName().equalsIgnoreCase(report)){
                Player reported = Bukkit.getServer().getPlayer(args[0]);
                StringBuilder stringBuilder = new StringBuilder();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm.dd.yy-hh:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                for (int i = 1; i < args.length; i++){
                    stringBuilder.append(args[i] + " ");
                }

                for (Player online : Bukkit.getOnlinePlayers()){
                    if (online.hasPermission("vipreporter.admin")){
                        online.sendMessage(" ");
                        online.sendMessage(" ");
                        online.sendMessage("§6§lVIPReporter> §9§l" + reported.getName() + "§7 report log");
                        online.sendMessage(" ");
                        online.sendMessage("§6§lName> §9§l" + reported.getName());
                        online.sendMessage("§6§lUUID> §9§l" + reported.getUniqueId());
                        online.sendMessage("§6§lReporter> §9§l" + sender.getName());
                        online.sendMessage("§6§lServer> §9§l" + reported.getServer().getServerName());
                        online.sendMessage("§6§lReason> §9§l" + stringBuilder);
                        online.sendMessage(" ");
                        online.sendMessage(" ");
                        online.sendMessage("§6§l-------------   Additional info   ----------------------");
                        online.sendMessage("§6§lWrold> §9§l" + reported.getWorld().getName());
                        online.sendMessage("§6§lTime> §9§l" + formatter.format(now));
                        if (reported.isOp()){
                            online.sendMessage("§6§lOpped> §9§lTrue");
                        }else{
                            online.sendMessage("§6§lOpped> §9§lFalse");
                        }
                    }
                }
                sender.sendMessage("§6§lVIPReporter> §9§l" + args[0] + "§7 has been reported successfully!");
                return true;
            }

        }catch (Exception e){
            sender.sendMessage("§6§lVIPReporter> §9§l/vipreporter help §7for help");
            return true;
        }
        return false;
    }
}
