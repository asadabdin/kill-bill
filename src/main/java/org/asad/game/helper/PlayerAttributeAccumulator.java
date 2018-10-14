package org.asad.game.helper;

/**
 * this util class helps to calculate the new attributes.
 */
public class PlayerAttributeAccumulator {

    /**
     * Max health a Hero can get
     */
    private static final int MAX_HEALTH = 100;

    /**
     * Max Anxiety a Hero can Reach
     */
    private static final int MAX_ANXIETY = 100;

    /**
     * Max Money a hero can Earn
     */
    public static final int MAX_MONEY = 400000;

    /**
     * Max Skill a Hero can earn
     */
    private static final int MAX_SKILL = 5000;


    /**
     * this help to get the new Health
     *
     * @param opponentHealth
     * @param heroHealth
     * @return
     */
    public static int getNewHealth(int opponentHealth, int heroHealth) {
        return ceil(opponentHealth, heroHealth, MAX_HEALTH);
    }

    /**
     * this helps to get new Skill.
     *
     * @param opponentSkill
     * @param heroSkill
     * @return
     */
    public static int getNewSkill(int opponentSkill, int heroSkill) {
        return ceil(opponentSkill/3, heroSkill, MAX_SKILL);
    }

    /**
     * this helps to get new Anxiety value.
     *
     * @param opponentAnxiety
     * @param heroAnxiety
     * @return
     */
    public static int getNewAnxiety(int opponentAnxiety, int heroAnxiety) {
        return ceil(opponentAnxiety, heroAnxiety/2, MAX_ANXIETY);
    }

    /**
     * this helps to get new Money.
     *
     * @param opponentMoney
     * @param heroMoney
     * @return
     */
    public static int getNewMoney(int opponentMoney, int heroMoney) {
        return ceil(opponentMoney/2, heroMoney, MAX_MONEY);
    }

    /**
     * this ceil the calculation to upper an lower limit passes as an arguments.
     *
     * @param opponentData
     * @param heroData
     * @param ceilTo
     * @return
     */
    private static int ceil(int opponentData, int heroData, int ceilTo) {
        heroData = opponentData + heroData;
        return heroData > 0 ? heroData > ceilTo ? ceilTo : heroData : 0;
    }
}
