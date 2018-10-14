package org.asad.game.event;

import org.asad.game.MockData;
import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.Place;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.helper.PlayerAttributeAccumulator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WorkEventTest {

    Place place;

    WorkEvent workEvent;

    @Before
    public void setUp() {
        place = MockData.getHideOut();
        workEvent = new WorkEvent(place);
    }

    @Test
    public void occurEvent() {
        Hero hero = MockData.getHero();
        int newHealth = PlayerAttributeAccumulator.getNewHealth(place.getHealth(), hero.getHealth());
        int newMoney = PlayerAttributeAccumulator.getNewMoney(place.getMoney(), hero.getMoney());
        int newAnxiety = PlayerAttributeAccumulator.getNewAnxiety(place.getAnxiety(), hero.getAnxiety());
        int newSkill = PlayerAttributeAccumulator.getNewSkill(place.getSkill(), hero.getSkill());
        workEvent.occurEvent(hero);
        Assert.assertEquals(hero.getHealth(), newHealth);
        Assert.assertEquals(hero.getMoney(), newMoney);
        Assert.assertEquals(hero.getAnxiety(), newAnxiety);
        Assert.assertEquals(hero.getSkill(), newSkill);
    }

    @Test(expected = HighAttributeException.class)
    public void isAllowedForEvent_LowMoney() throws LowAttributeException, HighAttributeException {
        Hero hero = MockData.getHero();
        hero.setMoney(PlayerAttributeAccumulator.MAX_MONEY);
        workEvent.isAllowedForEvent(hero);
    }

    @Test(expected = LowAttributeException.class)
    public void isAllowedForEvent_LowHealth() throws LowAttributeException, HighAttributeException {
        Hero hero = MockData.getHero();
        hero.setHealth(10);
        workEvent.isAllowedForEvent(hero);
    }

    @Test(expected = HighAttributeException.class)
    public void isAllowedForEvent_HighAnxiety() throws LowAttributeException, HighAttributeException {
        Hero hero = MockData.getHero();
        hero.setAnxiety(91);
        workEvent.isAllowedForEvent(hero);
    }

    @Test
    public void isAllowedForEvent() throws LowAttributeException, HighAttributeException {
        Hero hero = MockData.getHero();
        boolean value = workEvent.isAllowedForEvent(hero);
        Assert.assertTrue(value);
    }

}