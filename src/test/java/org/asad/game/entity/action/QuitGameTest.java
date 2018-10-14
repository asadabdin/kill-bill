package org.asad.game.entity.action;

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
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConsoleLogger.class, GameUtil.class})
public class QuitGameTest {

    @Mock
    private Chapter chapter;

    @InjectMocks
    private QuitGame quitGame;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(ConsoleLogger.class);
        PowerMockito.mockStatic(GameUtil.class);
        PowerMockito.doNothing().when(ConsoleLogger.class, "println", Mockito.anyString());
        PowerMockito.doNothing().when(GameUtil.class, "exitGame");
        quitGame = Mockito.spy(quitGame);
    }

    @Test
    public void quit_withSave() {
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("y");
        quitGame.execute(chapter);
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(2));
        GameUtil.readUserInput();
    }

    @Test
    public void quit_withOutSave() {
        PowerMockito.when(GameUtil.readUserInput()).thenAnswer(new Answer<String>() {
            int count =0;
            @Override
            public String answer(InvocationOnMock invocation) {
                if (++count == 1)
                    return "y";

                return "n";
            }
        });
        quitGame.execute(chapter);
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(2));
        GameUtil.readUserInput();
    }

    @Test
    public void dontQuit() {
        PowerMockito.when(GameUtil.readUserInput()).thenReturn("n");
        quitGame.execute(chapter);
        PowerMockito.verifyStatic(GameUtil.class,VerificationModeFactory.times(1));
        GameUtil.readUserInput();
    }

}