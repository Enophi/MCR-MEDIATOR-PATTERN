package ch.heig.mediator;

import ch.heig.models.runways.Runway;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;

import java.util.Random;

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
            runway.setOpen(getRandomBoolean());
    }

}
