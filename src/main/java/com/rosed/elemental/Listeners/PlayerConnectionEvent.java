package com.rosed.elemental.Listeners;

import com.rosed.elemental.ElementalPlayer;
import com.rosed.elemental.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerConnectionEvent implements Listener {

    /**
     * Adds players with no elements to the PlayerManager
     * @param event PlayerJoinEvent
     */
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        PlayerManager.INSTANCE.getPlayer(event.getPlayer().getUniqueId());
    }

    /**
     * Removes players with no elements from the PlayerManager
     * @param event PlayerQuitEvent
     */
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        PlayerManager playerManager = PlayerManager.INSTANCE;
        UUID uuid = event.getPlayer().getUniqueId();
        ElementalPlayer ePlayer = playerManager.getPlayer(uuid);

        if (ePlayer.getElement() == null) playerManager.removePlayer(uuid);
    }

}
