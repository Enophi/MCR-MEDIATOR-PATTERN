package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.controller.WeatherController;
import ch.heig.models.flyingobjects.shared.FlyingObjectAction;
import ch.heig.models.flyingobjects.shared.FlyingObjectMovement;
import ch.heig.models.runways.Runway;
import ch.heig.ui.ControlTowerUIController;
import com.almasb.fxgl.entity.Entity;
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

    /**
     * The Game.
     */
    protected ControlTowerGame game;
    private ControlTowerUIController uiController;
    /**
     * The Duration.
     */
    protected double duration;
    /**
     * The Weather controller.
     */
    protected WeatherController weatherController;
    /**
     * The Incoming weather circle.
     */
    protected Circle incomingWeatherCircle;
    /**
     * The Incoming weather circle background.
     */
    protected Circle incomingWeatherCircleBackground;
    private static final double SHOWING_TIME = 15;
    private static final double WEATHER_ICON_RADIUS = 15;

    /**
     * Instantiates a new Abstract weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public AbstractWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        this.game = game;
        this.uiController = uiController;
        this.weatherController = new WeatherController(this, SHOWING_TIME);
        setRandomDuration();
    }

    /**
     * Instantiates a new Abstract weather mediator.
     *
     * @param awm the awm
     */
    public AbstractWeatherMediator(AbstractWeatherMediator awm) {
        game = awm.getGame();
        uiController = awm.getUiController();
        weatherController = awm.getWeatherController();
        setRandomDuration();
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    protected ControlTowerGame getGame() {
        return game;
    }

    /**
     * Gets ui controller.
     *
     * @return the ui controller
     */
    protected ControlTowerUIController getUiController() {
        return uiController;
    }

    /**
     * Gets weather controller.
     *
     * @return the weather controller
     */
    protected WeatherController getWeatherController() {
        return weatherController;
    }

    /**
     * Sets weather icon.
     */
    public void setWeatherIcon() {
        uiController.getWeatherIconForeground().setFill(new ImagePattern(getWeatherIconImage()));
    }

    /**
     * Gets weather icon image.
     *
     * @return the weather icon image
     */
    protected abstract Image getWeatherIconImage();

    /**
     * Sets weather background.
     */
    public void setWeatherBackground() {
        game.setWeatherBackground(getWeatherImagePattern());
    }

    /**
     * Gets weather background image.
     *
     * @return the weather background image
     */
    protected abstract Image getWeatherBackgroundImage();

    /**
     * Gets weather image pattern.
     *
     * @return the weather image pattern
     */
    protected abstract ImagePattern getWeatherImagePattern();

    /**
     * Gets min duration.
     *
     * @return the min duration
     */
    protected abstract int getMinDuration();

    /**
     * Gets max duration.
     *
     * @return the max duration
     */
    protected abstract int getMaxDuration();

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets random duration.
     */
    protected void setRandomDuration() {
        Random random = new Random();
        duration = random.nextInt(getMaxDuration() - getMinDuration()) + getMinDuration();
    }

    /**
     * Check weather change.
     */
    public void checkWeatherChange() {
        weatherController.checkWeather();
    }

    /**
     * Update mediator.
     */
    public void updateMediator() {
        game.setWeatherMediator(this);
        setWeatherIcon();
        setWeatherBackground();
        addModifiers();
    }

    /**
     * Init incoming weather icon.
     *
     * @param position the position
     */
    public void initIncomingWeatherIcon(int position) {
        incomingWeatherCircleBackground = initCircle(position);
        incomingWeatherCircle = initCircle(position);
        incomingWeatherCircle.setFill(new ImagePattern(getWeatherIconImage()));

        game.initIncomingWeatherIcon(incomingWeatherCircleBackground);
        game.initIncomingWeatherIcon(incomingWeatherCircle);
    }

    private Circle initCircle(int position) {
        Circle weatherIcon = uiController.getWeatherIconForeground();
        double centerX = weatherIcon.getCenterX() + weatherIcon.getRadius() / 2 + 5;
        double centerY = weatherIcon.getCenterY() + 2;

        Circle circle = new Circle(centerX, centerY, WEATHER_ICON_RADIUS, Color.AQUAMARINE);
        circle.setStrokeWidth(2);
        circle.setStroke(Color.BLACK);
        circle.setTranslateX(WEATHER_ICON_RADIUS * 2 * position + 5 * position);

        return circle;
    }

    /**
     * Update incoming weather icon.
     *
     * @param position the position
     */
    public void updateIncomingWeatherIcon(int position) {
        incomingWeatherCircleBackground.setTranslateX(WEATHER_ICON_RADIUS * 2 * position + 5 * position);
        incomingWeatherCircle.setTranslateX(WEATHER_ICON_RADIUS * 2 * position + 5 * position);
    }

    /**
     * Remove incoming weather icon.
     */
    public void removeIncomingWeatherIcon() {
        game.removeIncomingWeatherIcon(incomingWeatherCircleBackground);
        game.removeIncomingWeatherIcon(incomingWeatherCircle);
    }

    public void addModifiers() {
        for (Entity e : game.getMediator().getFlyingObjects()) {
            resetFlyingModifiers(e);
            addFlyingModifiers(e);
        }

        for (Runway r : game.getMediator().getRunways()) {
            resetRunwayModifiers(r);
            addRunwayModifiers(r);
        }
    }

    public abstract void addFlyingModifiers(Entity e);

    public void resetFlyingModifiers(Entity e) {
        e.getComponent(FlyingObjectMovement.class).setSpeedMultiplier(0);
        e.getComponent(FlyingObjectMovement.class).setDeviationX(0);
        e.getComponent(FlyingObjectAction.class).setRandomCrash(0);
    }

    public abstract void addRunwayModifiers(Runway r);

    public void resetRunwayModifiers(Runway r) {
        r.setMaxPlaces(1.0);
    }

}
