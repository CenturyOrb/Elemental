package com.rosed.elemental.Listeners;

import com.rosed.elemental.ElementalPlayer;
import com.rosed.elemental.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerConnectionEvent implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        /*
        add to playermanager if the player isnt already in playermanager
        this only happens when a player without an element joins
         */
        PlayerManager.INSTANCE.getPlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        /*
        if player without element leaves, remove them from
        playermanager
        this only happens when the player without an element leaves
         */
        PlayerManager playerManager = PlayerManager.INSTANCE;
        UUID uuid = event.getPlayer().getUniqueId();
        ElementalPlayer ePlayer = playerManager.getPlayer(uuid);

        if (ePlayer.getElement() == null) playerManager.removePlayer(uuid);
    }

}
