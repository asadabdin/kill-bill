package org.asad.game.entity.action;

import org.asad.game.entity.chapter.Chapter;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.exception.NoSpaceToMoveException;
import org.asad.game.helper.GameUtil;

/**
 * Following Command pattern,
 * Every Action should implements this Interface.
 *
 * @see MoveDown
 * @see MoveUp
 * @see MoveLeft
 * @see MoveRight
 * @see QuitGame
 * @see SaveGame
 */
public interface Action {

    /**
     * it check is the space left to move before Moving
     * and then call {@link GameUtil#moveHero(Chapter, int, int)} with coordinates to move.
     *
     * @param chapter
     * @throws NoSpaceToMoveException
     * @throws LowAttributeException
     * @throws HighAttributeException
     */
    void execute(Chapter chapter) throws NoSpaceToMoveException, LowAttributeException, HighAttributeException;
}
