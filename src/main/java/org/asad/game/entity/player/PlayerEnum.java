package org.asad.game.entity.player;

/**
 * Possible Opponents with Name.
 */
public enum  PlayerEnum {
    O_Ren("O-Ren Ishii"),
    Budd("Budd"),
    Sofie("Sofie Fatale"),
    Gogo("Gogo Yubari"),
    Bill("Bill");

    String playerName;


    PlayerEnum(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
