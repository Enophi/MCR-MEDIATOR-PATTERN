package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 19:45
 */

public class HeavyRainWeatherMediator extends AbstractWeatherMediator {

    private final Image rainIcon = new Image("assets/icons/rain.png");
    private final Image rainBackground = new Image("assets/textures/heavyrain.gif");
    private final ImagePattern rainImagePattern = new ImagePattern(rainBackground);

    public HeavyRainWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    @Override
    protected Image getWeatherIconImage() {
        return rainIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return rainBackground;
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

