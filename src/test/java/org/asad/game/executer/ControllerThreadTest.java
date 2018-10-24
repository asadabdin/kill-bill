package org.asad.game.executer;

import org.asad.game.MockData;
import org.asad.game.draw.PrintChainInterface;
import org.asad.game.entity.Location;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.entity.chapter.ChapterFactory;
import org.asad.game.helper.ConsoleLogger;
import org.asad.game.helper.GameUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.concurrent.BlockingQueue;

import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConsoleLogger.class, GameUtil.class, ChapterFactory.class})
public class ControllerThreadTest {

    @InjectMocks
    private ControllerThread controllerThread;

    @Mock
    BlockingQueue<Chapter> inQueue;

    @Mock
    BlockingQueue<Chapter> outQueue;

    @Mock
    private PrintChainInterface printStatusChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(ConsoleLogger.class);
        PowerMockito.mockStatic(GameUtil.class);
        PowerMockito.mockStatic(ChapterFactory.class);
        PowerMockito.doNothing().when(printStatusChain).draw();
        PowerMockito.doNothing().when(GameUtil.class, "exitGame");
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("Asad Abdin :)");
        PowerMockito.when(GameUtil.isGameOver()).thenAnswer(new Answer() {
            private int count = 0;
            public Boolean answer(InvocationOnMock invocation) {
                if (++count == 1)
                    return false;

                return true;
            }
        });

        PowerMockito.doNothing().when(ConsoleLogger.class, "println", Mockito.anyString());
        PowerMockito.doNothing().when(ConsoleLogger.class, "print", Mockito.anyString());
        PowerMockito.doNothing().when(ConsoleLogger.class, "printPoster", Mockito.anyString());
        PowerMockito.when(ChapterFactory.class, "startNewChapter", null).thenReturn(MockData.getChapter());
        controllerThread = spy(controllerThread);
    }

    @Test
    public void run_NewGame() throws InterruptedException {
        PowerMockito.when(GameUtil.hasFoundPreviousSaveGameToResume()).thenReturn(true);
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("n");
        controllerThread.run();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.hasFoundPreviousSaveGameToResume();
        Mockito.verify(outQueue, Mockito.atLeastOnce()).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_NewGameByNoSaveGameFound() throws InterruptedException {
        controllerThread.run();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.hasFoundPreviousSaveGameToResume();
        Mockito.verify(outQueue, Mockito.atLeastOnce()).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_resumeGame() throws InterruptedException {
        PowerMockito.when(GameUtil.hasFoundPreviousSaveGameToResume()).thenReturn(true);
        PowerMockito.when(GameUtil.resumeGame()).thenReturn(MockData.getChapter());
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("r");
        controllerThread.run();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.hasFoundPreviousSaveGameToResume();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.resumeGame();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.readUserInput();
        Mockito.verify(outQueue, Mockito.atLeastOnce()).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_invalidInput() throws InterruptedException {
        Whitebox.setInternalState(controllerThread, "chapter", MockData.getChapter());
        Whitebox.setInternalState(controllerThread, "printStatusChain", printStatusChain);
        PowerMockito.when(GameUtil.hasFoundPreviousSaveGameToResume()).thenReturn(true);
        PowerMockito.when(GameUtil.readUserInput()).then(new Answer<String>() {
            int count =0;
            @Override
            public String answer(InvocationOnMock invocation) {
                if (++count == 1)
                    return "x";

                return "n";
            }
        });
        controllerThread.run();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.readUserInput();
        Mockito.verify(outQueue, Mockito.atLeastOnce()).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_createNewPlaces() throws InterruptedException {
        Chapter chapter = MockData.getChapter();
        chapter.getPlaces().removeIf(place -> place.getPlayer() != null);
        Mockito.doAnswer(new Answer() {
            private int count = 0;
            public Chapter answer(InvocationOnMock invocation) {
                if (++count == 1)
                    return chapter;

                return null;
            }
        }).when(inQueue).take();
        controllerThread.run();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.hasFoundPreviousSaveGameToResume();
        Mockito.verify(outQueue, Mockito.atLeastOnce()).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_loose() throws InterruptedException {
        Chapter chapter = MockData.getChapter();
        chapter.getPlaces().removeIf(place -> true);
        chapter.getHero().setHealth(0);
        PowerMockito.when(GameUtil.isGameOver()).thenAnswer(new Answer() {
            private int count = 0;
            public Boolean answer(InvocationOnMock invocation) {
                count++;
                if (count == 1 || count == 2 ) {
                    return false;
                }

                return true;
            }
        });
        Mockito.doAnswer(new Answer() {
            private int count = 0;
            public Chapter answer(InvocationOnMock invocation) {
                if (++count == 1)
                    return chapter;

                return null;
            }
        }).when(inQueue).take();
        controllerThread.run();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.hasFoundPreviousSaveGameToResume();
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.atLeastOnce());
        GameUtil.exitGame();
        Mockito.verify(outQueue, Mockito.atLeastOnce()).put(Mockito.any(Chapter.class));
    }
}