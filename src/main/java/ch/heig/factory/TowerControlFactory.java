package ch.heig.factory;

import ch.heig.ControlTowerGame;
import ch.heig.models.livingobjects.Bird;
import ch.heig.models.livingobjects.Duck;
import ch.heig.models.livingobjects.Pier;
import ch.heig.models.flyingobjects.Chopper;
import ch.heig.models.flyingobjects.Ovni;
import ch.heig.models.flyingobjects.Plane;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.models.flyingobjects.shared.FlyingObjectAction;
import ch.heig.models.flyingobjects.shared.FlyingObjectMovement;
import ch.heig.ui.FlyingObjectType;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.util.Duration;

import static com.almasb.fxgl.app.DSLKt.play;
import static com.almasb.fxgl.app.DSLKt.texture;

/**
 * The type Tower control factory.
 */
public class TowerControlFactory implements EntityFactory {

    /**
     * New slow plane entity.
     *
     * @param data the data
     * @return the entity
     */
    @Spawns("plane")
    public Entity newSlowPlane(SpawnData data) {
        final int SLOW_PLANE_MIN = 40;
        final int SLOW_PLANE_MAX = 130;

        return Entities
                .builder()
                .from(data)
                .type(FlyingObjectType.PLANE)
                .viewFromNodeWithBBox(texture("plane.png", 60, 60))
                .with(new FlyingObject(((ControlTowerGame) FXGL.getApp()).getMediator()))
                .with(new Plane())
                .with(new FlyingObjectMovement(FXGLMath.random(SLOW_PLANE_MIN, SLOW_PLANE_MAX)))
                .with(new FlyingObjectAction())
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * New chopper entity.
     *
     * @param data the data
     * @return the entity
     */
    @Spawns("chopper")
    public Entity newChopper(SpawnData data) {
        final int CHOPPER_MIN = 50;
        final int CHOPPER_MAX = 90;

        return Entities
                .builder()
                .from(data)
                .type(FlyingObjectType.CHOPPER)
                .viewFromNodeWithBBox(texture("chopper.png", 80, 40))
                .with(new FlyingObject(((ControlTowerGame) FXGL.getApp()).getMediator()))
                .with(new Chopper())
                .with(new FlyingObjectMovement(FXGLMath.random(CHOPPER_MIN, CHOPPER_MAX)))
                .with(new FlyingObjectAction())
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * New ovni entity.
     *
     * @param data the data
     * @return the entity
     */
    @Spawns("ovni")
    public Entity newOvni(SpawnData data) {
        final int OVNI_MIN = 150;
        final int OVNI_MAX = 250;

        return Entities
                .builder()
                .from(data)
                .type(FlyingObjectType.OVNI)
                .viewFromNodeWithBBox(texture("ovni.png", 50, 50))
                .with(new FlyingObject(((ControlTowerGame) FXGL.getApp()).getMediator()))
                .with(new Ovni())
                .with(new FlyingObjectMovement(FXGLMath.random(OVNI_MIN, OVNI_MAX)))
                .with(new FlyingObjectAction())
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * New explosion entity.
     *
     * @param data the data
     * @return the entity
     */
    @Spawns("explosion")
    public Entity newExplosion(SpawnData data) {
        play("explosion.wav");

        return Entities.builder()
                .at(data.getX() - 40, data.getY() - 40)
                // it has 16 frames ( 80 * 16 )
                .viewFromAnimatedTexture(texture("explosion.png", 80 * 16, 80).toAnimatedTexture(16, Duration.seconds(0.5)), false, true)
                .build();
    }

    /**
     * New bird entity.
     *
     * @param data the data
     * @return the entity
     */
    @Spawns("bird")
    public Entity newBird(SpawnData data) {

        return Entities
                .builder()
                .from(data)
                .viewFromNodeWithBBox(texture("bird.png", 40, 40))
                .with(new Bird(((ControlTowerGame) FXGL.getApp()).getRunway()))
                .build();
    }

    /**
     * New duck entity.
     *
     * @param data the data
     * @return the entity
     */
    @Spawns("duck")
    public Entity newDuck(SpawnData data) {

        return Entities
                .builder()
                .from(data)
                .viewFromNodeWithBBox(texture("duck.png", 40, 40))
                .with(new Duck(((ControlTowerGame) FXGL.getApp()).getRunway()))
                .build();
    }

    /**
     * New pier entity.
     *
     * @param data the data
     * @return the entity
     */
    @Spawns("pier")
    public Entity newPier(SpawnData data) {

        return Entities
                .builder()
                .from(data)
                .viewFromNodeWithBBox(texture("pier.png", 100, 100))
                .with(new Pier(((ControlTowerGame) FXGL.getApp()).getRunway()))
                .build();
    }
}