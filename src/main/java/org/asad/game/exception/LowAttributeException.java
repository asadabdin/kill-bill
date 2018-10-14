package org.asad.game.exception;

/**
 * this should be thrown when {@link org.asad.game.entity.player.Player} / {@link org.asad.game.entity.place.Place}
 * attribute has reached to Minimum.
 */

public class LowAttributeException extends Exception {

    public LowAttributeException(String message) {
        super(new StringBuilder("You are Low in ")
                .append(message)
                .append("  :::  ")
                .append("Try Some other places and then visit again").toString());
    }
}
