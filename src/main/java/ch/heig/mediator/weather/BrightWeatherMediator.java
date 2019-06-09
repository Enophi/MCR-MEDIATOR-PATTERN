package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 19:15
 */

public class BrightWeatherMediator extends AbstractWeatherMediator {

    private final Image brightIcon = new Image("assets/icons/bright.png");
    private final Image brightBackground = new Image("assets/textures/bright.png");
    private final ImagePattern normalImagePattern = new ImagePattern(brightBackground);

    public BrightWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    @Override
    protected Image getWeatherIconImage() {
        return brightIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return brightBackground;
    }

    @Override
    protected int getMinDuration() {
        return 5;
    }

    @Override
    protected int getMaxDuration() {
        return 10;
    }
}

