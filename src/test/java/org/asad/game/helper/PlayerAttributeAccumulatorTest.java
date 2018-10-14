package org.asad.game.helper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class PlayerAttributeAccumulatorTest {

    @Test
    public void getNewHealth() {
        int health = PlayerAttributeAccumulator.getNewHealth(1, 1);
        Assert.assertNotEquals(health, 0);
        Assert.assertEquals(health, 2);
    }

    @Test
    public void getNewSkill() {
        int skill = PlayerAttributeAccumulator.getNewSkill(1, 0);
        Assert.assertEquals(skill, 0);
    }

    @Test
    public void getNewAnxiety() {
        int anxiety = PlayerAttributeAccumulator.getNewAnxiety(1, 100);
        Assert.assertNotEquals(anxiety, 0);
        Assert.assertEquals(anxiety, 51);
    }

    @Test
    public void getNewMoney() {
        int money = PlayerAttributeAccumulator.getNewMoney(2, 400000);
        Assert.assertNotEquals(money, 0);
        Assert.assertEquals(money, 400000);
    }
}