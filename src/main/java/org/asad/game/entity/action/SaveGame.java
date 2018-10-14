package org.asad.game.entity.action;

import org.asad.game.entity.chapter.Chapter;
import org.asad.game.helper.GameUtil;

/**
 * it Trigger the {@link GameUtil#saveGame(Chapter)} to save the game.
 */
public class SaveGame implements Action {

    @Override
    public void execute(Chapter chapter) {
        GameUtil.saveGame(chapter);
    }
}
