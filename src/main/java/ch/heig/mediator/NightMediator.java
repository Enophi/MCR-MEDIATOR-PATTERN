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

}
