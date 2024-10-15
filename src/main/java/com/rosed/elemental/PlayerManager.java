package com.rosed.elemental;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PlayerManager {

    // HashMap that links Bukkit.Player to ElementalPlayer
    private HashMap<Player, ElementalPlayer> players = new HashMap<>();

    // Cooldown that keeps track of Element cooldown
    private Cache<UUID, Long> elementCooldown = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).build();

    /**
     * Returns ElementalPlayer that is linked to Player
     * if ElementalPlayer doesn't exist for Player yet, create
     * with no element
     * @param player player
     * @return ElementalPlayer
     */
    public ElementalPlayer getPlayer(Player player) {
        if (players.get(player) != null) return players.get(player);
        players.put(player, new ElementalPlayer());
        return players.get(player);
    }

    /**
     * Checks if player is on element cooldown
     * @param player player
     * @return true if on cooldown, otherwise false
     */
    public boolean onCooldown(Player player) { return elementCooldown.asMap().containsKey(player.getUniqueId()); }

}
