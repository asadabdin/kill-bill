package org.asad.game.entity.place;

import org.asad.game.entity.Location;
import org.asad.game.entity.player.Player;
import org.asad.game.event.Event;

/**
 * This {@link Place} where {@link Hero} is currently.
 */
public class Hero implements Place {

    private static final long serialVersionUID = 7935387391400949929L;
    private Location location;
    private Player player;
    private int health;
    private int money;
    private int skill;
    private int anxiety;
    private boolean readyForBossFight;


    public Hero(Location location, Player player) {
        this.location = location;
        this.player = player;
        this.health = player.getHealth();
        this.anxiety = player.getAnxiety();
        this.money = player.getMoney();
        this.skill = player.getSkill();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Event getEvent() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    @Override
    public int getAnxiety() {
        return anxiety;
    }

    public void setAnxiety(int anxiety) {
        this.anxiety = anxiety;
    }

    public boolean isReadyForBossFight() {
        return readyForBossFight;
    }

    public void setReadyForBossFight(boolean readyForBossFight) {
        this.readyForBossFight = readyForBossFight;
    }

    @Override
    public String getColor() {
        return player.getColor();
    }
}
