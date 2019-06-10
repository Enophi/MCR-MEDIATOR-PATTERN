package ch.heig.mediator;

import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.ui.FlyingObjectType;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
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
    public void askToLand(Entity e, int piste) {
        switch (piste) {
            case 1:
                if (e.getType() == FlyingObjectType.CHOPPER || e.getType() == FlyingObjectType.OVNI) {
                    if (FXGL.getGameState().getDouble("nbInCOne") < MAX_PROGRESS) {
                        // Increase the number of plane on the first airstrip
                        FXGL.getGameState().increment("nbInCOne", PROGRESS_STEP);
                        autorhiseLanding(e);
                        e.removeFromWorld();
                    } else {
                        setAlertRunvay(piste, "");
                    }
                } else {
                    setAlertRunvay(piste, "Wrong");
                }
                break;
            case 2:
                if (e.getType() == FlyingObjectType.PLANE || e.getType() == FlyingObjectType.OVNI) {
                    if (FXGL.getGameState().getDouble("nbInPOne") < MAX_PROGRESS) {
                        // Increase the number of plane on the second airstrip
                        FXGL.getGameState().increment("nbInPOne", PROGRESS_STEP);
                        autorhiseLanding(e);
                        e.removeFromWorld();
                    } else {
                        setAlertRunvay(piste, "");
                    }
                } else {
                    setAlertRunvay(piste, "Wrong");
                }
                break;
            case 3:
                if (e.getType() == FlyingObjectType.PLANE || e.getType() == FlyingObjectType.OVNI) {
                    if (FXGL.getGameState().getDouble("nbInPTwo") < MAX_PROGRESS) {
                        // Increase the number of plane on the second airstrip
                        FXGL.getGameState().increment("nbInPTwo", PROGRESS_STEP);
                        autorhiseLanding(e);
                        e.removeFromWorld();
                    } else {
                        setAlertRunvay(piste, "");
                    }
                } else {
                    setAlertRunvay(piste, "Wrong");
                }
                break;
            default:
                FXGL.getGameState().setValue("playerNotif", String.format("%d close!", piste));
                break;
        }
    }
}
