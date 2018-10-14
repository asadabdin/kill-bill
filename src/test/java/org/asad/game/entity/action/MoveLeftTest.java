package org.asad.game.entity.action;

import org.asad.game.MockData;
import org.asad.game.entity.Location;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.exception.NoSpaceToMoveException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoveLeftTest {

    private Action moveLeft = new MoveLeft();
    private Chapter chapter;

    @Before
    public void setUp() {
        chapter = MockData.getChapter();
    }

    @Test(expected = NoSpaceToMoveException.class)
    public void execute_NoSpace() throws LowAttributeException, NoSpaceToMoveException, HighAttributeException {
        chapter.getHero().setLocation(new Location.Builder(3). withY(0).build());
        moveLeft.execute(chapter);
    }

    @Test
    public void execute_success() throws LowAttributeException, NoSpaceToMoveException, HighAttributeException {
        moveLeft.execute(chapter);
        assertEquals(chapter.getHero().getLocation().getY(), 2);
    }
}