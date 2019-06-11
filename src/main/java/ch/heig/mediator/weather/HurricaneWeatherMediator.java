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
 * 09.06.2019
 * 19:42
 */
public class HurricaneWeatherMediator extends AbstractWeatherMediator {

    private static final Image hurricaneIcon = new Image("assets/icons/hurricane.png");
    private static final Image hurricaneBackground = new Image("assets/textures/hurricane.gif");
    private static final ImagePattern hurricaneImagePattern = new ImagePattern(hurricaneBackground);

    /**
     * Instantiates a new Hurricane weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public HurricaneWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Hurricane weather mediator.
     *
     * @param awm the awm
     */
    public HurricaneWeatherMediator(AbstractWeatherMediator awm) {
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
        e.getComponent(FlyingObjectMovement.class).setDeviationX(10);
        e.getComponent(FlyingObjectAction.class).setRandomCrash(25);
    }

    @Override
    public void addRunwayModifiers(Runway r) {
        r.setMaxPlaces(0.5);
    }
}

