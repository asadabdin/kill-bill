package org.asad.game.entity.place;

import org.asad.game.MockData;
import org.asad.game.entity.Location;
import org.asad.game.entity.player.Player;
import org.asad.game.event.FightEvent;
import org.junit.Test;

import static org.junit.Assert.*;

public class WarFieldTest {

    @Test
    public void test() {
        Player gogo = MockData.getChapterBoss_Gogo_Place().getPlayer();
        WarField warField = new WarField(new Location.Builder(2).withY(1).build(), gogo);
        assertNotNull(warField);
        assertEquals(warField.getLocation().getX(), 2);
        assertEquals(warField.getLocation().getY(), 1);
        assertEquals(warField.getAnxiety(), gogo.getAnxiety());
        assertEquals(warField.getHealth(), gogo.getHealth());
        assertEquals(warField.getSkill(), gogo.getSkill());
        assertEquals(warField.getMoney(), gogo.getMoney());
        assertEquals(warField.getName(), gogo.getName());
        assertTrue(warField.getEvent() instanceof FightEvent);
    }

}