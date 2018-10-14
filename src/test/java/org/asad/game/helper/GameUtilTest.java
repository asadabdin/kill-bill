package org.asad.game.helper;

import org.asad.game.MockData;
import org.asad.game.entity.chapter.Chapter;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConsoleLogger.class)
public class GameUtilTest {

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(ConsoleLogger.class);
        PowerMockito.doNothing().when(ConsoleLogger.class, "println", Mockito.anyString());
        PowerMockito.doNothing().when(ConsoleLogger.class, "print", Mockito.anyString());
    }

    @Test
    public void moveHero_NotReadyForBossFight() throws LowAttributeException, HighAttributeException {
        Chapter chapter = MockData.getChapter();
        GameUtil.moveHero(chapter, 1, 1);
    }

    @Test
    public void moveHero_ReadyForBossFight() throws LowAttributeException, HighAttributeException {
        Chapter chapter = MockData.getChapter();
        chapter.getHero().setReadyForBossFight(true);
        GameUtil.moveHero(chapter, 1, 1);
        Assert.assertEquals(chapter.getHero().getLocation().getX(), 1);
        Assert.assertEquals(chapter.getHero().getLocation().getY(), 1);
    }

    @Test
    public void moveHero_MakeHeroReadyForBossFight() throws LowAttributeException, HighAttributeException {
        Chapter chapter = MockData.getChapter();
        chapter.getMap()[1][1] = null;
        Assert.assertFalse(chapter.getHero().isReadyForBossFight());
        GameUtil.moveHero(chapter, 1, 1);
        Assert.assertEquals(chapter.getHero().getLocation().getX(), 1);
        Assert.assertEquals(chapter.getHero().getLocation().getY(), 1);
        Assert.assertTrue(chapter.getHero().isReadyForBossFight());
    }

    @Test
    public void saveGame() {
        File file = new File("killBill.save");
        if (file.exists()) {
            file.delete();
        }
        GameUtil.saveGame(MockData.getChapter());
        Assert.assertTrue(file.exists());
    }

    @Test
    public void checkAndCreateNewPlacesIfRequired() {
        Chapter chapter = MockData.getChapter();
        chapter.getHero().setHealth(10);
        chapter.getPlaces().removeIf(place -> place.getPlayer() == null);
        GameUtil.checkAndCreateNewPlacesIfRequired(chapter);
        Assert.assertEquals(chapter.getPlaces().size(), 5);
    }
}