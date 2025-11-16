package ru.kirushkinx.snakegame.listener.protocol;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerInput;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.kirushkinx.snakegame.SnakeGamePlugin;
import ru.kirushkinx.snakegame.game.Direction;
import ru.kirushkinx.snakegame.game.SnakeGame;

import java.util.HashMap;
import java.util.Map;

public class PlayerMovementPacketListener extends PacketListenerAbstract {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_INPUT) {
            Player player = event.getPlayer();
            SnakeGame game = SnakeGame.getGame(player);

            if (game == null || !game.isRunning()) {
                return;
            }

            WrapperPlayClientPlayerInput wrapper = new WrapperPlayClientPlayerInput(event);

            if (wrapper.isShift()) {
                Bukkit.getScheduler().runTask(SnakeGamePlugin.getInstance(), game::stop);
                return;
            }

            Direction direction = null;

            if (wrapper.isForward()) {
                direction = Direction.UP;
            } else if (wrapper.isBackward()) {
                direction = Direction.DOWN;
            } else if (wrapper.isLeft()) {
                direction = Direction.LEFT;
            } else if (wrapper.isRight()) {
                direction = Direction.RIGHT;
            }

            if (direction != null) {
                Direction finalDirection = direction;

                Bukkit.getScheduler().runTask(SnakeGamePlugin.getInstance(), () -> {
                    game.changeDirection(finalDirection);
                });
            }
        }
    }
}