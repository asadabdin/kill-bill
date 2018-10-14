package org.asad.game.entity.place;

import org.asad.game.entity.Location;
import org.asad.game.entity.player.Player;
import org.asad.game.event.Event;
import org.asad.game.event.HideEvent;

/**
 * This {@link Place} to relax get some Money and Health.
 */
public class HideOut implements Place {

    private static final long serialVersionUID = -2995809174953502618L;
    private Location location;
    private Event event;

    public HideOut(Location location) {
        this.location = location;
        this.event = new HideEvent(this);
    }

    @Override
    public Event getEvent() {
        return event;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return "HideOut";
    }

    @Override
    public int getHealth() {
        return 50;
    }

    @Override
    public int getMoney() {
        return 1000;
    }

    @Override
    public int getSkill() {
        return 0;
    }

    @Override
    public int getAnxiety() {
        return -30;
    }

    @Override
    public String getColor() {
        return "\u001b[42m";
    }
}
