package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 16:35
 */

public class CloudyWeatherMediator extends AbstractWeatherMediator {

    private static final Image cloudyIcon = new Image("assets/icons/cloud.png");
    private static final Image cloudyBackground = new Image("assets/textures/cloudy.png");
    private static final ImagePattern cloudyImagePattern = new ImagePattern(cloudyBackground);

    public CloudyWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    @Override
    protected Image getWeatherIconImage() {
        return cloudyIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return cloudyBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return cloudyImagePattern;
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

