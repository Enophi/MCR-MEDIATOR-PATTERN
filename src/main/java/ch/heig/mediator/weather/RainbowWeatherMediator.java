package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 20:00
 */

public class RainbowWeatherMediator extends AbstractWeatherMediator {

    private final Image rainbowIcon = new Image("assets/icons/rainbow.png");
    private final Image rainbowBackground = new Image("assets/textures/rainbow.png");
    private final ImagePattern rainbowImagePattern = new ImagePattern(rainbowBackground);

    public RainbowWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    @Override
    protected Image getWeatherIconImage() {
        return rainbowIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return rainbowBackground;
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

