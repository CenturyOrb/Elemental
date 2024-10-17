package com.rosed.elemental.Enums;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public enum Element {
    LEAP;

    public static void leap(Player player) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1.3f);
        player.setVelocity(player.getLocation().getDirection().multiply(2.5));
    }
}
