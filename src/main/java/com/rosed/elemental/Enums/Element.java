package com.rosed.elemental.Enums;

import org.bukkit.entity.Player;

public enum Element {
    LEAP;

    public static void leap(Player player) {
        player.setVelocity(player.getLocation().getDirection().multiply(2.5));
    }
}
