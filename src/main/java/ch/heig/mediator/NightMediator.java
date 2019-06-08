package ch.heig.mediator;

import static ch.heig.utils.Rand.getRandomBool;

import ch.heig.models.runways.Runway;
import javafx.scene.paint.Color;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:48
 */
public class NightMediator extends AbstractMediator {

    public NightMediator(AbstractMediator other) {
        super(other);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.rgb(7, 26, 76);
    }

    @Override
    public void setOpenedRunways() {
        for (Runway runway : runways)
            runway.setOpen(getRandomBool());
    }

}
