package org.asad.game.entity.place;

import org.asad.game.entity.Location;
import org.asad.game.entity.player.Player;
import org.asad.game.event.Event;
import org.asad.game.event.FightEvent;

/**
 * This {@link Place} to fight with opponent.
 */
public class WarField implements Place {

    private static final long serialVersionUID = -8508970869613191418L;
    private Location location;

    private Player player;

    private Event event;

    public WarField(Location location, Player player) {
        this.location = location;
        this.player = player;
        this.event = new FightEvent(this);
    }

    @Override
    public Event getEvent() {
        return event;
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
        return player.getHealth();
    }

    @Override
    public int getMoney() {
        return player.getMoney();
    }

    @Override
    public int getSkill() {
        return player.getSkill();
    }

    @Override
    public int getAnxiety() {
        return player.getAnxiety();
    }

    @Override
    public String getColor() {
        return player.getColor();
    }
}