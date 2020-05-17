package controllers.game.commands;

import controllers.game.Game;
import models.Environment;

/**
 * A Reset Command to reset the game's environment.
 *
 * @author Ábrahám Dániel
 */
public class ResetCommand implements Command {

    private Game game;
    private String[] args;

    public ResetCommand(Game _game, String[] _args) {
        game = _game;
        args = _args;
    }

    /**
     * Command execution's logic.
     *
     */
    @Override
    public void execute() {
        Environment.getInstance().reset();
        game.getOutput().println("Game Reset!");
    }
}