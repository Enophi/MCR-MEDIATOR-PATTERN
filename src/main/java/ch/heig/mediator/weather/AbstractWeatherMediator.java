package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.controller.WeatherController;
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
    protected WeatherController weatherController;

    public AbstractWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        this.game = game;
        this.uiController = uiController;
        this.weatherController = new WeatherController(this);
        setRandomDuration();
    }

    public AbstractWeatherMediator(AbstractWeatherMediator awm) {
        game = awm.getGame();
        uiController = awm.getUiController();
        weatherController = awm.getWeatherController();
        setRandomDuration();
    }

    protected ControlTowerGame getGame() {
        return game;
    }

    protected ControlTowerUIController getUiController() {
        return uiController;
    }

    protected WeatherController getWeatherController() {
        return weatherController;
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

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    protected void setRandomDuration() {
        Random random = new Random();
        duration = random.nextInt(getMaxDuration() - getMinDuration()) + getMinDuration();
    }

    public void checkWeatherChange() {
        weatherController.checkWeather();
    }

    public void updateMediator() {
        game.setWeatherMediator(this);
        setWeatherIcon();
        setWeatherBackground();
    }
}
