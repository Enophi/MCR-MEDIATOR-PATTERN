package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
import ch.heig.models.flyingobjects.shared.FlyingObjectAction;
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
public class CloudyWeatherMediator extends AbstractWeatherMediator {

    private static final Image cloudyIcon = new Image("assets/icons/cloud.png");
    private static final Image cloudyBackground = new Image("assets/textures/cloudy.png");
    private static final ImagePattern cloudyImagePattern = new ImagePattern(cloudyBackground);

    /**
     * Instantiates a new Cloudy weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public CloudyWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Cloudy weather mediator.
     *
     * @param awm the awm
     */
    public CloudyWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
    }

    @Override
    protected Image getWeatherIconImage() {
        return cloudyIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return cloudyBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return cloudyImagePattern;
    }

    @Override
    protected int getMinDuration() {
        return 7;
    }

    @Override
    protected int getMaxDuration() {
        return 13;
    }

    @Override
    public void addFlyingModifiers(Entity e) {
        e.getComponent(FlyingObjectAction.class).setRandomCrash(1);
    }

    @Override
    public void addRunwayModifiers(Runway r) {
    }
}

