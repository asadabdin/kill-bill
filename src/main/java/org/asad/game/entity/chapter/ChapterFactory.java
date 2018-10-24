package org.asad.game.entity.chapter;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.asad.game.entity.Location;
import org.asad.game.entity.place.*;
import org.asad.game.entity.player.PlayerEnum;
import org.asad.game.entity.player.PlayerFactory;
import org.asad.game.helper.ConsoleLogger;
import org.asad.game.helper.GameUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChapterFactory {


    /**
     * create a new chapter, or increase the level of chapter
     */
    public static Chapter startNewChapter(Chapter chapter) {
        if (null == chapter) {
            String nameOfTheCharacter = GameUtil.readCharacterName();
            Hero hero = new Hero(ChapterFactory.getUniqueLocation(null), PlayerFactory.createHero(nameOfTheCharacter));
            return ChapterFactory.openChapter(hero, ChapterLevel.ONE);
        } else {
            ConsoleLogger.println("You have completed the chapter : "+ chapter.getChapterLevel().getNameOfTheChapter());
            if (chapter.getChapterLevel() == ChapterLevel.ONE) {
                return ChapterFactory.openChapter(chapter.getHero(), ChapterLevel.TWO);
            } else if (chapter.getChapterLevel() == ChapterLevel.TWO) {
                return ChapterFactory.openChapter(chapter.getHero(), ChapterLevel.THREE);
            } else {
                ConsoleLogger.printPoster("won");
                GameUtil.exitGame();
            }
        }
        return chapter;
    }

    private static Chapter openChapter(Hero hero, ChapterLevel chapterLevel) {
        hero.setReadyForBossFight(false);
        return new Chapter.Builder(hero, chapterLevel)
                .withMap(new Place[10][10])
                .withPlaces(getChapterBossesAndOtherLocations(chapterLevel))
                .build();
    }

    private static List<Place> getChapterBossesAndOtherLocations(ChapterLevel chapterLevel) {
        List<Place> places = new ArrayList<>();
        places.addAll(getWarField(chapterLevel));
        places.add(new HideOut(getUniqueLocation(places)));
        places.add(new TrainingArena(getUniqueLocation(places)));
        places.add(new WorkPlace(getUniqueLocation(places)));
        return places;
    }

    private static List<Place> getWarField(ChapterLevel chapterLevel) {
        List<Place> places = new ArrayList<>();
        if (chapterLevel == ChapterLevel.ONE) {
            places.add(new WarField(getUniqueLocation(places), PlayerFactory.createPlayer(PlayerEnum.Sofie)));
        } else if (chapterLevel == ChapterLevel.TWO) {
            places.add(new WarField(getUniqueLocation(places), PlayerFactory.createPlayer(PlayerEnum.Gogo)));
            places.add(new WarField(getUniqueLocation(places), PlayerFactory.createPlayer(PlayerEnum.O_Ren)));
        } else if (chapterLevel == ChapterLevel.THREE) {
            places.add(new WarField(getUniqueLocation(places), PlayerFactory.createPlayer(PlayerEnum.Budd)));
            places.add(new WarField(getUniqueLocation(places), PlayerFactory.createPlayer(PlayerEnum.Bill)));
        }
        return places;
    }

    public static Location getUniqueLocation(List<Place> places) {
        BooleanProperty regenerate = new SimpleBooleanProperty();
        IntegerProperty x = new SimpleIntegerProperty();
        IntegerProperty y = new SimpleIntegerProperty();
        Random random = new Random();
        do {
            regenerate.set(false);
            x.set(random.nextInt(9));
            y.set(random.nextInt(9));
            if (null != places && places.size() > 0) {
                if (places.stream().anyMatch(place -> place.getLocation().getX() == x.get() && place.getLocation().getY() == y.get())) {
                    regenerate.set(true);
                }
            }
        } while (regenerate.get());
        return new Location.Builder(x.get())
                .withY(y.get())
                .build();
    }
}
