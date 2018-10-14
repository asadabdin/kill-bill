package org.asad.game.entity.place;

import org.asad.game.MockData;
import org.asad.game.entity.Location;
import org.asad.game.entity.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;


public class HeroTest {

    @Test
    public void test() {
        Player hero = MockData.getHero().getPlayer();
        Hero heroPlace = new Hero (new Location.Builder(2).withY(1).build(), hero);
        assertNotNull(heroPlace);
        assertEquals(heroPlace.getLocation().getX(), 2);
        assertEquals(heroPlace.getLocation().getY(), 1);
        assertEquals(heroPlace.getAnxiety(), hero.getAnxiety());
        assertEquals(heroPlace.getHealth(), hero.getHealth());
        assertEquals(heroPlace.getSkill(), hero.getSkill());
        assertEquals(heroPlace.getMoney(), hero.getMoney());
        assertEquals(heroPlace.getName(), hero.getName());
        assertNull(heroPlace.getEvent());
    }
}