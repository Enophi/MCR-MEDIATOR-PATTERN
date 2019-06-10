package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 16:11
 */
public abstract class AbstractWeatherMediator {

    protected ControlTowerGame game;
    private ControlTowerUIController uiController;
    protected int duration;

    public AbstractWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        this.game = game;
        this.uiController = uiController;
        Random random = new Random();
        this.duration = random.nextInt(getMaxDuration() - getMinDuration()) + getMinDuration();
    }

    public void setWeatherIcon() {
        uiController.getWeatherIconForeground().setFill(new ImagePattern(getWeatherIconImage()));
    }

    protected abstract Image getWeatherIconImage();

    public void setWeatherBackground() {
        game.setWeatherBackground(getWeatherImagePattern());
    }

    protected abstract Image getWeatherBackgroundImage();

    protected abstract ImagePattern getWeatherImagePattern();

    protected abstract int getMinDuration();
    protected abstract int getMaxDuration();

    public int getDuration() {
        return duration;
    }
}
