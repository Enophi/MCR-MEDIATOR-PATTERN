package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 19:50
 */

public class LightningWeatherMediator extends AbstractWeatherMediator {

    private final Image lightningIcon = new Image("assets/icons/flash.png");
    private final Image lightningBackground = new Image("assets/textures/lightning.gif");
    private final ImagePattern lightningImagePattern = new ImagePattern(lightningBackground);

    public LightningWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    @Override
    protected Image getWeatherIconImage() {
        return lightningIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return lightningBackground;
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

