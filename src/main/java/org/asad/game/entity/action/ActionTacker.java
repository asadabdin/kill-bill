package org.asad.game.entity.action;

import org.asad.game.entity.chapter.Chapter;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.exception.NoSpaceToMoveException;

/**
 * Following Command pattern this is an invoker class.
 * responsible to invoke the commands.
 */
public class ActionTacker {

    /**
     * {@link Action} what needs to be executed.
     *
     * @see MoveDown
     * @see MoveUp
     * @see MoveLeft
     * @see MoveRight
     * @see QuitGame
     * @see SaveGame
     */
    private Action action;

    /**
     * set {@link Action}
     *
     * @param action
     */
    public void setCommand(Action action){
        this.action = action;
    }

    /**
     * Takes Action which has passed through {@link #setCommand(Action)}
     *
     * @param chapter
     * @throws NoSpaceToMoveException
     * @throws LowAttributeException
     * @throws HighAttributeException
     */
    public void takeAction(Chapter chapter) throws NoSpaceToMoveException, LowAttributeException, HighAttributeException {
        if (null != action) {
            action.execute(chapter);
            action = null;
        }
    }
}
