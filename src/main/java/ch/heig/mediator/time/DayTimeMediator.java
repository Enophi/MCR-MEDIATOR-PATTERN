package ch.heig.mediator.time;

import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:47
 */
public class DayTimeMediator extends AbstractTimeMediator {

    private static final Image dayIcon = new Image("assets/icons/sun.png");

    /**
     * Instantiates a new Day time mediator.
     *
     * @param uiController the ui controller
     */
    public DayTimeMediator(ControlTowerUIController uiController) {
        super(uiController);
    }

    /**
     * Instantiates a new Day time mediator.
     *
     * @param other the other
     */
    public DayTimeMediator(AbstractTimeMediator other) {
        super(other);
    }

    @Override
    public Color getBackgroundColor() {
        return Color.rgb(38, 195, 219);
    }

    @Override
    protected Image getTimeIconImage() {
        return dayIcon;
    }
}
