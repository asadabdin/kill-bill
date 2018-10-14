package org.asad.game.entity.place;

import org.asad.game.entity.Location;
import org.asad.game.entity.player.Player;
import org.asad.game.event.Event;
import org.asad.game.event.TrainingEvent;

/**
 * This {@link Place} to get some Skill.
 */
public class TrainingArena implements Place {

    private static final long serialVersionUID = 1198057696810983307L;
    private Location location;
    private Event event;

    public TrainingArena(Location location) {
        this.location = location;
        this.event = new TrainingEvent(this);
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
        return "Training Arena";
    }

    @Override
    public int getHealth() {
        return -10;
    }

    @Override
    public int getMoney() {
        return -500;
    }

    @Override
    public int getSkill() {
        return 50;
    }

    @Override
    public int getAnxiety() {
        return 30;
    }

    @Override
    public String getColor() {
        return "\u001b[45m";
    }
}
