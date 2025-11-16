package ru.kirushkinx.snakegame.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.kirushkinx.snakegame.game.SnakeGame;

import java.util.List;

public class SnakeGameCommand extends Command {

    public SnakeGameCommand(String name) {
        super(name);
        setDescription("Start the snake game");
        setAliases(List.of("snake"));
        Bukkit.getCommandMap().register("snakegame", this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        SnakeGame game = new SnakeGame(player);
        game.start();

        return true;
    }
}
