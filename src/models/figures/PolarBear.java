package models.figures;

import controllers.RandomController;
import models.exceptions.EndOfGameException;
import models.policies.SwimOutPolicy;
import models.tiles.Tile;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents a polar bear that wanders
 * around randomly. Upon entering a tile,
 * it attacks every figure on the tile.
 *
 * @author Józsa György
 * @version 1.0
 * @see models.figures.Figure
 * @since prototype
 * @since 2020.04.16
 */
public class PolarBear extends Figure implements Serializable {

    public PolarBear() {
        swimToShoreStrategy = new SwimOutPolicy();
    }

    /**
     * If the randomness is on, the PolarBear will move to
     * a random neighbouring tile. If is off, it will move
     * to a given tile.
     *
     * @throws EndOfGameException if a main character dies.
     */
    @Override
    public void step() throws EndOfGameException {
        if (RandomController.getRandom()) {
            if (tile.getNeighbours() == null || tile.getNeighbours().size() == 0)
                System.out.println("No tiles available to move to");
            else {
                Random random = new Random();
                int idx = random.nextInt(tile.getNeighbours().size());
                Tile destination = tile.getNeighbours().get(idx);

                moveTo(destination);
            }
        } else {
            System.out.println("polarbear");
            System.out.println("tile: " + tile.getID());
        }
    }

    /**
     * Moves the player to the destination Tile.
     *
     * @param destination the destination to move to
     *
     * @throws EndOfGameException if a player dies
     * @see EndOfGameException
     */
    @Override
    public void moveTo(Tile destination) throws EndOfGameException{

        if (destination.acceptCharacter(this)) {
            System.out.println("move character to " + destination.getID() + ": successful");
            tile.removeCharacter(this);
        }
        tile.reactToAttack(this);
    }

    /**
     * Realises a attack's effect on the figure.
     */
    @Override
    public void reactToAttack() {

    }

    /**
     * Realises a blizzard's effect on the figure.
     */
    @Override
    public void reactToStorm() {

    }

    /**
     * Returns the base body heat of the figure
     *
     * @return negative one
     */
    @Override
    public int getBaseBodyHeat() {
        return -1;
    }
}
