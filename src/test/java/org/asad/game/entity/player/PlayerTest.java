package org.asad.game.entity.player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void testPLayer() {
        Player player = new Player.Builder("Asad")
                .withAnxiety(100)
                .withColor("xyz")
                .withHealth(50)
                .withMoney(1000)
                .withSkill(200)
                .withChapterBoss(true)
                .build();

        Assert.assertNotNull(player);
        Assert.assertEquals(player.getName(), "Asad");
        Assert.assertEquals(player.getAnxiety(), 100);
        Assert.assertEquals(player.getColor(), "xyz");
        Assert.assertEquals(player.getHealth(), 50);
        Assert.assertEquals(player.getMoney(), 1000);
        Assert.assertEquals(player.getSkill(), 200);
        Assert.assertTrue(player.isChapterBoss());
    }

}