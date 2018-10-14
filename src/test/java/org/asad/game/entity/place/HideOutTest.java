package org.asad.game.entity.place;

import org.asad.game.entity.Location;
import org.asad.game.event.HideEvent;
import org.junit.Test;

import static org.junit.Assert.*;

public class HideOutTest {

    @Test
    public void test() {
        HideOut hideOut = new HideOut(new Location.Builder(2).withY(1).build());
        assertNotNull(hideOut);
        assertEquals(hideOut.getLocation().getX(), 2);
        assertEquals(hideOut.getLocation().getY(), 1);
        assertEquals(hideOut.getAnxiety(), -30);
        assertEquals(hideOut.getHealth(), 50);
        assertEquals(hideOut.getSkill(), 0);
        assertEquals(hideOut.getMoney(), 1000);
        assertEquals(hideOut.getName(), "HideOut");
        assertTrue(hideOut.getEvent() instanceof HideEvent);
    }
}