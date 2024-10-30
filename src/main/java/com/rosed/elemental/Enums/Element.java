package com.rosed.elemental.Enums;

import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public enum Element {
    LEAP,
    FIREBALL;

    public static void leap(Player player) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1.3f);
        player.setVelocity(player.getLocation().getDirection().multiply(2.5));
    }
    public static void fireball(Player player) {
        Fireball fireball = player.getWorld().spawn(player.getEyeLocation().add(player.getLocation().getDirection().multiply(1.5)), Fireball.class);

        // Set the fireball's direction to match the player's looking direction
        fireball.setDirection(player.getLocation().getDirection());

        // Optional: Set custom properties for the fireball (e.g., speed, explosion power)
        fireball.setIsIncendiary(false); // Prevents setting blocks on fire
        fireball.setYield(0); // Explosion power set to 0, so it doesn’t destroy blocks
    }
}
