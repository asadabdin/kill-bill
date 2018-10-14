package org.asad.game.entity;

import java.io.Serializable;

/**
 * This is the base Entity every Cell in the Map is implementation of this
 *
 * @see org.asad.game.entity.place.Hero
 * @see org.asad.game.entity.place.HideOut
 * @see org.asad.game.entity.place.TrainingArena
 * @see org.asad.game.entity.place.WarField
 * @see org.asad.game.entity.place.WorkPlace
 */
public interface AtomEntity extends Serializable {

    String resetColor = "\u001b[0m";
    String underline = "\u001b[4m";

    String getName();
    int getHealth();
    int getMoney();
    int getSkill();
    int getAnxiety();
    String getColor();
}
