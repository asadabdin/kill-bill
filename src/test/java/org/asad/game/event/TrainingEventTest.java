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

public class TrainingEventTest {

    Place place;

    TrainingEvent trainingEvent;

    @Before
    public void setUp() {
        place = MockData.getHideOut();
        trainingEvent = new TrainingEvent(place);
    }

    @Test
    public void occurEvent() {
        Hero hero = MockData.getHero();
        int newHealth = PlayerAttributeAccumulator.getNewHealth(place.getHealth(), hero.getHealth());
        int newMoney = PlayerAttributeAccumulator.getNewMoney(hero.getMoney() - place.getMoney(), 0);
        int newAnxiety = PlayerAttributeAccumulator.getNewAnxiety(place.getAnxiety(), hero.getAnxiety());
        int newSkill = PlayerAttributeAccumulator.getNewSkill(place.getSkill(), hero.getSkill());
        trainingEvent.occurEvent(hero);
        Assert.assertEquals(hero.getHealth(), newHealth);
        Assert.assertEquals(hero.getMoney(), newMoney);
        Assert.assertEquals(hero.getAnxiety(), newAnxiety);
        Assert.assertEquals(hero.getSkill(), newSkill);
    }

    @Test(expected = LowAttributeException.class)
    public void isAllowedForEvent_HighMoney() throws LowAttributeException, HighAttributeException {
        Hero hero = MockData.getHero();
        hero.setMoney(10);
        trainingEvent.isAllowedForEvent(hero);
    }

    @Test(expected = LowAttributeException.class)
    public void isAllowedForEvent_LowHealth() throws LowAttributeException, HighAttributeException {
        Hero hero = MockData.getHero();
        hero.setHealth(10);
        trainingEvent.isAllowedForEvent(hero);
    }

    @Test(expected = HighAttributeException.class)
    public void isAllowedForEvent_HighAnxiety() throws LowAttributeException, HighAttributeException {
        Hero hero = MockData.getHero();
        hero.setMoney(10001);
        hero.setAnxiety(71);
        trainingEvent.isAllowedForEvent(hero);
    }

    @Test
    public void isAllowedForEvent() throws LowAttributeException, HighAttributeException {
        Hero hero = MockData.getHero();
        hero.setMoney(10001);
        hero.setHealth(100);
        boolean value = trainingEvent.isAllowedForEvent(hero);
        Assert.assertTrue(value);
    }


}