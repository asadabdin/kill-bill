package org.asad.game.event;

import org.asad.game.MockData;
import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.Place;
import org.asad.game.helper.PlayerAttributeAccumulator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FightEventTest {

    Place place;

    FightEvent fightEvent;

    @Before
    public void setUp() {
        place = MockData.getChapterBoss_O_Ren_Place();
        fightEvent = new FightEvent(place);
    }

    @Test
    public void occurEvent_win() {
        Hero hero = MockData.getHero();
        hero.setHealth(100);
        hero.setSkill(151);
        int newHealth = PlayerAttributeAccumulator.getNewHealth(hero.getHealth() - (place.getPlayer().getHealth() / 2), 0);
        int newMoney = PlayerAttributeAccumulator.getNewMoney(hero.getMoney() + (place.getPlayer().getMoney() / 2), 0);
        int newAnxiety = PlayerAttributeAccumulator.getNewAnxiety((hero.getAnxiety() / 3) + place.getPlayer().getAnxiety(), 0);
        int newSkill = PlayerAttributeAccumulator.getNewSkill(place.getPlayer().getSkill(), hero.getSkill());

        fightEvent.occurEvent(hero);
        Assert.assertEquals(hero.getHealth(), newHealth);
        Assert.assertEquals(hero.getMoney(), newMoney);
        Assert.assertEquals(hero.getAnxiety(), newAnxiety);
        Assert.assertEquals(hero.getSkill(), newSkill);
    }

    @Test
    public void occurEvent_Lose() {
        Hero hero = MockData.getHero();
        fightEvent.occurEvent(hero);
        Assert.assertEquals(hero.getHealth(), 0);
    }

    @Test
    public void isAllowedForEvent_HeroReadyForBossFight() {
        Hero hero = MockData.getHero();
        hero.setReadyForBossFight(true);
        boolean value = fightEvent.isAllowedForEvent(hero);
        Assert.assertTrue(value);
    }

    @Test
    public void isAllowedForEvent_BossFight() {
        place = MockData.getChapterBoss_Gogo_Place();
        fightEvent = new FightEvent(place);
        Hero hero = MockData.getHero();
        hero.setHealth(10);
        fightEvent.isAllowedForEvent(hero);
    }

}