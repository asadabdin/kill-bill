package org.asad.game.event;

import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.Place;
import org.asad.game.exception.HighAttributeException;
import org.asad.game.exception.LowAttributeException;
import org.asad.game.helper.PlayerAttributeAccumulator;

/**
 * this Event only be associated to {@link org.asad.game.entity.place.WorkPlace}
 */
public class WorkEvent implements Event {

    private static final long serialVersionUID = 7604811249570983242L;
    Place place;

    public WorkEvent(Place place) {
        this.place = place;
    }

    @Override
    public void occurEvent(Hero hero) {
        hero.setHealth(PlayerAttributeAccumulator.getNewHealth(place.getHealth(), hero.getHealth()));
        hero.setMoney(PlayerAttributeAccumulator.getNewMoney(place.getMoney(), hero.getMoney()));
        hero.setAnxiety(PlayerAttributeAccumulator.getNewAnxiety(place.getAnxiety(), hero.getAnxiety()));
        hero.setSkill(PlayerAttributeAccumulator.getNewSkill(place.getSkill(), hero.getSkill()));
    }

    @Override
    public boolean isAllowedForEvent(Hero hero) throws HighAttributeException, LowAttributeException {
        if (PlayerAttributeAccumulator.MAX_MONEY == hero.getMoney()) {
            throw new HighAttributeException("Money, you don't need to work anymore");
        } else if (hero.getHealth() < 11) {
            throw new LowAttributeException("Health, Go to hideout");
        } else if (hero.getAnxiety() > 90) {
            throw new HighAttributeException("Anxiety, Go to hideout");
        }
        return true;
    }
}
