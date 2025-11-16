package ru.kirushkinx.snakegame;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.tofaa.entitylib.APIConfig;
import me.tofaa.entitylib.EntityLib;
import me.tofaa.entitylib.spigot.SpigotEntityLibPlatform;
import org.bukkit.plugin.java.JavaPlugin;
import ru.kirushkinx.snakegame.command.SnakeGameCommand;
import ru.kirushkinx.snakegame.game.SnakeGame;
import ru.kirushkinx.snakegame.listener.PlayerListener;
import ru.kirushkinx.snakegame.listener.protocol.PlayerMovementPacketListener;

public class SnakeGamePlugin extends JavaPlugin {

    private static SnakeGamePlugin instance;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        instance = this;

        // TODO: Add configuration

        PacketEvents.getAPI().init();

        SpigotEntityLibPlatform platform = new SpigotEntityLibPlatform(this);
        APIConfig settings = new APIConfig(PacketEvents.getAPI())
                .tickTickables()
                .trackPlatformEntities()
                .usePlatformLogger();
        EntityLib.init(platform, settings);

        new SnakeGameCommand("snakegame");

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        PacketEvents.getAPI().getEventManager().registerListener(new PlayerMovementPacketListener());

        getLogger().info("SnakeGame enabled!");
    }

    @Override
    public void onDisable() {
        SnakeGame.stopAllGames();

        PacketEvents.getAPI().terminate();
        getLogger().info("SnakeGame disabled!");
    }

    public static SnakeGamePlugin getInstance() {
        return instance;
    }
}