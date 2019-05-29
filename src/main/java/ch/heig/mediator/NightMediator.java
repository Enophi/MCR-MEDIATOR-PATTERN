package ch.heig.mediator;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
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
        return Color.DARKBLUE;
    }

    @Override
    public void askToLand(Entity e, int piste) {
        if (piste == 3) {
            FXGL.getGameState().increment("nbInThree", 1);
            e.removeFromWorld();
        } else {
            FXGL.getNotificationService().setBackgroundColor(Color.RED);
            FXGL.getNotificationService().pushNotification(String.format("%d close!", piste));
        }
    }
}
