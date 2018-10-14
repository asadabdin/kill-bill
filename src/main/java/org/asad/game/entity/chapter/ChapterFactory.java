package org.asad.game.entity.chapter;

import org.asad.game.entity.Location;
import org.asad.game.entity.place.*;
import org.asad.game.entity.player.PlayerEnum;
import org.asad.game.entity.player.PlayerFactory;
import org.asad.game.helper.ConsoleLogger;
import org.asad.game.helper.GameUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

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
        return null;
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
        AtomicReference<Boolean> regenerate = new AtomicReference<>(false);
        Random random = new Random();
        Location location;
        do {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (null != places && places.size() > 0) {
                places.forEach(place ->  {
                    if (place.getLocation().getX() == x && place.getLocation().getY() == y) {
                        regenerate.set(true);
                    }
                });
            }
            location = new Location.Builder(x)
                    .withY(y)
                    .build();
        } while (regenerate.get());
        return location;
    }
}
