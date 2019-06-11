package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.models.flyingobjects.shared.FlyingObjectAction;
import ch.heig.models.flyingobjects.shared.FlyingObjectMovement;
import ch.heig.models.runways.Runway;
import ch.heig.ui.ControlTowerUIController;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 11.06.2019
 * 20:35
 */
public class WindyWeatherMediator extends AbstractWeatherMediator {

    private static final Image hurricaneIcon = new Image("assets/icons/wind.png");
    private static final Image hurricaneBackground = new Image("assets/textures/windy.gif");
    private static final ImagePattern hurricaneImagePattern = new ImagePattern(hurricaneBackground);

    /**
     * Instantiates a new Windy weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public WindyWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Windy weather mediator.
     *
     * @param awm the awm
     */
    public WindyWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
    }

    @Override
    protected Image getWeatherIconImage() {
        return hurricaneIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return hurricaneBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return hurricaneImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 3;
    }

    @Override
    protected int getMaxDuration() {
        return 7;
    }

    @Override
    public void addFlyingModifiers(Entity e) {
        e.getComponent(FlyingObjectMovement.class).setSpeedMultiplier(0.75);
        e.getComponent(FlyingObjectMovement.class).setDeviationX(3);
        e.getComponent(FlyingObjectAction.class).setRandomCrash(3);
    }

    @Override
    public void addRunwayModifiers(Runway r) {
    }
}

