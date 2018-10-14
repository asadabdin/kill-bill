package org.asad.game.exception;

/**
 * this exception should be thrown when No space left to move
 */
public class NoSpaceToMoveException extends Exception {

    public NoSpaceToMoveException(String message) {
        super(message);
    }
}
