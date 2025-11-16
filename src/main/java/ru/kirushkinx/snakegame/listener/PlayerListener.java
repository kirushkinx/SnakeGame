package ru.kirushkinx.snakegame.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import ru.kirushkinx.snakegame.game.SnakeGame;

public class PlayerInputListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        SnakeGame game = SnakeGame.getGame(event.getPlayer());
        if (game != null) {
            game.stop();
        }
    }
}
