package org.asad.game.entity.player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerFactoryTest {


    @Test
    public void createHero() {
        Player hero = PlayerFactory.createHero("Asad");
        Assert.assertEquals(hero.getName(), "Asad");
    }

    @Test
    public void createPlayer_Sofie() {
        Player player = PlayerFactory.createPlayer(PlayerEnum.Sofie);
        Assert.assertEquals(player.getName(), PlayerEnum.Sofie.getPlayerName());
    }

    @Test
    public void createPlayer_Gogo() {
        Player player = PlayerFactory.createPlayer(PlayerEnum.Gogo);
        Assert.assertEquals(player.getName(), PlayerEnum.Gogo.getPlayerName());
    }

    @Test
    public void createPlayer_O_Ren() {
        Player player = PlayerFactory.createPlayer(PlayerEnum.O_Ren);
        Assert.assertEquals(player.getName(), PlayerEnum.O_Ren.getPlayerName());
    }

    @Test
    public void createPlayer_Budd() {
        Player player = PlayerFactory.createPlayer(PlayerEnum.Budd);
        Assert.assertEquals(player.getName(), PlayerEnum.Budd.getPlayerName());
    }

    @Test
    public void createPlayer_Bill() {
        Player player = PlayerFactory.createPlayer(PlayerEnum.Bill);
        Assert.assertEquals(player.getName(), PlayerEnum.Bill.getPlayerName());
    }
}