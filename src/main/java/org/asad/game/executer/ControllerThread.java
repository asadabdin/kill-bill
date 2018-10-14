package org.asad.game.executer;

import org.asad.game.draw.PrintChainInterface;
import org.asad.game.draw.PrintMapChain;
import org.asad.game.draw.PrintStatsChain;
import org.asad.game.entity.Location;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.entity.chapter.ChapterFactory;
import org.asad.game.helper.ConsoleLogger;
import org.asad.game.helper.GameUtil;

import java.util.concurrent.BlockingQueue;

/**
 * this thread manges the complete game except the movement of Hero.
 */
public class ControllerThread implements Runnable {

    private Chapter chapter;
    private BlockingQueue<Chapter> inQueue;
    private BlockingQueue<Chapter> outQueue;
    private PrintChainInterface printStatusChain;
    private Location heroLastLocation;
    int placesCount;


    public ControllerThread(BlockingQueue<Chapter> inQueue, BlockingQueue<Chapter> outQueue) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    /**
     * it takes care of populating the map and manage the flow of the game.
     */
    @Override
    public void run() {
        if (GameUtil.hasFoundPreviousSaveGameToResume()) {
            do {
                ConsoleLogger.println("Choose correct option : Press 'n' for new Game --  'r' to resume Game");
                String input = GameUtil.readUserInput();
                switch (input) {
                    case "n":
                        ConsoleLogger.println("You Choose to start new Game");
                        startNewChapter();
                        break;
                    case "r":
                        ConsoleLogger.println("You Choose to resume the game");
                        chapter = GameUtil.resumeGame();
                        initiatePrintStatusChain();
                        break;
                    default:
                        ConsoleLogger.println("Not an valid action");
                }
            } while (chapter == null);
        } else {
            startNewChapter();
        }
        placesCount = chapter.getPlaces().size();
        try {
            executor();
        } catch (InterruptedException e) {
            ConsoleLogger.print(e.getMessage());
        }
    }

    /**
     * this handles whether hero needs more friendly places and flow of the game.
     * @throws InterruptedException
     */
    private void executor() throws InterruptedException {
        do {
            if (null == heroLastLocation || heroLastLocation != chapter.getHero().getLocation()) {
                heroLastLocation = chapter.getHero().getLocation();
                if (placesCount != chapter.getPlaces().size()) {
                    placesCount = chapter.getPlaces().size();
                    if (chapter.getHero().getHealth() > 0) {
                        if (chapter.getPlaces().stream().filter(place -> null != place.getPlayer()).count() == 0) {
                            startNewChapter();
                        }
                    } else {
                        ConsoleLogger.println("You Lost the fight");
                        ConsoleLogger.printPoster("gameOver");
                        GameUtil.exitGame();
                    }
                    GameUtil.checkAndCreateNewPlacesIfRequired(chapter);
                }
                printStatusChain.draw();
            }
            outQueue.put(chapter);
        } while((chapter = inQueue.take()) != null);
    }

    private void startNewChapter() {
        chapter = ChapterFactory.startNewChapter(chapter);
        initiatePrintStatusChain();
    }

    /**
     * initialize the {@link PrintChainInterface} to draw the map and Player stats.
     */
    private void initiatePrintStatusChain() {
        if (null != chapter) {
            printStatusChain = new PrintStatsChain(chapter);
            printStatusChain.setNextPaintChain(new PrintMapChain(chapter));
        }
    }
}
