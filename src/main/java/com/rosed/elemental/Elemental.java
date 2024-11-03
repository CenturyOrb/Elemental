package com.rosed.elemental;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.rosed.elemental.Commands.DebugCommands;
import com.rosed.elemental.Commands.SummonTrader;
import com.rosed.elemental.Listeners.ElementActivateEvent;
import com.rosed.elemental.Listeners.PlayerConnectionEvent;
import com.rosed.elemental.Listeners.PlayerPotionConsume;
import com.rosed.elemental.Listeners.PrepareSmithNetherite;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public final class Elemental extends JavaPlugin {

    private static Elemental instance;
    private PlayerManager playerManager;

    private Gson gson;
    private File file;

    @Override
    public void onEnable() {
        // PDC
        NamespacedKey key = new NamespacedKey(this, "elemental");
        instance = this;
        playerManager = PlayerManager.INSTANCE;
        registerEvents();
        registerCommands();
        loadJSON();
    }

    @Override
    public void onDisable() {
        updateJSON();
    }

    /**
     * Registers all commands
     */
    private void registerCommands() {
        Lamp<BukkitCommandActor> lamp = BukkitLamp.builder(this).build();
        lamp.register(new SummonTrader());
        lamp.register(new DebugCommands());
    }

    /**
     * Registers all events
     */
    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new ElementActivateEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerConnectionEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPotionConsume(), this);
        Bukkit.getPluginManager().registerEvents(new PrepareSmithNetherite(), this);
    }

    /**
     * Loads JSON data of players to store in PlayerManager
     * players HashMap
     */
    private void loadJSON() {
        // reads JSON file for player data
        // updates PlayerManager with player's Element and UUID
        gson = new Gson();
        try {
            // makes the directory for the plugin
            if (!getDataFolder().exists())
                getDataFolder().mkdirs();
            // gets player.json file
            file = new File(getDataFolder(), "player.json");
            // (1) if player.json doesn't exist then make it
            // (2) if player.json does exist then read the data and
            // update the PlayerManager players HashMap
            if (!file.exists()) {
                file.createNewFile();
            } else {
                String json = new String(Files.readAllBytes(file.toPath()));
                if (!json.isEmpty()) {
                    java.lang.reflect.Type playerListType = new TypeToken<List<ElementalPlayer>>() {}.getType();
                    List<ElementalPlayer> players = gson.fromJson(json, playerListType);
                    for (ElementalPlayer player : players) {
                        playerManager.addPlayer(player.getUUID(), player.getElement());
                    }

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        playerManager.getPlayer(player.getUniqueId());
                    }
                } else {
                    getLogger().warning("Player data JSON file is empty. No players loaded.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates JSON with current player data with Elements
     */
    private void updateJSON() {
        gson = new Gson();

        String json = gson.toJson(playerManager.playersWithElement());

        try {
            // Create or get the player.json file
            file = new File(getDataFolder(), "player.json");

            // Write JSON to the file
            FileWriter writer = new FileWriter(file, false);
            writer.write(json);
            writer.flush();
            writer.close();

            getLogger().info("Player data updated successfully.");
        } catch (IOException e) {
            getLogger().severe("Failed to update player data to JSON file: " + e.getMessage());
        }

        getLogger().info("Disabled");
    }

    public static Elemental getInstance() { return instance; }
}
