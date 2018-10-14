package org.asad.game.entity.chapter;

import org.asad.game.MockData;
import org.asad.game.entity.Location;
import org.asad.game.helper.ConsoleLogger;
import org.asad.game.helper.GameUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConsoleLogger.class, GameUtil.class})
public class ChapterFactoryTest {

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(ConsoleLogger.class);
        PowerMockito.mockStatic(GameUtil.class);
        PowerMockito.doNothing().when(GameUtil.class, "exitGame");
        PowerMockito.doNothing().when(ConsoleLogger.class, "println", Mockito.anyString());
        PowerMockito.doNothing().when(ConsoleLogger.class, "printPoster", Mockito.anyString());
    }

    @Test
    public void startNewChapter_one() {
        Chapter chapter = ChapterFactory.startNewChapter(null);
        Assert.assertNotNull(chapter);
        Assert.assertEquals(chapter.getChapterLevel(), ChapterLevel.ONE);
        Assert.assertNotNull(chapter.getHero());
        Assert.assertNotNull(chapter.getPlaces());
        Assert.assertNotNull(chapter.getMap());
    }

    @Test
    public void startNewChapter_two() {
        Chapter chapter = MockData.getChapter();
        chapter = ChapterFactory.startNewChapter(chapter);
        Assert.assertNotNull(chapter);
        Assert.assertEquals(chapter.getChapterLevel(), ChapterLevel.TWO);
        Assert.assertNotNull(chapter.getHero());
        Assert.assertNotNull(chapter.getPlaces());
        Assert.assertNotNull(chapter.getMap());
    }

    @Test
    public void startNewChapter_three() {
        Chapter chapter = MockData.getChapter();
        Whitebox.setInternalState(chapter, "chapterLevel", ChapterLevel.TWO);
        chapter = ChapterFactory.startNewChapter(chapter);
        Assert.assertNotNull(chapter);
        Assert.assertEquals(chapter.getChapterLevel(), ChapterLevel.THREE);
        Assert.assertNotNull(chapter.getHero());
        Assert.assertNotNull(chapter.getPlaces());
        Assert.assertNotNull(chapter.getMap());
    }

    @Test
    public void getUniqueLocation() {
        Chapter chapter = MockData.getChapter();
        Location location = ChapterFactory.getUniqueLocation(chapter.getPlaces());
        Assert.assertNotNull(location);
        chapter.getPlaces().forEach(place -> Assert.assertNotEquals(location, place.getLocation()));
    }
}