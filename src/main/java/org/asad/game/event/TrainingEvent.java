package org.asad.game.event;

import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.Place;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.helper.PlayerAttributeAccumulator;

/**
 * this Event only be associated to {@link org.asad.game.entity.place.TrainingArena}
 */
public class TrainingEvent implements Event {

    private static final long serialVersionUID = -8887683655102150440L;
    Place place;

    public TrainingEvent(Place place) {
        this.place = place;
    }

    @Override
    public void occurEvent(Hero hero) {
        hero.setHealth(PlayerAttributeAccumulator.getNewHealth(place.getHealth(), hero.getHealth()));
        hero.setMoney(PlayerAttributeAccumulator.getNewMoney(hero.getMoney() - place.getMoney(), 0));
        hero.setAnxiety(PlayerAttributeAccumulator.getNewAnxiety(place.getAnxiety(), hero.getAnxiety()));
        hero.setSkill(PlayerAttributeAccumulator.getNewSkill(place.getSkill(), hero.getSkill()));
    }

    @Override
    public boolean isAllowedForEvent(Hero hero) throws LowAttributeException, HighAttributeException {
        if (hero.getMoney() < place.getMoney()) {
            throw new LowAttributeException("Money, go to work place");
        } else if (hero.getAnxiety() > 70) {
            throw new HighAttributeException("Anxiety, go to hideout");
        } else if (hero.getHealth() < 30) {
            throw new LowAttributeException("Health, go to hideout");
        }
        return true;
    }
}
