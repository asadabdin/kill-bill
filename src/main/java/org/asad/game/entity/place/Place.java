package org.asad.game.entity.place;

import org.asad.game.entity.AtomEntity;
import org.asad.game.entity.Location;
import org.asad.game.entity.player.Player;
import org.asad.game.event.Event;

import java.io.Serializable;

public interface Place extends AtomEntity, Serializable {

    Location getLocation();
    Player getPlayer();
    Event getEvent();
}
