package org.asad.game.helper;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConsoleLogger.class)
@Ignore
public class ConsoleLoggerTest {

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(ConsoleLogger.class);
        PowerMockito.doNothing().when(ConsoleLogger.class, "println", Mockito.anyString());
        PowerMockito.doNothing().when(ConsoleLogger.class, "print", Mockito.anyString());
        PowerMockito.doNothing().when(ConsoleLogger.class, "printPoster", Mockito.anyString());
    }

    @Test
    public void print() {
        ConsoleLogger.print("xyz");
    }

    @Test
    public void println() {
        ConsoleLogger.print("xyz");
    }

    @Test
    public void printPoster() {
        ConsoleLogger.printPoster("won");
    }
}