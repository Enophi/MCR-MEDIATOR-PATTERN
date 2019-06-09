package ch.heig.mediator;

import ch.heig.models.animals.Animal;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.models.runways.Runway;
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

    private List<Entity> flyingObjects;
    protected List<Runway> runways;
    protected List<Animal> animals;

    public AbstractMediator() {
        flyingObjects = new LinkedList<>();
        runways = new LinkedList<>();
        animals = new LinkedList<>();
    }

    AbstractMediator(AbstractMediator other) {
        this.flyingObjects = new LinkedList<>(other.flyingObjects);
        this.runways = new LinkedList<>(other.runways);
        this.animals = new LinkedList<>(other.animals);
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
     * Announce to the mediator
     *
     * @param r The runway which announce
     */
    public void selfAnnounce(Runway r) {
        this.runways.add(r);
    }

    /**
     * Remove the game runway of the list
     *
     * @param r The runway to remove
     */
    public void selfDestroy(Runway r) {
        this.runways.remove(r);
    }

    /**
     * Announce to the mediator
     *
     * @param a The animal which announce
     */
    public void selfAnnounce(Animal a) {
        this.animals.add(a);
    }

    /**
     * Remove the game entity of the list
     *
     * @param a The animal to remove
     */
    public void selfDestroy(Animal a) {
        this.animals.remove(a);
    }

    /**
     * Update all colleagues of the mediator change
     */
    public void updateAllCollegues() {
        this.flyingObjects.forEach(e -> e.getComponent(FlyingObject.class).setMediator(this));
        this.runways.forEach((Runway r) -> r.setMediator(this));
        //this.animals.forEach(e -> e.getComponent(FlyingObject.class).setMediator(this));
    }

    public void askToLand(Entity e, Runway runway) {

        if (runway.isOpen()) {
            if (FXGL.getGameState().getInt(runway.toString()) < runway.getSpaces()) {
                FXGL.getGameState().increment(runway.toString(), 1);
                e.removeFromWorld();
            } else {
                FXGL.getGameState().setValue("playerNotif", String.format("Landing Strip #%s is full !", runway.toString().split("_")[1]));
            }
        } else {
            FXGL.getGameState().setValue("playerNotif", String.format("Landing Strip #%s closed", runway.toString().split("_")[1]));
        }

    }

    public abstract Color getBackgroundColor();
    public abstract void setOpenedRunways();
}
