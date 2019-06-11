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

    private static final Image snowIcon = new Image("assets/icons/snow.png");
    private static final Image snowBackground = new Image("assets/textures/snow.gif");
    private static final ImagePattern snowImagePattern = new ImagePattern(snowBackground);

    /**
     * Instantiates a new Snow weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public SnowWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Snow weather mediator.
     *
     * @param awm the awm
     */
    public SnowWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
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
    protected ImagePattern getWeatherImagePattern() {
        return snowImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 3;
    }

    @Override
    protected int getMaxDuration() {
        return 9;
    }
}

