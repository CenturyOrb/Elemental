package com.rosed.elemental.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.inventory.ItemStack;

public class PrepareSmithNetherite implements Listener {

    @EventHandler
    public void onPrepareSmithNetherite(SmithItemEvent event) {
        ItemStack result = event.getInventory().getResult();
        event.setCancelled(result.getType().toString().contains("NETHERITE"));
    }

}
