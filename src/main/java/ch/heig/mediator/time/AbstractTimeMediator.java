package ch.heig.mediator.time;

import ch.heig.models.flyingobjects.Chopper;
import ch.heig.models.flyingobjects.Ovni;
import ch.heig.models.flyingobjects.Plane;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.models.runways.Runway;
import ch.heig.ui.ControlTowerUIController;
import ch.heig.ui.FlyingObjectType;
import com.almasb.fxgl.app.FXGL;
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
    final double PROGRESS_STEP = 0.1;
    protected List<Runway> runways;
    private List<Entity> flyingObjects;
    protected ControlTowerUIController uiController;

    public AbstractTimeMediator(ControlTowerUIController uiController) {
        this.flyingObjects = new LinkedList<>();
        this.runways = new LinkedList<>();
        this.uiController = uiController;
    }

    AbstractTimeMediator(AbstractTimeMediator other) {
        this.flyingObjects = new LinkedList<>(other.flyingObjects);
        this.uiController = other.getUiController();

        this.runways = new LinkedList<>();
        for (Runway r : other.runways)
            selfDestroy(r);
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
     * Announce to the mediator and enabled the runway on UI
     *
     * @param r The runway which announce
     */
    public void selfAnnounce(Runway r) {
        this.runways.add(r);
        String name = r.toString() + "_open";
        FXGL.getGameState().setValue(name, true);
    }

    /**
     * Signale that runway must be disabled on UI
     * and remove all animals on runway
     *
     * @param r The runway to disabled
     */
    public void selfDestroy(Runway r) {
        String name = r.toString() + "_open";
        FXGL.getGameState().setValue(name, false);
        r.destroyAll();
    }

    /**
     * Update all colleagues of the mediator change
     */
    public void updateAllCollegues() {
        this.flyingObjects.forEach(e -> e.getComponent(FlyingObject.class).setMediator(this));
    }

    /**
     * Authorize the landing without the penalty
     *
     * @param e entity who ask to landing
     */
    void autorhiseLanding(Entity e) {
        autorhiseLandingWithPenalties(e, 0);
    }

    /**
     * Authorize the landing with the penalty
     *
     * @param e          entity who ask to landing
     * @param penalities numbers of animals kiled
     */
    void autorhiseLandingWithPenalties(Entity e, int penalities) {
        FXGL.getGameState().setValue("playerNotif", String.format("Good game, authorized landing!!!"));
        if (e.getType() == FlyingObjectType.PLANE) {
            e.getComponent(Plane.class).onAllowLanding(penalities);
        } else if (e.getType() == FlyingObjectType.CHOPPER) {
            e.getComponent(Chopper.class).onAllowLanding(penalities);
        } else {
            e.getComponent(Ovni.class).onAllowLanding(penalities);
        }
    }

    /**
     * Landing application
     *
     * @param e      entity who asked to landing
     * @param runway runway to landing
     */
    public void askToLand(Entity e, Runway runway) {

        if(!runways.contains(runway)){
            return;
        }

        if (e.getComponent(FlyingObject.class).getEntity().getType() != FlyingObjectType.OVNI) {
            if (runway.getType() != e.getComponent(FlyingObject.class).getEntity().getType()) {
                FXGL.getGameState().setValue("playerNotif", "Wrong landing strip!!!");
                return;
            }
        }

        if (FXGL.getGameState().getDouble(runway.toString() + "_places") < runway.getMaxPlaces()) {
            FXGL.getGameState().increment(runway.toString() + "_places", PROGRESS_STEP / runway.getMaxPlaces());
            FXGL.getGameState().setValue("playerNotif", "");
            if (runway.isBlocked()) {
                autorhiseLandingWithPenalties(e, runway.getNumberOfAnimals());
                runway.destroyAll();
            } else
                autorhiseLanding(e);
            e.removeFromWorld();
        } else {
            FXGL.getGameState().setValue("playerNotif", String.format("Wait! Landing Strip #%s is full", runway.toString().split("_")[1]));
        }

    }

    public ControlTowerUIController getUiController() {
        return uiController;
    }

    public void setTimeIcon() {
        uiController.getTimeIconForeground().setFill(new ImagePattern(getTimeIconImage()));
    }

    protected abstract Image getTimeIconImage();

    /**
     * The mediator's background
     *
     * @return his color
     */
    public abstract Color getBackgroundColor();

    public List<Entity> getFlyingObjects() {
        return flyingObjects;
    }

    public List<Runway> getRunways() {
        return runways;
    }
}
