package me.KlukCZ.VIPReporter.Events;

import me.KlukCZ.VIPReporter.UI.AdminReport;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminReportListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        String title = event.getInventory().getTitle();
        if (title.equals(AdminReport.inventory_name)){
            event.setCancelled(true);
            if (event.getCurrentItem() == null){
                return;
            }
            if (title.equals(AdminReport.inventory_name)){
                AdminReport.clicked( (Player) event.getWhoClicked(), event.getSlot(), event.getCurrentItem(), event.getInventory());
            }
        }
    }
}
