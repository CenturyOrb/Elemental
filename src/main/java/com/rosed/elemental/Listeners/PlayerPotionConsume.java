package com.rosed.elemental.Listeners;

import com.rosed.elemental.Commands.SummonTrader;
import com.rosed.elemental.Elemental;
import com.rosed.elemental.Enums.Element;
import com.rosed.elemental.PlayerManager;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PlayerPotionConsume implements Listener {
    private final PlayerManager playerManager = PlayerManager.INSTANCE;

    @EventHandler
    public void PlayerConsume(PlayerItemConsumeEvent e) {
        NamespacedKey key = new NamespacedKey(Elemental.getInstance(), "elemental");
        ItemStack item = e.getItem();

        // Make sure item has meta to avoid errors
        if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            String name = item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
            // See if PDC data matches an Enum value
            try {
                Element element = Element.valueOf(name.toUpperCase());
                playerManager.getPlayer(e.getPlayer().getUniqueId()).setElement(element);


            } catch (IllegalArgumentException error) {
                Elemental.getInstance().getLogger().warning("Element " + name + " not found");
            }
        }
    }
}
