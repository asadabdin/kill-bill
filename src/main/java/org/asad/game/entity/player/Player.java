package org.asad.game.entity.player;

import org.asad.game.entity.AtomEntity;

/**
 * Player Builder class
 */
public class Player implements AtomEntity {

    private static final long serialVersionUID = -490088182239497357L;
    private String name;
    private int health;
    private int money;
    private int skill;
    private int anxiety;
    private String color;
    private boolean chapterBoss;

    private Player(Builder builder) {
        this.name = builder.name;
        this.health = builder.health;
        this.money = builder.money;
        this.skill = builder.skill;
        this.anxiety = builder.anxiety;
        this.color = builder.color;
        this.chapterBoss = builder.chapterBoss;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public int getSkill() {
        return skill;
    }

    @Override
    public int getAnxiety() {
        return anxiety;
    }

    @Override
    public String getColor() {
        return color;
    }

    public boolean isChapterBoss() {
        return chapterBoss;
    }

    public static final class Builder {

        private String name;
        private int health;
        private int money;
        private int skill;
        private int anxiety;
        private String color;
        private boolean chapterBoss;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withHealth(int health) {
            this.health = health;
            return this;
        }

        public Builder withMoney(int money) {
            this.money = money;
            return this;
        }

        public Builder withSkill(int skill) {
            this.skill = skill;
            return this;
        }

        public Builder withAnxiety(int anxiety) {
            this.anxiety = anxiety;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withChapterBoss(boolean chapterBoss) {
            this.chapterBoss = chapterBoss;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
