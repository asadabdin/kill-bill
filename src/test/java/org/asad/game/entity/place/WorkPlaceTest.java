package org.asad.game.entity.place;

import org.asad.game.entity.Location;
import org.asad.game.event.WorkEvent;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkPlaceTest {

    @Test
    public void test() {
        WorkPlace workPlace = new WorkPlace(new Location.Builder(2).withY(1).build());
        assertNotNull(workPlace);
        assertEquals(workPlace.getLocation().getX(), 2);
        assertEquals(workPlace.getLocation().getY(), 1);
        assertEquals(workPlace.getAnxiety(), 10);
        assertEquals(workPlace.getHealth(), -10);
        assertEquals(workPlace.getSkill(), 30);
        assertEquals(workPlace.getMoney(), 1000);
        assertEquals(workPlace.getName(), "Work Place");
        assertTrue(workPlace.getEvent() instanceof WorkEvent);
    }
}