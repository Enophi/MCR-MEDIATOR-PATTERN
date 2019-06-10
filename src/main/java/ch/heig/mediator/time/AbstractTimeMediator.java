package ch.heig.mediator.time;

import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.models.runways.AbstractRunway;
import ch.heig.ui.ControlTowerUIController;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.LinkedList;
import java.util.List;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:46
 */
public abstract class AbstractTimeMediator {

    private List<Entity> flyingObjects;
    private List<AbstractRunway> landingRunways;
    protected ControlTowerUIController uiController;

    public AbstractTimeMediator(ControlTowerUIController uiController) {
        this.flyingObjects = new LinkedList<>();
        this.landingRunways = new LinkedList<>();
        this.uiController = uiController;
    }

    AbstractTimeMediator(AbstractTimeMediator other) {
        this.flyingObjects = new LinkedList<>(other.flyingObjects);
        this.landingRunways = new LinkedList<>(other.landingRunways);
        this.uiController = other.getUiController();
    }

    /**
     * Announce to the mediator
     *
     * @param e The entity which announce
     */
    public void selfAnnounce(Entity e) {
        this.flyingObjects.add(e);
    }

    /**
     * Remove the game entity of the list
     *
     * @param e The entity to remove
     */
    public void selfDestroy(Entity e) {
        this.flyingObjects.remove(e);
    }

    /**
     * Update all colleagues of the mediator change
     */
    public void updateAllCollegues() {
        this.flyingObjects.forEach(e -> e.getComponent(FlyingObject.class).setMediator(this));
    }

    public abstract Color getBackgroundColor();

    public abstract void askToLand(Entity e, int piste);

    public ControlTowerUIController getUiController() {
        return uiController;
    }

    public void setTimeIcon() {
        uiController.getTimeIconForeground().setFill(new ImagePattern(getTimeIconImage()));
    }

    protected abstract Image getTimeIconImage();
}
