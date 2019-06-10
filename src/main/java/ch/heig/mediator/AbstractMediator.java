package ch.heig.mediator;

import ch.heig.models.flyingobjects.Chopper;
import ch.heig.models.flyingobjects.Ovni;
import ch.heig.models.flyingobjects.Plane;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.models.runways.AbstractRunway;
import ch.heig.ui.FlyingObjectType;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:46
 */
public abstract class AbstractMediator {
    final double MAX_PROGRESS = 1.0;
    final double PROGRESS_STEP = 0.1;

    private List<Entity> flyingObjects;
    private List<AbstractRunway> landingRunways;

    public AbstractMediator() {
        flyingObjects = new LinkedList<>();
        landingRunways = new LinkedList<>();
    }

    AbstractMediator(AbstractMediator other) {
        this.flyingObjects = new LinkedList<>(other.flyingObjects);
        this.landingRunways = new LinkedList<>(other.landingRunways);
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

    void autorhiseLanding(Entity e) {
        FXGL.getGameState().setValue("playerNotif", String.format("Good game, authorized landing!!!"));
        if (e.getType() == FlyingObjectType.PLANE) {
            e.getComponent(Plane.class).onAllowLanding();
        } else if (e.getType() == FlyingObjectType.CHOPPER) {
            e.getComponent(Chopper.class).onAllowLanding();
        } else {
            e.getComponent(Ovni.class).onAllowLanding();
        }
    }

    void setAlertRunvay(int piste, String msg) {
        if (!msg.equals(""))
            FXGL.getGameState().setValue("playerNotif", String.format("%s airstrip!!!", msg));
        else
            FXGL.getGameState().setValue("playerNotif", String.format("Airstrip %d full!!!", piste));
    }

    public abstract Color getBackgroundColor();

    public abstract void askToLand(Entity e, int piste);
}
