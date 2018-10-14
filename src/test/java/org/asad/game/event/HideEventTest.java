package org.asad.game.event;

import org.asad.game.MockData;
import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.Place;
import org.asad.game.helper.PlayerAttributeAccumulator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HideEventTest {

    Place place;

    HideEvent hideEvent;

    @Before
    public void setUp() {
        place = MockData.getHideOut();
        hideEvent = new HideEvent(place);
    }

    @Test
    public void occurEvent() {
        Hero hero = MockData.getHero();
        int newHealth = PlayerAttributeAccumulator.getNewHealth(place.getHealth(), hero.getHealth());
        int newMoney = PlayerAttributeAccumulator.getNewMoney(place.getMoney(), hero.getMoney());
        int newAnxiety = PlayerAttributeAccumulator.getNewAnxiety(place.getAnxiety(), hero.getAnxiety());
        int newSkill = PlayerAttributeAccumulator.getNewSkill(place.getSkill(), hero.getSkill());

        hideEvent.occurEvent(hero);
        Assert.assertEquals(hero.getHealth(), newHealth);
        Assert.assertEquals(hero.getMoney(), newMoney);
        Assert.assertEquals(hero.getAnxiety(), newAnxiety);
        Assert.assertEquals(hero.getSkill(), newSkill);
    }

    @Test
    public void isAllowedForEvent() {
        Hero hero = MockData.getHero();
        boolean value = hideEvent.isAllowedForEvent(hero);
        Assert.assertTrue(value);
    }

}