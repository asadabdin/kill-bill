package org.asad.game.entity.action;

import org.asad.game.entity.chapter.Chapter;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.exception.NoSpaceToMoveException;
import org.asad.game.helper.GameUtil;

/**
 * this Class is responsible to move hero right.
 */
public class MoveRight implements Action {

    @Override
    public void execute(Chapter chapter) throws NoSpaceToMoveException, LowAttributeException, HighAttributeException {
        if (chapter.getHero().getLocation().getY() < (chapter.getMap()[0].length-1)) {
            GameUtil.moveHero(chapter, chapter.getHero().getLocation().getX(), chapter.getHero().getLocation().getY()+1);
        } else {
            throw new NoSpaceToMoveException("No Space left to execute down in the Map");
        }
    }
}
