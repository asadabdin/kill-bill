package org.asad.game;

import org.asad.game.helper.ConsoleLogger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConsoleLogger.class)
public class GameStarterTest {


    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(ConsoleLogger.class);
        PowerMockito.doNothing().when(ConsoleLogger.class, "printPoster", Mockito.anyString());
    }

    @Test
    public void main() {
        GameStarter.main(new String[0]);
        PowerMockito.verifyStatic(ConsoleLogger.class, VerificationModeFactory.times(1));
        ConsoleLogger.printPoster("poster");
    }
}