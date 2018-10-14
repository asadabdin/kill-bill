package org.asad.game.executer;

import org.asad.game.entity.action.*;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.exception.NoSpaceToMoveException;
import org.asad.game.helper.ConsoleLogger;
import org.asad.game.helper.GameUtil;

import java.util.concurrent.BlockingQueue;

/**
 * Bride Thread handles Movement action of Hero.
 */
public class BrideThread implements Runnable {

    private BlockingQueue<Chapter> inQueue;
    private BlockingQueue<Chapter> outQueue;

    public BrideThread(BlockingQueue<Chapter> inQueue, BlockingQueue<Chapter> outQueue) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    /**
     * This Method is responsible for capturing the movement.
     */
    @Override
    public void run() {
        Chapter chapter;
        ActionTacker actionTacker = new ActionTacker();
        Action moveUp = new MoveUp();
        Action moveDown = new MoveDown();
        Action moveLeft = new MoveLeft();
        Action moveRight = new MoveRight();
        Action saveGame = new SaveGame();
        Action quitGame = new QuitGame();
        try {
            while ((chapter = inQueue.take()) != null) {
                if (chapter.getPlaces().stream().filter(place -> place.getPlayer() != null).count() != 0) {
                    String input;
                    do {
                        ConsoleLogger.println("Take Action for your character :- press 'w' to go up -- 'a' to go left -- 's' to go down -- 'd' to go right -- 'x' to quit -- 'p' to save the game");
                        input = GameUtil.readUserInput();
                        switch (input) {
                            case "w":
                                ConsoleLogger.println("You Choose to go up");
                                actionTacker.setCommand(moveUp);
                                break;
                            case "a":
                                ConsoleLogger.println("You Choose to go left");
                                actionTacker.setCommand(moveLeft);
                                break;
                            case "s":
                                ConsoleLogger.println("You Choose to go down");
                                actionTacker.setCommand(moveDown);
                                break;
                            case "d":
                                ConsoleLogger.println("You Choose to go right");
                                actionTacker.setCommand(moveRight);
                                break;
                            case "x":
                                ConsoleLogger.println("You Choose to quit the game");
                                actionTacker.setCommand(quitGame);
                                break;
                            case "p":
                                ConsoleLogger.println("Your Choose to save the game");
                                actionTacker.setCommand(saveGame);
                                break;
                            default:
                                ConsoleLogger.println("Not an valid action");
                        }
                    }
                    while (null == input || !(input.equals("w") || input.equals("s") || input.equals("d") || input.equals("a") || input.equals("x") || input.equals("p")));
                }
                try {
                    actionTacker.takeAction(chapter);
                } catch (NoSpaceToMoveException | LowAttributeException | HighAttributeException e) {
                    ConsoleLogger.println(e.getMessage());
                } finally {
                    outQueue.put(chapter);
                }
            }
        } catch (InterruptedException e) {
            ConsoleLogger.println(e.getMessage());
        }
    }
}
