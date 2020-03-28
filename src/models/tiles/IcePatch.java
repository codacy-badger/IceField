package models.tiles;

import models.characters.Character;
import models.items.Item;
import models.policies.FrostBitePolicy;

/**
 * A Tile frozen with ice on which characters
 * can stand on.
 */
abstract public class IcePatch extends Tile {

    /**
     * Returns the item hidden in the IcePatch.
     *
     * @param player the player who picked the item up
     * @return the buried item
     */
    public Item unBuryItem(Character player) {
        ///TODO
        throw new NullPointerException("Not implemented yet");
    }

    /**
     * Realises the storm's effects.
     */
    @Override
    public void reactToStorm() {
        addSnow(1);
    }

    /**
     * Changes the strategy of the Tile for a storm.
     *
     * @param strategy the new strategy
     */
    public void changeFrostBitePolicy(FrostBitePolicy strategy) {
        ///TODO
    }

}
