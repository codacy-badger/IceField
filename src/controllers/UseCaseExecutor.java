package controllers;

import models.Environment;
import models.characters.Character;
import models.characters.Eskimo;
import models.characters.Researcher;
import models.items.*;
import models.policies.*;
import models.tiles.Hole;
import models.tiles.InstableIcePatch;
import models.tiles.StableIcePatch;
import models.tiles.Tile;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that sets up and executes use cases.
 */
public class UseCaseExecutor {

    /**
     * Sets up and executes the MoveCharacterToStable use case.
     */
    public static void moveCharacterToStable() {
        System.out.println("Usecase: MoveCharacterToStable");

        StableIcePatch source = new StableIcePatch();
        StableIcePatch dest = new StableIcePatch();
        Researcher explorer = new Researcher(source);
        source.addCharacter(explorer);

        explorer.moveTo(dest);
    }

    /**
     * Sets up and executes the MoveCharacterToInstable use case.
     */
    public static void moveCharacterToInstable() {
        System.out.println("Usecase: MoveCharacterToInstable");

        StableIcePatch source = new StableIcePatch();
        InstableIcePatch dest = new InstableIcePatch(10);
        Researcher explorer = new Researcher(source);
        source.addCharacter(explorer);

        explorer.moveTo(dest);
    }

    /**
     * Sets up and executes the moveCharacterToInstableNoDiveSuit use case.
     */
    public static void moveCharacterToInstableNoDiveSuit() {
        System.out.println("Usecase: MoveCharacterToInstableNoDiveSuit");

        StableIcePatch source = new StableIcePatch();
        InstableIcePatch dest = new InstableIcePatch(0);
        Researcher explorer = new Researcher(source);
        source.addCharacter(explorer);

        explorer.moveTo(dest);
    }

    /**
     * Sets up and executes the moveCharacterToInstableWithDiveSuit use case.
     */
    public static void moveCharacterToInstableWithDiveSuit() {
        System.out.println("Usecase: MoveCharacterToInstableWithDiveSuit");

        StableIcePatch source = new StableIcePatch();
        InstableIcePatch dest = new InstableIcePatch(0);
        Researcher explorer = new Researcher(source);
        explorer.setSwimToShoreStrategy(new HasDiveSuitPolicy());
        source.addCharacter(explorer);

        explorer.moveTo(dest);
    }

    /**
     * Sets up and executes the moveCharacterToUndiscoveredHole use case.
     */
    public static void moveCharacterToUndiscoveredHole() {
        System.out.println("Usecase: MoveCharacterToUndiscoveredHole");

        StableIcePatch source = new StableIcePatch();
        Hole dest = new Hole();
        Researcher explorer = new Researcher(source);
        source.addCharacter(explorer);

        explorer.moveTo(dest);
    }

    /**
     * Sets up and executes the moveCharacterToDiscoveredHole use case.
     */
    public static void moveCharacterToDiscoveredHole() {
        System.out.println("Usecase: MoveCharacterToDiscoveredHole");

        StableIcePatch source = new StableIcePatch();
        Hole dest = new Hole();
        dest.setDiscovered(true);
        Researcher explorer = new Researcher(source);
        source.addCharacter(explorer);

        explorer.moveTo(dest);
    }

    /**
     * Sets up and executes the craftSignalFlare use case.
     */
    public static void craftSignalFlare() {
        System.out.println("Usecase: CraftSignalFlare");

        boolean beaconFound = getYesNo("Has the Beacon been found?");
        boolean cartridgeFound = getYesNo("Has the Cartridge been found?");
        boolean gunFound = getYesNo("Has the Gun been found?");

        StableIcePatch tile = new StableIcePatch();
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
        Researcher player = new Researcher(tile);
        ArrayList<Character> players = new ArrayList<>();
        players.add(player);
        tile.addCharacter(player);
        Environment environment = Environment.getInstance();
        environment.setBeaconIsDiscovered(beaconFound);
        environment.setCartridgeIsDiscovered(cartridgeFound);
        environment.setGunIsDiscovered(gunFound);
        environment.setPlayers(players);
        environment.setIceTiles(tiles);
        environment.setCurrentPlayer(player);

        player.craftSignalFlare();
    }

    /**
     * Sets up and executes the clearPatch use case.
     */
    public static void clearPatch() {
        System.out.println("Usecase: ClearPatch");

        StableIcePatch tile = new StableIcePatch();
        Researcher explorer = new Researcher(tile);
        tile.addCharacter(explorer);

        explorer.clearPatch();
    }

    /**
     * Sets up and executes the analyzeStable use case.
     */
    public static void analyzeStable() {
        System.out.println("Usecase: AnalyzeStable");

        StableIcePatch tile = new StableIcePatch();
        StableIcePatch target = new StableIcePatch();
        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(target);
        tile.setNeighbours(neighbours);
        Researcher explorer = new Researcher(tile);
        tile.addCharacter(explorer);

        explorer.useSpecial(target);
    }

    /**
     * Sets up and executes the analyzeInstable use case.
     */
    public static void analyzeInstable() {
        System.out.println("Usecase: AnalyzeInstable");

        StableIcePatch tile = new StableIcePatch();
        InstableIcePatch target = new InstableIcePatch(10);
        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(target);
        tile.setNeighbours(neighbours);
        Researcher explorer = new Researcher(tile);
        tile.addCharacter(explorer);

        explorer.useSpecial(target);
    }

    /**
     * Sets up and executes the analyzeHole use case.
     */
    public static void analyzeHole() {
        System.out.println("Usecase: AnalyzeHole");

        StableIcePatch tile = new StableIcePatch();
        Hole target = new Hole();
        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(target);
        tile.setNeighbours(neighbours);
        Researcher explorer = new Researcher(tile);
        tile.addCharacter(explorer);

        explorer.useSpecial(target);
    }

    /**
     * Sets up and executes the buildIglu use case.
     */
    public static void buildIglu() {
        System.out.println("Usecase: BuildIglu");

        StableIcePatch tile = new StableIcePatch();
        Eskimo explorer = new Eskimo(tile);
        tile.addCharacter(explorer);

        explorer.useSpecial(tile);
    }

    /**
     * Sets up and executes the rescueFriendWithRope use case.
     */
    public static void rescueFriendWithRope() {
        System.out.println("Usecase: RescueFriendWithRope");

        StableIcePatch dest = new StableIcePatch();
        Hole source = new Hole();
        Researcher player = new Researcher(dest);
        Researcher victim = new Researcher(source);
        player.setHelpFriendStrategy(new HasRopePolicy());
        dest.addCharacter(player);
        source.acceptCharacter(victim);

        player.rescueFriend(victim);
    }

    /**
     * Sets up and executes the rescueFriendWithNothing use case.
     */
    public static void rescueFriendWithNothing() {
        System.out.println("Usecase: RescueFriendWithNothing");

        StableIcePatch dest = new StableIcePatch();
        Hole source = new Hole();
        Researcher player = new Researcher(dest);
        Researcher victim = new Researcher(source);
        player.setHelpFriendStrategy(new NoRescuePolicy());
        dest.addCharacter(player);
        source.acceptCharacter(victim);

        player.rescueFriend(victim);
    }

    /**
     * Sets up and executes the makeStormWithoutIglu use case.
     */
    public static void makeStormWithoutIglu() {
        System.out.println("Usecase: MakeStormWithoutIglu");

        Environment environment = setUpStorm(new NoProtectionPolicy());

        environment.makeStorm();
    }

    /**
     * Sets up and executes the makeStormWithIglu use case.
     */
    public static void makeStormWithIglu() {
        System.out.println("Usecase: MakeStormWithIglu");

        Environment environment = setUpStorm(new IgluPolicy());
        environment.makeStorm();
    }

    /**
     * Sets up the Environment for 'makeStorm' use cases.
     *
     * @param frostBiteStrategy the strategy of the tile
     * @return the all set-up Environment
     */
    private static Environment setUpStorm(FrostBitePolicy frostBiteStrategy) {
        System.out.println("Usecase: SetUpStorm");

        StableIcePatch tile = new StableIcePatch();
        ArrayList<Tile> tiles = new ArrayList<>();
        tile.setFrostBiteStrategy(frostBiteStrategy);
        tiles.add(tile);
        Researcher victim1 = new Researcher(tile);
        Researcher victim2 = new Researcher(tile);
        Eskimo victim3 = new Eskimo(tile);
        ArrayList<Character> players = new ArrayList<>();
        players.add(victim1);
        players.add(victim2);
        players.add(victim3);
        Environment environment = Environment.getInstance();
        environment.setPlayers(players);
        environment.setIceTiles(tiles);
        return environment;
    }

    /**
     * Sets up and executes the unburyFood use case.
     */
    public static void unburyFood() {
        System.out.println("Usecase: UnburyFood");

        StableIcePatch target = new StableIcePatch();
        Researcher finder = new Researcher(target);
        target.addCharacter(finder);
        Food food = new Food();
        target.setBuriedItem(food);

        finder.retrieveItem();
    }

    /**
     * Sets up and executes the unburyDiveSuit use case.
     */
    public static void unburyDiveSuit() {
        System.out.println("Usecase: UnburyDiveSuit");

        StableIcePatch target = new StableIcePatch();
        Researcher finder = new Researcher(target);
        target.addCharacter(finder);
        DiveSuit diveSuit = new DiveSuit();
        target.setBuriedItem(diveSuit);

        finder.retrieveItem();
    }

    /**
     * Sets up and executes the unburyRope use case.
     */
    public static void unburyRope() {
        System.out.println("Usecase: UnburyRope");

        StableIcePatch target = new StableIcePatch();
        Researcher finder = new Researcher(target);
        target.addCharacter(finder);
        Rope rope = new Rope();
        target.setBuriedItem(rope);

        finder.retrieveItem();
    }

    /**
     * Sets up and executes the unburyShovel use case.
     */
    public static void unburyShovel() {
        System.out.println("Usecase: UnburyShovel");

        StableIcePatch target = new StableIcePatch();
        Researcher finder = new Researcher(target);
        target.addCharacter(finder);
        Shovel shovel = new Shovel();
        target.setBuriedItem(shovel);

        finder.retrieveItem();
    }

    /**
     * Sets up and executes the unburyCartridge use case.
     */
    public static void unburyCartridge() {
        System.out.println("Usecase: UnburyCartridge");

        StableIcePatch target = new StableIcePatch();
        Researcher finder = new Researcher(target);
        target.addCharacter(finder);
        Cartridge cartridge = new Cartridge();
        target.setBuriedItem(cartridge);

        finder.retrieveItem();
    }

    /**
     * Sets up and executes the unburyBeacon use case.
     */
    public static void unburyBeacon() {
        System.out.println("Usecase: UnburyBeacon");

        StableIcePatch target = new StableIcePatch();
        Researcher finder = new Researcher(target);
        target.addCharacter(finder);
        Beacon beacon = new Beacon();
        target.setBuriedItem(beacon);

        finder.retrieveItem();
    }

    /**
     * Sets up and executes the unburyGun use case.
     */
    public static void unburyGun() {
        System.out.println("Usecase: UnburyGun");

        StableIcePatch target = new StableIcePatch();
        Researcher finder = new Researcher(target);
        target.addCharacter(finder);
        Gun gun = new Gun();
        target.setBuriedItem(gun);

        finder.retrieveItem();
    }

    /**
     * Asks the user to answer to a Yes or No question,
     * and returns with the boolean answer.
     *
     * @param question a String containing a simple question
     * @return true if answer was a yes, false otherwise
     */
    private static boolean getYesNo(String question) {

        System.out.println(question + " (y/n)");

        while (true) {
            System.out.print("> ");
            String input;
            Scanner in = new Scanner(System.in);

            try {
                input = in.next();
            } catch (InputMismatchException exception) {
                System.out.println("Please enter y/n!");
                continue;
            }

            if (input.equals("y"))
                return true;
            else if (input.equals("n"))
                return false;
            else
                System.out.println("Please enter y/n!");
        }
    }

}
