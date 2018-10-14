package org.asad.game;

import org.asad.game.entity.Location;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.entity.chapter.ChapterLevel;
import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.HideOut;
import org.asad.game.entity.place.Place;
import org.asad.game.entity.place.WarField;
import org.asad.game.entity.player.Player;
import org.asad.game.entity.player.PlayerEnum;
import org.asad.game.entity.player.PlayerFactory;

import java.util.ArrayList;

public class MockData {

    public static Chapter getChapter() {
        Hero hero = getHero();
        Place place = getHideOut();
        Place [][] places = new Place[10][10];
        places[1][1] = place;
        places[1][2] = hero;
        Chapter chapter = new Chapter.Builder(hero, ChapterLevel.ONE)
                .withMap(places)
                .withPlaces(new ArrayList<Place>() {{
                    add(place);
                    add(new WarField(
                            new Location.Builder(1).withY(1).build(),
                            new Player.Builder(PlayerEnum.Sofie.name())
                                    .withHealth(70)
                                    .withMoney(1200)
                                    .withAnxiety(20)
                                    .withSkill(50)
                                    .withChapterBoss(true)
                                    .withColor("\u001b[35m").build()));
                }}).build();

        return chapter;
    }

    public static Hero getHero() {
        return new Hero(new Location.Builder(1).withY(2).build(), PlayerFactory.createHero("Asad"));
    }

    public static HideOut getHideOut() {
        return new HideOut(new Location.Builder(1).withY(1).build());
    }

    public static Place getChapterBoss_O_Ren_Place() {
       return new WarField(new Location.Builder(1).withY(1).build(), new Player.Builder(PlayerEnum.O_Ren.name())
                .withHealth(80)
                .withMoney(3000)
                .withAnxiety(50)
                .withSkill(150)
                .withChapterBoss(true)
                .withColor("\u001b[36m").build());
    }

    public static Place getChapterBoss_Gogo_Place() {
        return new WarField(new Location.Builder(1).withY(1).build(), new Player.Builder(PlayerEnum.Gogo.name())
                .withHealth(80)
                .withMoney(3000)
                .withAnxiety(50)
                .withSkill(150)
                .withColor("\u001b[36m").build());
    }

}
