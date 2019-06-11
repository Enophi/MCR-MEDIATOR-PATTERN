package ch.heig.mediator.time;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:48
 */
public class NightTimeMediator extends AbstractTimeMediator {

    private static final Image nightIcon = new Image("assets/icons/moon.png");

    /**
     * Instantiates a new Night time mediator.
     *
     * @param other the other
     */
    public NightTimeMediator(AbstractTimeMediator other) {
        super(other);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.rgb(7, 26, 76);
    }

    @Override
    protected Image getTimeIconImage() {
        return nightIcon;
    }
}
