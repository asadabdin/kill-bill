package org.asad.game.exception;


/**
 * this should be thrown when {@link org.asad.game.entity.player.Player} / {@link org.asad.game.entity.place.Place}
 * attribute has reached to maximum.
 */
public class HighAttributeException extends Exception {

    public HighAttributeException(String message) {
        super(new StringBuilder("You are High in ")
                .append(message)
                .append("  :::  ")
                .append("Try Some other places and then visit again").toString());
    }
}
