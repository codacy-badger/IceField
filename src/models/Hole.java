package models;

/**
 * A tile with no solid platform.
 */
public class Hole extends Tile {
    /**
     * If the Hole has not been revealed, the
     * accepts the player, otherwise it doesn't.
     *
     * @param character the player to accept
     * @return true if successful, false otherwise
     */
    @Override
    public boolean acceptCharacter(Character character) {
        return false;
    }

    /**
     * Realises the storm's effects.
     */
    @Override
    public void reactToStorm() {

    }

    /**
     * Returns the maximum number of characters the
     * Tile can hold at one time, which is zero.
     *
     * @return 0
     */
    @Override
    public int getCapacity() {
        return 0;
    }
}
