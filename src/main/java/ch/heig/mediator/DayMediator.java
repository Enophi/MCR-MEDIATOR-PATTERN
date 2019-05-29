package ch.heig.mediator;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:47
 */

public class DayMediator extends AbstractMediator {

    private final int MAX_ON_ONE = 5;
    private final int MAX_ON_TWO = 15;

    public DayMediator() {
    }

    public DayMediator(AbstractMediator other) {
        super(other);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.YELLOW;
    }

    @Override
    public void askToLand(Entity e, int piste) {
        if (piste == 1) {
            if (FXGL.getGameState().getInt("nbInOne") < MAX_ON_ONE) {
                // Increase the number of plane on the first airstrip
                FXGL.getGameState().increment("nbInOne", 1);
                e.removeFromWorld();
            } else {
                FXGL.getNotificationService().setBackgroundColor(Color.ORANGE);
                FXGL.getNotificationService().pushNotification("Airstrip full!!");
            }
        } else if (piste == 2) {
            if (FXGL.getGameState().getInt("nbInTwo") < MAX_ON_TWO) {
                // Increase the number of plane on the second airstrip
                FXGL.getGameState().increment("nbInTwo", 1);
                e.removeFromWorld();
            } else {
                FXGL.getNotificationService().setBackgroundColor(Color.ORANGE);
                FXGL.getNotificationService().pushNotification("Airstrip full!!");
            }
        } else {
            FXGL.getNotificationService().setBackgroundColor(Color.RED);
            FXGL.getNotificationService().pushNotification(String.format("%d close!", piste));
        }
    }
}
