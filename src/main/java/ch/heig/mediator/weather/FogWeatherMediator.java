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
public class FogWeatherMediator extends AbstractWeatherMediator {

    private static final Image fogIcon = new Image("assets/icons/fog.png");
    private static final Image fogBackground = new Image("assets/textures/fog.png");
    private static final ImagePattern fogImagePattern = new ImagePattern(fogBackground);

    /**
     * Instantiates a new Fog weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public FogWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Fog weather mediator.
     *
     * @param awm the awm
     */
    public FogWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
    }

    @Override
    protected Image getWeatherIconImage() {
        return fogIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return fogBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return fogImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 4;
    }

    @Override
    protected int getMaxDuration() {
        return 11;
    }
}

