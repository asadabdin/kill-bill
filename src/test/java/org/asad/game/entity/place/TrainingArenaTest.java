package org.asad.game.entity.place;

import org.asad.game.entity.Location;
import org.asad.game.event.TrainingEvent;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrainingArenaTest {

    @Test
    public void test() {
        TrainingArena trainingArena = new TrainingArena(new Location.Builder(2).withY(1).build());
        assertNotNull(trainingArena);
        assertEquals(trainingArena.getLocation().getX(), 2);
        assertEquals(trainingArena.getLocation().getY(), 1);
        assertEquals(trainingArena.getAnxiety(), 30);
        assertEquals(trainingArena.getHealth(), -10);
        assertEquals(trainingArena.getSkill(), 50);
        assertEquals(trainingArena.getMoney(), -500);
        assertEquals(trainingArena.getName(), "Training Arena");
        assertTrue(trainingArena.getEvent() instanceof TrainingEvent);
    }
}