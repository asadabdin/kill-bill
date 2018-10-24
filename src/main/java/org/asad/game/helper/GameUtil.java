package org.asad.game.helper;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.asad.game.entity.Location;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.entity.chapter.ChapterFactory;
import org.asad.game.entity.place.*;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * this Util class helps to Game to function properly.
 */
public class GameUtil {

    /**
     * Scanner instance to read inputs.
     */
    private static final Scanner SCANNER = new Scanner(System.in);
    /**
     * the file on which game will be saved.
     */
    private static final String SAVE_FILE = "killBill.save";

    private static boolean gameOver;


    /**
     * this method helps to move Hero on the coordinate passed as argument.
     * also checks the Movement is allowed by checking {@link org.asad.game.event.Event#isAllowedForEvent(Hero)}.
     *
     *
     * @param chapter
     * @param newX
     * @param newY
     * @throws LowAttributeException
     * @throws HighAttributeException
     */
    public static void moveHero(Chapter chapter, int newX, int newY) throws LowAttributeException, HighAttributeException {
        Place place = chapter.getMap()[newX][newY];
        if (null != place) {
            if (place.getEvent().isAllowedForEvent(chapter.getHero())) {
                place.getEvent().occurEvent(chapter.getHero());
                chapter.getPlaces().removeIf(placeToRemove -> placeToRemove.getLocation() == place.getLocation());
            } else {
                ConsoleLogger.println("You Cannot fight with Chapter's Boss now, Fight with Boss's sidekicks first");
                return;
            }
        }
        chapter.getMap()[chapter.getHero().getLocation().getX()][chapter.getHero().getLocation().getY()] = null;
        Location location = new Location.
                Builder(newX)
                .withY(newY).build();
        chapter.getHero().setLocation(location);
        chapter.getMap()[location.getX()][location.getY()] = chapter.getHero();
        if (!chapter.getHero().isReadyForBossFight()
                && chapter.getPlaces().stream().filter(place1 -> null != place1.getPlayer()).count() == 1) {
            chapter.getHero().setReadyForBossFight(true);
        }
    }

    /**
     * it helps to save the game.
     *
     * @param chapter
     */
    public static void saveGame(Chapter chapter) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(chapter);
            ConsoleLogger.println("Your Progress through the game has saved");
        } catch(IOException e) {
            ConsoleLogger.println(e.getMessage());
        }
    }


    /**
     * it helps to resume the game.
     *
     * @return
     */
    public static Chapter resumeGame() {
        Chapter chapter = null;
        try (FileInputStream fileInputStream = new FileInputStream(SAVE_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            chapter = (Chapter) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            ConsoleLogger.println(e.getMessage());
        }
        return chapter;
    }

    /**
     * it checks whether previous save game exist.
     *
     * @return
     */
    public static boolean hasFoundPreviousSaveGameToResume() {
        return new File(SAVE_FILE).exists();
    }

    /**
     * it helps to exit the game.
     */
    public static void exitGame() {
        SCANNER.close();
        gameOver = true;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    /**
     * it gives the user input.
     *
     * @return
     */
    public static String readUserInput() {
        return SCANNER.next();
    }

    /**
     * it reads the character name in the process to start a new game.
     * @return Character name
     */
    public static String readCharacterName() {
        ConsoleLogger.println("Enter your character name");
        String inputName = readUserInput();
        if (inputName.length() > 10) {
            ConsoleLogger.println("You have entered a long name");
            inputName = inputName.substring(0, 10);
            ConsoleLogger.println("I have shortened it to : "+ inputName);
        }
        return inputName;
    }

    /**
     * in case health of Hero is less then opponent then this method gets called,
     * it adds few more helping Places to gain Money, Health, Skill or decrease Anxiety.
     */
    public static void checkAndCreateNewPlacesIfRequired(Chapter chapter) {
        if (!isGameOver()) {
            IntegerProperty heroHealth = new SimpleIntegerProperty(chapter.getHero().getHealth());
            Map<Boolean, List<Place>> objectListMap = chapter.getPlaces().stream().collect(Collectors.groupingBy(place -> place.getPlayer() == null));
            if (!objectListMap.containsKey(Boolean.TRUE)
                    && objectListMap.containsKey(Boolean.FALSE)
                    && objectListMap.get(Boolean.FALSE).stream().filter(place -> place.getPlayer().getHealth() > heroHealth.get()).count() > 0) {
                ObjectProperty<Chapter> chapterObjectProperty = new SimpleObjectProperty(chapter);
                chapter.getPlaces().add(new HideOut(ChapterFactory.getUniqueLocation(chapter.getPlaces())));
                chapter.getPlaces().add(new HideOut(ChapterFactory.getUniqueLocation(chapter.getPlaces())));
                chapter.getPlaces().add(new TrainingArena(ChapterFactory.getUniqueLocation(chapter.getPlaces())));
                chapter.getPlaces().add(new WorkPlace(ChapterFactory.getUniqueLocation(chapter.getPlaces())));
                chapter.getPlaces().forEach(place -> chapterObjectProperty.get().getMap()[place.getLocation().getX()][place.getLocation().getY()] = place);
            }
        }
    }
}
