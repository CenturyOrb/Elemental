package com.rosed.elemental;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public enum PlayerManager {
    INSTANCE;

    // HashMap that links Bukkit.Player to ElementalPlayer
    private HashMap<UUID, ElementalPlayer> players = new HashMap<>();

    // Cooldown that keeps track of Element cooldown
    private Cache<UUID, Long> elementCooldown = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).build();

    /**
     * Returns ElementalPlayer that is linked to UUID
     * if ElementalPlayer doesn't exist for Player yet, create
     * with no element
     * @param uuid player's UUID
     * @return ElementalPlayer
     */
    public ElementalPlayer getPlayer(UUID uuid) {
        if (players.get(uuid) != null) return players.get(uuid);

        players.put(uuid, new ElementalPlayer(uuid));
        return players.get(uuid);
    }

    /**
     * Removes pairing of UUID and ElementalPlayer
     * @param uuid player's UUID
     */
    public void removePlayer(UUID uuid) { players.remove(uuid); }

    /**
     * Checks if player is on element cooldown
     * @param player player
     * @return true if on cooldown, otherwise false
     */
    public boolean onCooldown(Player player) { return elementCooldown.asMap().containsKey(player.getUniqueId()); }

    public HashMap<UUID, ElementalPlayer> getPlayers() { return players; }
}
