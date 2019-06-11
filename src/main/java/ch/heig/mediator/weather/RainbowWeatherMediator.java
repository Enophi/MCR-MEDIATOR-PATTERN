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
 * 20:00
 */
public class RainbowWeatherMediator extends AbstractWeatherMediator {

    private static final Image rainbowIcon = new Image("assets/icons/rainbow.png");
    private static final Image rainbowBackground = new Image("assets/textures/rainbow.png");
    private static final ImagePattern rainbowImagePattern = new ImagePattern(rainbowBackground);

    /**
     * Instantiates a new Rainbow weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public RainbowWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Rainbow weather mediator.
     *
     * @param awm the awm
     */
    public RainbowWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
    }

    @Override
    protected Image getWeatherIconImage() {
        return rainbowIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return rainbowBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return rainbowImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 5;
    }

    @Override
    protected int getMaxDuration() {
        return 10;
    }

    @Override
    public void addFlyingModifiers(Entity e) {
        e.getComponent(FlyingObjectMovement.class).setSpeedMultiplier(2.5);
    }

    @Override
    public void addRunwayModifiers(Runway r) {
    }
}

