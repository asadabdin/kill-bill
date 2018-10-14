package org.asad.game.entity.player;

/**
 * Factory class responsible to produce the {@link Player}
 */
public class PlayerFactory {

    /**
     * it creates a Hero with Name as an argument.
     *
     * @param name
     * @return
     */
    public static Player createHero(String name) {
        return new Player.Builder(name)
                .withHealth(20)
                .withMoney(100)
                .withAnxiety(70)
                .withSkill(10)
                .withColor("\u001b[32m").build();
    }

    /**
     * it creates the Opponent based on the {@link PlayerEnum} as an argument.
     *
     * @param playerEnum
     * @return
     */
    public static Player createPlayer(PlayerEnum playerEnum) {
        Player.Builder playerBuilder = null;
        if (playerEnum == PlayerEnum.Sofie) {
            playerBuilder = new Player.Builder(PlayerEnum.Sofie.playerName)
                    .withHealth(70)
                    .withMoney(1200)
                    .withAnxiety(20)
                    .withSkill(50)
                    .withChapterBoss(true)
                    .withColor("\u001b[35m");

        } else if (playerEnum == PlayerEnum.Gogo) {
            playerBuilder = new Player.Builder(PlayerEnum.Gogo.playerName)
                    .withHealth(60)
                    .withMoney(2500)
                    .withAnxiety(50)
                    .withSkill(60)
                    .withColor("\u001b[34m");

        } else if (playerEnum == PlayerEnum.O_Ren) {
            playerBuilder = new Player.Builder(PlayerEnum.O_Ren.playerName)
                    .withHealth(80)
                    .withMoney(3000)
                    .withAnxiety(50)
                    .withSkill(150)
                    .withChapterBoss(true)
                    .withColor("\u001b[36m");

        } else if (playerEnum == PlayerEnum.Budd) {
            playerBuilder = new Player.Builder(PlayerEnum.Budd.playerName)
                    .withHealth(70)
                    .withMoney(5000)
                    .withAnxiety(30)
                    .withSkill(150)
                    .withColor("\u001b[33m");

        } else if (playerEnum == PlayerEnum.Bill) {
            playerBuilder = new Player.Builder(PlayerEnum.Bill.playerName)
                    .withHealth(90)
                    .withMoney(100000)
                    .withAnxiety(50)
                    .withSkill(250)
                    .withChapterBoss(true)
                    .withColor("\u001b[31m");
        }
        return playerBuilder.build();
    }
}
