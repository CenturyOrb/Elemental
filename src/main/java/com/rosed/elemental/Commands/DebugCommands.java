package com.rosed.elemental.Commands;

import com.rosed.elemental.Elemental;
import com.rosed.elemental.Enums.Element;
import com.rosed.elemental.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

import java.util.UUID;

public class DebugCommands {
    private final Elemental elemental = Elemental.getInstance();
    private final PlayerManager playerManager = PlayerManager.INSTANCE;

    @Command("playerlist")
    public void playerList(BukkitCommandActor commandSender) {
        // Command logic here
        if (!commandSender.isConsole()) return;

        if (playerManager.getPlayers().keySet().isEmpty()) {
            elemental.getLogger().info("No players found.");
            return;
        }

        for (UUID uuid : playerManager.getPlayers().keySet()) {
            if (Bukkit.getPlayer(uuid) == null) {
                elemental.getLogger().info(Bukkit.getOfflinePlayer(uuid).getName() + " (Offline): " + playerManager.getPlayer(uuid).getElement());
            } else
                elemental.getLogger().info(Bukkit.getPlayer(uuid).getName() + ": " + playerManager.getPlayer(uuid).getElement());
        }
    }

    @Command("setelement")
    public void setElement(BukkitCommandActor commandSender, Player player, Element element) {
        playerManager.getPlayer(player.getUniqueId()).setElement(element);
    }

}
