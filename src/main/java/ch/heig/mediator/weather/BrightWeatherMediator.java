package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.models.flyingobjects.shared.FlyingObjectMovement;
import ch.heig.models.runways.Runway;
import ch.heig.ui.ControlTowerUIController;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * created by Alain Gobet
 * 09.06.2019
 * 19:15
 */
public class BrightWeatherMediator extends AbstractWeatherMediator {

    private static final Image brightIcon = new Image("assets/icons/bright.png");
    private static final Image brightBackground = new Image("assets/textures/bright.png");
    private static final ImagePattern brightImagePattern = new ImagePattern(brightBackground);

    /**
     * Instantiates a new Bright weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public BrightWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Bright weather mediator.
     *
     * @param awm the awm
     */
    public BrightWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
    }

    @Override
    protected Image getWeatherIconImage() {
        return brightIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return brightBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return brightImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 6;
    }

    @Override
    protected int getMaxDuration() {
        return 12;
    }

    @Override
    public void addFlyingModifiers(Entity e) {
        e.getComponent(FlyingObjectMovement.class).setSpeedMultiplier(1.5);
    }

    @Override
    public void addRunwayModifiers(Runway r) {
    }
}

