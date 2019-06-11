package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 19:55
 */

public class BigFogWeatherMediator extends AbstractWeatherMediator {

    private static final Image bigFogIcon = new Image("assets/icons/fog.png");
    private static final Image bigFogBackground = new Image("assets/textures/fog.gif");
    private static final ImagePattern bigFogImagePattern = new ImagePattern(bigFogBackground);

    public BigFogWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    public BigFogWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
    }

    @Override
    protected Image getWeatherIconImage() {
        return bigFogIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return bigFogBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return bigFogImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 3;
    }

    @Override
    protected int getMaxDuration() {
        return 7;
    }

}

