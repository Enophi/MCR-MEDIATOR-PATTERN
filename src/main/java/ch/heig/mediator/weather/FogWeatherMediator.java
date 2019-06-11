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

    @Override
    public void addFlyingModifiers(Entity e) {
        e.getComponent(FlyingObjectMovement.class).setDeviationX(1);
        e.getComponent(FlyingObjectAction.class).setRandomCrash(1);
    }

    @Override
    public void addRunwayModifiers(Runway r) {
        r.setMaxPlaces(0.9);
    }
}

