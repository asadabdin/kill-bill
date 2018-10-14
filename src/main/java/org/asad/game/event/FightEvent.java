package org.asad.game.event;

import org.asad.game.entity.place.Hero;
import org.asad.game.entity.place.Place;
import org.asad.game.helper.PlayerAttributeAccumulator;

/**
 * this Event only be associated to {@link org.asad.game.entity.place.WarField}
 */
public class FightEvent implements Event {

    private static final long serialVersionUID = 687572032384468563L;
    Place place;

    public FightEvent(Place place) {
        this.place = place;
    }

    @Override
    public void occurEvent(Hero hero) {
        if (hero.getHealth() > place.getPlayer().getHealth() && hero.getSkill() >= place.getPlayer().getSkill()) {
            hero.setHealth(PlayerAttributeAccumulator.getNewHealth(hero.getHealth() - (place.getPlayer().getHealth() / 2), 0));
            hero.setMoney(PlayerAttributeAccumulator.getNewMoney(hero.getMoney() + (place.getPlayer().getMoney() / 2), 0));
            hero.setAnxiety(PlayerAttributeAccumulator.getNewAnxiety((hero.getAnxiety() / 3) + place.getPlayer().getAnxiety(), 0));
            hero.setSkill(PlayerAttributeAccumulator.getNewSkill(place.getPlayer().getSkill(), hero.getSkill()));
        } else {
            hero.setHealth(0);
        }
    }

    @Override
    public boolean isAllowedForEvent(Hero hero) {
        return hero.isReadyForBossFight() || !place.getPlayer().isChapterBoss();
    }
}
