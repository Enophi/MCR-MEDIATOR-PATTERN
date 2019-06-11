package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.controller.WeatherController;
import ch.heig.ui.ControlTowerUIController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.Random;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 16:11
 */
public abstract class AbstractWeatherMediator {

    protected ControlTowerGame game;
    private ControlTowerUIController uiController;
    protected double duration;
    protected WeatherController weatherController;
    protected Circle incomingWeatherCircle;
    protected Circle incomingWeatherCircleBackground;
    private static final double SHOWING_TIME = 15;
    private static final double WEATHER_ICON_RADIUS = 15;

    public AbstractWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        this.game = game;
        this.uiController = uiController;
        this.weatherController = new WeatherController(this, SHOWING_TIME);
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

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
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

    public void initIncomingWeatherIcon(int position) {
        Circle weatherIcon = uiController.getWeatherIconForeground();
        double centerX = weatherIcon.getCenterX() + weatherIcon.getRadius() / 2 + 5;
        double centerY = weatherIcon.getCenterY() + 2;

        incomingWeatherCircleBackground = new Circle(centerX, centerY, WEATHER_ICON_RADIUS, Color.AQUAMARINE);
        incomingWeatherCircleBackground.setStrokeWidth(2);
        incomingWeatherCircleBackground.setStroke(Color.BLACK);
        incomingWeatherCircleBackground.setTranslateX(WEATHER_ICON_RADIUS * 2 * position + 5 * position);

        incomingWeatherCircle = new Circle(centerX, centerY, WEATHER_ICON_RADIUS, Color.AQUAMARINE);
        incomingWeatherCircle.setStrokeWidth(2);
        incomingWeatherCircle.setStroke(Color.BLACK);
        incomingWeatherCircle.setFill(new ImagePattern(getWeatherIconImage()));
        incomingWeatherCircle.setTranslateX(WEATHER_ICON_RADIUS * 2 * position + 5 * position);

        game.initIncomingWeatherIcon(incomingWeatherCircleBackground);
        game.initIncomingWeatherIcon(incomingWeatherCircle);
    }

    public void updateIncomingWeatherIcon(int position) {
        incomingWeatherCircleBackground.setTranslateX(WEATHER_ICON_RADIUS * 2 * position + 5 * position);
        incomingWeatherCircle.setTranslateX(WEATHER_ICON_RADIUS * 2 * position + 5 * position);
    }

    public void removeIncomingWeatherIcon() {
        game.removeIncomingWeatherIcon(incomingWeatherCircleBackground);
        game.removeIncomingWeatherIcon(incomingWeatherCircle);
    }
}
