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
    private final int MAX_ON_FOUR = 3;

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
                if (FXGL.getGameState().getInt("nbInOne") < MAX_ON_ONE) {
                    // Increase the number of plane on the first airstrip
                    FXGL.getGameState().increment("nbInOne", 1);
                    e.removeFromWorld();
                } else {
                    FXGL.getGameState().setValue("playerNotif", String.format("Airstrip %d full!!", piste));
                }
                break;
            case 2:
                if (FXGL.getGameState().getInt("nbInTwo") < MAX_ON_TWO) {
                    // Increase the number of plane on the second airstrip
                    FXGL.getGameState().increment("nbInTwo", 1);
                    e.removeFromWorld();
                } else {
                    FXGL.getGameState().setValue("playerNotif", String.format("Airstrip %d full!!", piste));
                }
                break;
            case 4:
                if (FXGL.getGameState().getInt("nbInFour") < MAX_ON_FOUR) {
                    // Increase the number of plane on the second airstrip
                    FXGL.getGameState().increment("nbInFour", 1);
                    e.removeFromWorld();
                } else {
                    FXGL.getGameState().setValue("playerNotif", String.format("Airstrip %d full!!", piste));
                }
                break;
            default:
                FXGL.getGameState().setValue("playerNotif", String.format("%d close!", piste));
                break;
        }
    }
}
