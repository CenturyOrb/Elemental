package com.rosed.elemental.Listeners;

import com.rosed.elemental.Elemental;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FireballDamage implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityExplodeEvent event) {
        if(event.getEntity() instanceof Fireball) {
            Fireball fireball = (Fireball) event.getEntity();
            PersistentDataContainer pdc = fireball.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(Elemental.getInstance(), "fireball");
            if(pdc.has(key, PersistentDataType.STRING)) {
                String value = pdc.get(key, PersistentDataType.STRING);
                Location fireballLocation = fireball.getLocation();
                fireball.remove();
                event.setCancelled(true);
                World world = fireballLocation.getWorld();
                if ("special_fireball".equals(value)) {
                    if(world != null) {
                        world.createExplosion(fireballLocation, 1.2F, false, false);
                    }
                }
            }
        }
    }

}
