package models.items;

import models.characters.Character;
import models.policies.HasRopePolicy;

import static controllers.TabController.*;

/**
 * An Item that changes the finding player's
 * RescueFriendPolicy.
 */
public class Rope extends Item {
    /**
     * Changes the finder's RescueFriendPolicy to
     * an instance of HasRopePolicy.
     *
     * @param finder the character who discovered the item
     */
    @Override
    public void uponDiscovery(Character finder) {
        addIndent();
        printlnWithIndents("Rope.uponDiscovery(finder)");

        finder.changeRescuePolicy(new HasRopePolicy());

        printlnWithIndents("return");
        removeIndent();
    }
}
