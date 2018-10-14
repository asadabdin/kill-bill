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

public class MoveUpTest {

    private Action moveUp = new MoveUp();
    private Chapter chapter;

    @Before
    public void setUp() throws Exception {
        chapter = MockData.getChapter();
    }

    @Test(expected = NoSpaceToMoveException.class)
    public void execute_NoSpace() throws LowAttributeException, NoSpaceToMoveException, HighAttributeException {
        chapter.getHero().setLocation(new Location.Builder(0). withY(1).build());
        moveUp.execute(chapter);
    }

    @Test
    public void execute_success() throws LowAttributeException, NoSpaceToMoveException, HighAttributeException {
        moveUp.execute(chapter);
        assertEquals(chapter.getHero().getLocation().getX(), 0);
    }

}