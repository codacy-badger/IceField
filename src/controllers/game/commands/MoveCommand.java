package controllers.game.commands;

import controllers.game.Game;
import controllers.game.GameJFrame;
import controllers.view.MapPresenter;
import models.Environment;
import models.exceptions.EndOfGameException;
import models.tiles.Tile;

public class MoveCommand implements Command {

    private Game game;
    private String[] args;

    public MoveCommand(Game _game, String[] _args) {
        game = _game;
        args = _args;
    }

    @Override
    public void execute() {
        if (Environment.getInstance().getCurrentPlayer() == null) {
            GameJFrame.getInstance().OutputToTextBox("There is no Figure selected, please use the \"nextcharacter\" command before the first move!");
            return;
        }

        Tile currentTile = Environment.getInstance().getCurrentPlayer().getTile();

        if (args.length == 0) {
            GameJFrame.getInstance().OutputToTextBox("neigbours: ");
            StringBuilder sb = new StringBuilder();
            currentTile.getNeighbours().forEach(t -> sb.append(String.format("%d ", t.getID())));
            GameJFrame.getInstance().OutputToTextBox(sb.toString().trim());
        } else if (args.length == 1) {
            int tileID;
            try {
                tileID = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                //TODO invalid parameter
                return;
            }

            for (Tile tile : currentTile.getNeighbours()) {
                if (tile.getID() == tileID) {
                    try {
                        Environment.getInstance().getCurrentPlayer().moveTo(tile);
                        MapPresenter.getInstance().update();
                    } catch (EndOfGameException e) {
                        GameJFrame.getInstance().OutputToTextBox(e.getMessage());
                        Environment.getInstance().gameOver();
                    }
                    return;
                }
            }

            GameJFrame.getInstance().OutputToTextBox(String.format("move character to %d: unsuccessful", tileID));
            GameJFrame.getInstance().OutputToTextBox(String.format("The current Tile has no neighbor with the ID %d", tileID));

            MapPresenter.getInstance().update();
        }
    }
}
