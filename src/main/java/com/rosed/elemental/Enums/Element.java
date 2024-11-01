package com.rosed.elemental.Enums;

import com.rosed.elemental.Elemental;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public enum Element {
    LEAP,
    FIREBALL;

    public static void leap(Player player) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1.3f);
        player.setVelocity(player.getLocation().getDirection().multiply(2.5));
    }
    public static void fireball(Player player) {
        Fireball fireball = player.getWorld().spawn(player.getEyeLocation().add(player.getLocation().getDirection().multiply(1.5)), Fireball.class);

        // PDC
        PersistentDataContainer pdc = fireball.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Elemental.getInstance(), "fireball");
        pdc.set(key, PersistentDataType.STRING, "special_fireball");

        // Set the fireball's direction to match the player's looking direction
        fireball.setDirection(player.getLocation().getDirection());

    }
}
