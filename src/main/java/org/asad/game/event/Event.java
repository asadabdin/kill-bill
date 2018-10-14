package org.asad.game.event;

import org.asad.game.entity.place.Hero;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;

import java.io.Serializable;

/**
 * Every {@link org.asad.game.entity.place.Place} Should be associated to an Event.
 */
public interface Event extends Serializable {

    /**
     * it modifies the Hero attributes
     * @param hero
     */
    void occurEvent(Hero hero);

    /**
     * it checks whether {@link Hero} is allowed for this {@link Event}
     *
     * @param hero
     * @return boolean
     * @throws HighAttributeException
     * @throws LowAttributeException
     */
    boolean isAllowedForEvent(Hero hero) throws LowAttributeException, HighAttributeException;
}
