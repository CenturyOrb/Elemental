package com.rosed.elemental;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.rosed.elemental.Enums.Element;
import org.bukkit.Bukkit;

import java.util.*;
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

    public void addPlayer(UUID uuid, Element element) {
        players.put(uuid, new ElementalPlayer(uuid, element));
    }

    /**
     * Removes pairing of UUID and ElementalPlayer
     * @param uuid player's UUID
     */
    public void removePlayer(UUID uuid) { players.remove(uuid); }

    public List<ElementalPlayer> playersWithElement() {
        ArrayList<ElementalPlayer> elementPlayers = new ArrayList<>();

        for (ElementalPlayer player : players.values()) {
            if (player.getElement() != null)
                elementPlayers.add(player);
        }

        return elementPlayers;
    }

    /**
     * Checks if player is on element cooldown
     * @param uuid player's UUID
     * @return true if on cooldown, otherwise false
     */
    public boolean onCooldown(UUID uuid) { return elementCooldown.asMap().containsKey(uuid); }

    /**
     * Adds player on the cooldown for 30s
     * @param uuid player's UUID
     */
    public void addCooldowon(UUID uuid) { elementCooldown.put(uuid, System.currentTimeMillis() + 30000); }

    public HashMap<UUID, ElementalPlayer> getPlayers() { return players; }
}
