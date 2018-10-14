package org.asad.game.entity.place;

import org.asad.game.entity.Location;
import org.asad.game.entity.player.Player;
import org.asad.game.event.Event;
import org.asad.game.event.WorkEvent;

/**
 * This {@link Place} to get some Money and Skill.
 */
public class WorkPlace implements Place {

    private static final long serialVersionUID = 2697693489640635025L;
    private Location location;
    private Event event;

    public WorkPlace(Location location) {
        this.location = location;
        this.event = new WorkEvent(this);
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
        return "Work Place";
    }

    @Override
    public int getHealth() {
        return -10;
    }

    @Override
    public int getMoney() {
        return 1000;
    }

    @Override
    public int getSkill() {
        return 30;
    }

    @Override
    public int getAnxiety() {
        return 10;
    }

    @Override
    public String getColor() {
        return "\u001b[46;1m";
    }
}
