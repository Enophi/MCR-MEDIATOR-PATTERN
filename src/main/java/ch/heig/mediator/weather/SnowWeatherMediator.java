package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 19:38
 */

public class SnowWeatherMediator extends AbstractWeatherMediator {

    private final Image snowIcon = new Image("assets/icons/snow.png");
    private final Image snowBackground = new Image("assets/textures/snow.gif");
    private final ImagePattern snowImagePattern = new ImagePattern(snowBackground);

    public SnowWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    @Override
    protected Image getWeatherIconImage() {
        return snowIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return snowBackground;
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

