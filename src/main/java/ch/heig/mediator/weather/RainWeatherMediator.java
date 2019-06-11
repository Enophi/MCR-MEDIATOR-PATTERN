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
 * 19:35
 */
public class RainWeatherMediator extends AbstractWeatherMediator {

    private static final Image rainIcon = new Image("assets/icons/rain.png");
    private static final Image rainBackground = new Image("assets/textures/rain.gif");
    private static final ImagePattern rainImagePattern = new ImagePattern(rainBackground);

    /**
     * Instantiates a new Rain weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public RainWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Rain weather mediator.
     *
     * @param awm the awm
     */
    public RainWeatherMediator(AbstractWeatherMediator awm) {
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
        return 5;
    }

    @Override
    protected int getMaxDuration() {
        return 13;
    }

    @Override
    public void addFlyingModifiers(Entity e) {
        e.getComponent(FlyingObjectMovement.class).setDeviationX(1);
        e.getComponent(FlyingObjectAction.class).setRandomCrash(1);
    }

    @Override
    public void addRunwayModifiers(Runway r) { }
}

