package ch.heig.mediator;

import ch.heig.models.runways.Runway;
import javafx.scene.paint.Color;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:47
 */

public class DayMediator extends AbstractMediator {

    public DayMediator() {
    }

    public DayMediator(AbstractMediator other) {
        super(other);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.rgb(38, 195, 219);
    }

    @Override
    public void setOpenedRunways() {
        for (Runway runway : runways)
            runway.setOpen(true);
    }

}
