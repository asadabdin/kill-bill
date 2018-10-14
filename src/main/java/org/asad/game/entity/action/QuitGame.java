package org.asad.game.entity.action;

import org.asad.game.entity.chapter.Chapter;
import org.asad.game.helper.ConsoleLogger;
import org.asad.game.helper.GameUtil;

/**
 * it Trigger the {@link GameUtil#exitGame()} to quit the game.
 */
public class QuitGame implements Action {

    /**
     * it takes the user input before quiting the game.
     */
    @Override
    public void execute(Chapter chapter) {
        String input;
        do {
            ConsoleLogger.println("Are you sure want to quit : Press 'y' for Yes -- 'n' for No");
            input = GameUtil.readUserInput();
            switch (input) {
                case "y":
                    ConsoleLogger.println("You Choose to quit the Game");
                    saveBeforeQuit(chapter);
                    break;
                case "n":
                    ConsoleLogger.println("You Choose not to quit the Game");
                    break;
                default:
                    ConsoleLogger.println("Not an valid action");
            }
        } while (null == input || !(input.equals("y") || input.equals("n")));
        if (null != input && input.equals("y")) {
            GameUtil.exitGame();
        }
    }

    /**
     * it Asks to save the Game before quiting by invoking {@link GameUtil#saveGame(Chapter)}.
     */
    private void saveBeforeQuit(Chapter chapter) {
        String input;
        do {
            ConsoleLogger.println("Would like to save the game before quit : Press 'y' for Yes -- 'n' for No");
            input = GameUtil.readUserInput();
            switch (input) {
                case "y":
                    ConsoleLogger.println("You Choose to Save the Game");
                    GameUtil.saveGame(chapter);
                    break;
                case "n":
                    ConsoleLogger.println("You Choose not to save the Game");
                    ConsoleLogger.println("See you again");
                    break;
                default:
                    ConsoleLogger.println("Not an valid action");
            }
        } while (null == input || !(input.equals("y") || input.equals("n")));
    }
}
