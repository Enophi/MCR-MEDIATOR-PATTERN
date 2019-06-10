package ch.heig.mediator;

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

    /*@Override
    public void askToLand(Entity e, int piste) {
        switch (piste) {
            case 4:
                if (e.getType() == FlyingObjectType.PLANE || e.getType() == FlyingObjectType.OVNI) {
                    if (FXGL.getGameState().getDouble("nbInPThree") < MAX_PROGRESS) {
                        // Increase the number of plane on the second airstrip
                        FXGL.getGameState().increment("nbInPThree", PROGRESS_STEP);
                        autorhiseLanding(e);
                        e.removeFromWorld();
                    } else {
                        setAlertRunvay(piste, "");
                    }
                } else {
                    setAlertRunvay(piste, "Wrong");
                }
                break;
            case 5:
                if (e.getType() == FlyingObjectType.CHOPPER || e.getType() == FlyingObjectType.OVNI) {
                    if (FXGL.getGameState().getDouble("nbInCTwo") < MAX_PROGRESS) {
                        // Increase the number of plane on the second airstrip
                        FXGL.getGameState().increment("nbInCTwo", PROGRESS_STEP);
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
    }*/
}
