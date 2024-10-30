package com.rosed.elemental.Commands;

import com.rosed.elemental.Elemental;
import com.rosed.elemental.Enums.Trader;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.util.ArrayList;
import java.util.List;

public class SummonTrader {

    @Command("summontrader")
    @CommandPermission("elemental.operator")
    public void summonTrader(Player player, Trader trader) {
        // Command logic here
        spawnTrader(player, trader);
    }

    /**
     * Spawns in specified trader at player's location
     * @param player op player
     */
    private void spawnTrader(Player player, Trader traderName) {
        Location loc = player.getLocation();
        Villager trader = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        List<MerchantRecipe> trades = new ArrayList<>();
        MerchantRecipe trade;
        if (traderName == Trader.AMBROSIA) {
            trader.setCustomName(ChatColor.RED + "Ambrosia");
            trader.setProfession(Villager.Profession.CLERIC);
            trader.setCustomNameVisible(true);
            trader.setInvulnerable(true);
            trader.setAI(false);

            trade = new MerchantRecipe(createLeaprootConcoction(), 9999); // Max uses of trade
            trade.addIngredient(new ItemStack(Material.DRAGON_EGG)); // The required item (Ender Dragon Egg)
            trades.add(trade);
            trader.setRecipes(trades);
        } else if (traderName == Trader.CINTHIA) {
            trader.setCustomName(ChatColor.RED + "Cinthia");
            trader.setCustomNameVisible(true);
            trader.setInvulnerable(true);
            trader.setAI(false);

            trade = new MerchantRecipe(createFireballConcoction(), 9999); // Max uses of trade
            trade.addIngredient(new ItemStack(Material.FLINT_AND_STEEL)); // The required item (Ender Dragon Egg)
            trades.add(trade);
            trader.setRecipes(trades);
        }
    }

    /**
     * Creates ItemStack of Leaproot Concoction
     * @return Leaproot Concoction ItemStack
     */
    public static ItemStack createLeaprootConcoction() {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        potionMeta.setDisplayName(ChatColor.RED + "Leaproot Concoction");
        // PDC Key
        NamespacedKey key = new NamespacedKey(Elemental.getInstance(), "elemental");
        // PDC Meta
        PersistentDataContainer pdc = potionMeta.getPersistentDataContainer();
        pdc.set(key, PersistentDataType.STRING, "leap");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "A mysterious potion");
        lore.add("");
        lore.add(ChatColor.GREEN + "- Grants ability to leap");
        potionMeta.setLore(lore);

        potionMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        potionMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        potion.setItemMeta(potionMeta);

        return potion;
    }


    /**
     * Creates ItemStack of Fireball Concoction
     * @return Fireball Concoction ItemStack
     */
    public ItemStack createFireballConcoction() {
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        potionMeta.setDisplayName(ChatColor.RED + "Fireball Concoction");
        // PDC Key
        NamespacedKey key = new NamespacedKey(Elemental.getInstance(), "elemental");
        // PDC Meta
        PersistentDataContainer pdc = potionMeta.getPersistentDataContainer();
        pdc.set(key, PersistentDataType.STRING, "fireball");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "A mysterious potion");
        lore.add("");
        lore.add(ChatColor.GREEN + "- Grants ability to throw fireballs");
        potionMeta.setLore(lore);

        potionMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        potionMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        potion.setItemMeta(potionMeta);

        return potion;
    }
}
