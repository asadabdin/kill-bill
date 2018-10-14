package org.asad.game.executer;

import org.asad.game.MockData;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.helper.ConsoleLogger;
import org.asad.game.helper.GameUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.BlockingQueue;

import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConsoleLogger.class, GameUtil.class})
public class BrideThreadTest {

    @InjectMocks
    private BrideThread brideThread;

    @Mock
    BlockingQueue<Chapter> inQueue;

    @Mock
    BlockingQueue<Chapter> outQueue;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(ConsoleLogger.class);
        PowerMockito.mockStatic(GameUtil.class);
        PowerMockito.doNothing().when(ConsoleLogger.class, "println", Mockito.anyString());
        PowerMockito.when(inQueue.take()).thenAnswer(new Answer() {
            private int count = 0;
            public Chapter answer(InvocationOnMock invocation) {
                if (++count == 1)
                    return MockData.getChapter();

                return null;
            }
        });
        brideThread = spy(brideThread);
    }

    @Test
    public void run_MoveUp() throws InterruptedException {
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("w");
        brideThread.run();
        Mockito.verify(outQueue, Mockito.times(1)).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_MoveDown() throws InterruptedException {
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("s");
        brideThread.run();
        Mockito.verify(outQueue, Mockito.times(1)).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_MoveLeft() throws InterruptedException {
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("a");
        brideThread.run();
        Mockito.verify(outQueue, Mockito.times(1)).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_MoveRight() throws InterruptedException {
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("d");
        brideThread.run();
        Mockito.verify(outQueue, Mockito.times(1)).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_Quit() throws InterruptedException {
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("x");
        PowerMockito.when(GameUtil.readUserInput()).thenAnswer(new Answer<String>() {
            int count =0;
            @Override
            public String answer(InvocationOnMock invocation) {
                count++;
                if (count == 1)
                    return "x";
                else if (count == 2)
                    return "y";
                else
                    return "n";
            }
        });
        brideThread.run();
        Mockito.verify(outQueue, Mockito.times(1)).put(Mockito.any(Chapter.class));
    }

    @Test
    public void run_Save() throws InterruptedException {
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("p");
        brideThread.run();
        Mockito.verify(outQueue, Mockito.times(1)).put(Mockito.any(Chapter.class));
    }

}