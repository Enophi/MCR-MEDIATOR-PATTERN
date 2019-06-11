package ch.heig.mediator.weather;

import ch.heig.ControlTowerGame;
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
public class NormalWeatherMediator extends AbstractWeatherMediator {

    private static final Image normalIcon = new Image("assets/icons/normal.png");
    private static final Image normalBackground = new Image("assets/textures/blank.png");
    private static final ImagePattern normalImagePattern = new ImagePattern(normalBackground);

    /**
     * Instantiates a new Normal weather mediator.
     *
     * @param game         the game
     * @param uiController the ui controller
     */
    public NormalWeatherMediator(ControlTowerGame game, ControlTowerUIController uiController) {
        super(game, uiController);
    }

    /**
     * Instantiates a new Normal weather mediator.
     *
     * @param awm the awm
     */
    public NormalWeatherMediator(AbstractWeatherMediator awm) {
        super(awm);
    }

    @Override
    protected Image getWeatherIconImage() {
        return normalIcon;
    }

    @Override
    protected Image getWeatherBackgroundImage() {
        return normalBackground;
    }

    @Override
    protected ImagePattern getWeatherImagePattern() {
        return normalImagePattern;
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
    }

    @Override
    public void addRunwayModifiers(Runway r) {
    }
}

