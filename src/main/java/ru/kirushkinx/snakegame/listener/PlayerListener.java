package ru.kirushkinx.snakegame.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import ru.kirushkinx.snakegame.game.SnakeGame;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        SnakeGame game = SnakeGame.getGame(e.getPlayer());
        if (game != null) {
            game.stop();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        SnakeGame game = SnakeGame.getGame(e.getPlayer());
        if (game != null && game.isRunning()) {
            if (e.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
                e.setCancelled(true);
                return;
            }
            game.stop();
        }
    }
}