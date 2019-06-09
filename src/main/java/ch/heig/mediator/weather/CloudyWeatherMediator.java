package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 16:35
 */

public class CloudyWeatherMediator extends AbstractWeatherMediator {

    private final Image normalIcon = new Image("assets/icons/cloud.png");
    private final Image normalBackground = new Image("assets/textures/cloudy.png");


    public CloudyWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    @Override
    protected Image getWeatherIconImage() {
        return normalIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return normalBackground;
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

