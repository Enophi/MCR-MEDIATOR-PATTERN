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

public class NormalWeatherMediator extends AbstractWeatherMediator {

    private static final Image normalIcon = new Image("assets/icons/normal.png");
    private static final Image normalBackground = new Image("assets/textures/blank.png");
    private static final ImagePattern normalImagePattern = new ImagePattern(normalBackground);

    public NormalWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    public NormalWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
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
    protected ImagePattern getWeatherImagePattern() {
        return normalImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 5;
    }

    @Override
    protected int getMaxDuration() {
        return 13;
    }
}

