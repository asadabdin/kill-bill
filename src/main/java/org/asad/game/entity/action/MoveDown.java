package org.asad.game.entity.action;

import org.asad.game.entity.chapter.Chapter;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.exception.NoSpaceToMoveException;
import org.asad.game.helper.GameUtil;

/**
 * this Class is responsible to move hero downwards.
 */
public class MoveDown implements Action {

    @Override
    public void execute(Chapter chapter) throws NoSpaceToMoveException, LowAttributeException, HighAttributeException {
        if (chapter.getHero().getLocation().getX() < (chapter.getMap().length-1)) {
            GameUtil.moveHero(chapter, chapter.getHero().getLocation().getX()+1, chapter.getHero().getLocation().getY());
        } else {
            throw new NoSpaceToMoveException("No Space left to execute down in the Map");
        }
    }
}
