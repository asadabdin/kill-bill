package org.asad.game;

import org.asad.game.entity.chapter.Chapter;
import org.asad.game.executer.BrideThread;
import org.asad.game.executer.ControllerThread;
import org.asad.game.helper.ConsoleLogger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameStarter {

    /**
     * this method Starts the game by creating a thread pool of capacity 2.
     * and starting 2 threads
     *  1. {@link ControllerThread} which control the whole game.
     *  2. {@link BrideThread} which grab the action of movement.
     *
     * @param args
     */
    public static void main(String[] args) {
        ConsoleLogger.printPoster("poster");
        BlockingQueue<Chapter> controllerQueue = new ArrayBlockingQueue<>(1);
        BlockingQueue<Chapter> brideQueue = new ArrayBlockingQueue<>(1);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new ControllerThread(brideQueue, controllerQueue));
        service.submit(new BrideThread(controllerQueue, brideQueue));
        service.shutdown();
    }
}
