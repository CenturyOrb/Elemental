package com.rosed.elemental.Commands;

import com.rosed.elemental.Elemental;
import com.rosed.elemental.PlayerManager;
import org.bukkit.Bukkit;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

import java.util.UUID;

public class DebugCommands {

    @Command("playerlist")
    public void playerList(BukkitCommandActor commandSender) {
        // Command logic here
        if (!commandSender.isConsole()) return;
        Elemental elemental = Elemental.getInstance();
        PlayerManager playerManager = PlayerManager.INSTANCE;

        if (playerManager.getPlayers().keySet().isEmpty()) {
            elemental.getLogger().info("No players found.");
            return;
        }

        for (UUID uuid : playerManager.getPlayers().keySet()) {
            elemental.getLogger().info(Bukkit.getPlayer(uuid).getName() + ": " + playerManager.getPlayer(uuid).getElement());
        }

    }

}
