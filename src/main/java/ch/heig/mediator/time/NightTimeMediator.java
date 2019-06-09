package ch.heig.mediator.time;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:48
 */
public class NightTimeMediator extends AbstractTimeMediator {

    private final Image nightIcon = new Image("assets/icons/moon.png");

    public NightTimeMediator(AbstractTimeMediator other) {
        super(other);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.rgb(7, 26, 76);
    }

    @Override
    public void askToLand(Entity e, int piste) {
        switch (piste) {
            case 3:
                FXGL.getGameState().increment("nbInThree", 1);
                e.removeFromWorld();
                break;
            case 5:
                FXGL.getGameState().increment("nbInFive", 1);
                e.removeFromWorld();
                break;
            default:
                FXGL.getGameState().setValue("playerNotif", String.format("%d close!", piste));
                break;
        }
    }

    @Override
    protected Image getTimeIconImage() {
        return nightIcon;
    }
}
