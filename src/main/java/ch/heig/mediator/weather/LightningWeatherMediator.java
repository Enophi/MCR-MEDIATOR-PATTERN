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

    private static final Image lightningIcon = new Image("assets/icons/flash.png");
    private static final Image lightningBackground = new Image("assets/textures/lightning.gif");
    private static final ImagePattern lightningImagePattern = new ImagePattern(lightningBackground);

    /**
     * Instantiates a new Lightning weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public LightningWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Lightning weather mediator.
     *
     * @param awm the awm
     */
    public LightningWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
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
    protected ImagePattern getWeatherImagePattern() {
        return lightningImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 6;
    }

    @Override
    protected int getMaxDuration() {
        return 13;
    }
}

