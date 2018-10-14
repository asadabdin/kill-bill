package org.asad.game.event;

import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.Place;
import org.asad.game.helper.PlayerAttributeAccumulator;

/**
 * this Event only be associated to {@link org.asad.game.entity.place.HideOut}
 */
public class HideEvent implements Event {

    private static final long serialVersionUID = -8529828927815300092L;
    Place place;

    public HideEvent(Place place) {
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
    public boolean isAllowedForEvent(Hero hero) {
        return true;
    }
}
