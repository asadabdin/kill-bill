package org.asad.game.entity.chapter;

import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.Place;

import java.io.Serializable;
import java.util.List;

public class Chapter implements Serializable {

    private static final long serialVersionUID = 888838120502626099L;
    private Place[][] map;
    private List<Place> places;
    private Hero hero;
    private ChapterLevel chapterLevel;

    private Chapter() {
    }

    public Place[][] getMap() {
        return map;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public Hero getHero() {
        return hero;
    }

    public ChapterLevel getChapterLevel() {
        return chapterLevel;
    }

    public static final class Builder {
        private Place[][] map;
        private List<Place> places;
        private Hero hero;
        private ChapterLevel chapterLevel;

        public Builder(Hero hero, ChapterLevel chapterLevel) {
            this.hero = hero;
            this.chapterLevel = chapterLevel;
        }

        public Builder withMap(Place[][] map) {
            this.map = map;
            return  this;
        }

        public Builder withPlaces(List<Place> places) {
            this.places = places;
            return this;
        }

        public Chapter build() {
            Chapter chapter = new Chapter();
            chapter.hero = this.hero;
            chapter.map = this.map;
            chapter.places = this.places;
            chapter.chapterLevel = this.chapterLevel;
            chapter.places.forEach(place -> map[place.getLocation().getX()] [place.getLocation().getY()] = place);
            chapter.map[hero.getLocation().getX()] [hero.getLocation().getY()] = hero;
            return chapter;
        }
    }
}
