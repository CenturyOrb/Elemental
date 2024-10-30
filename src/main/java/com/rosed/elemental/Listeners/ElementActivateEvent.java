package com.rosed.elemental.Listeners;

import com.rosed.elemental.Enums.Element;
import com.rosed.elemental.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import java.util.UUID;

public class ElementActivateEvent implements Listener {

    private final PlayerManager playerManager = PlayerManager.INSTANCE;

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() != EquipmentSlot.HAND) return; // when clicking block, it fires once per hand
        if (!player.isSneaking()) return;


        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // player tries to use element
            // (1) doesn't have element or is on Cooldown, do nothing
            // (2) if do then use element, then add player to cooldown
            UUID uuid = player.getUniqueId();
            Element element = playerManager.getPlayer(uuid).getElement();
            if (playerManager.onCooldown(uuid)) {
                player.sendMessage(ChatColor.RED + "You are on cooldown!");
                return;
            }
            try  {
                switch (element) {
                    case LEAP:
                        player.sendMessage(ChatColor.GREEN + "Used LEAP");
                        playerManager.addCooldown(uuid);
                        Element.leap(player);
                        break;
                    case FIREBALL:
                        player.sendMessage(ChatColor.GREEN + "Used FIREBALL");
                        playerManager.addCooldown(uuid);
                        Element.fireball(player);
                }
            } catch (NullPointerException e) {
                player.sendMessage(ChatColor.RED + "Unknown element");
            }
        }
    }

}
