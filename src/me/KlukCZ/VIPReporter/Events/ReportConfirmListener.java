package me.KlukCZ.VIPReporter.Events;

import me.KlukCZ.VIPReporter.UI.ReportConfirm;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ReportConfirmListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        String title = event.getInventory().getTitle();
        if (title.equals(ReportConfirm.inventory_name)){
            event.setCancelled(true);
            if (event.getCurrentItem() == null){
                return;
            }
            if (title.equals(ReportConfirm.inventory_name)){
                ReportConfirm.clicked( (Player) event.getWhoClicked(), event.getSlot(), event.getCurrentItem(), event.getInventory());
            }
        }
    }
}
