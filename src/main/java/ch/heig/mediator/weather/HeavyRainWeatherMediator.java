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
 * 19:45
 */
public class HeavyRainWeatherMediator extends AbstractWeatherMediator {

    private static final Image rainIcon = new Image("assets/icons/rain.png");
    private static final Image rainBackground = new Image("assets/textures/heavyrain.gif");
    private static final ImagePattern rainImagePattern = new ImagePattern(rainBackground);

    /**
     * Instantiates a new Heavy rain weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public HeavyRainWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Heavy rain weather mediator.
     *
     * @param awm the awm
     */
    public HeavyRainWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
    }

    @Override
    protected Image getWeatherIconImage() {
        return rainIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return rainBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return rainImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 3;
    }

    @Override
    protected int getMaxDuration() {
        return 8;
    }

    @Override
    public void addFlyingModifiers(Entity e) {
        e.getComponent(FlyingObjectMovement.class).setDeviationX(5);
        e.getComponent(FlyingObjectAction.class).setRandomCrash(2);
    }

    @Override
    public void addRunwayModifiers(Runway r) {
        r.setMaxPlaces(0.8);
    }
}

