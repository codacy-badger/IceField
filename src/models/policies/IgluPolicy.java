package models.policies;

import models.characters.Character;

import static controllers.TabController.*;

/**
 * The strategy of a Tile to protect
 * its inhabitants with an Igloo
 */
public class IgluPolicy implements FrostBitePolicy {
    /**
     * Protects the victim with an Igloo.
     *
     * @param victim the character being exposed
     *               to the storm
     */
    @Override
    public void executeStrategy(Character victim) {
        addIndent();
        printlnWithIndents("IgluPolicy.executeStrategy(victim)");
        printlnWithIndents("return");
        removeIndent();
    }
}
