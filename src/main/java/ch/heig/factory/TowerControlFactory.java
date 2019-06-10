package ch.heig.factory;

import ch.heig.ControlTowerGame;
import ch.heig.models.animals.Bird;
import ch.heig.models.animals.Duck;
import ch.heig.models.animals.Pier;
import ch.heig.models.flyingobjects.Chopper;
import ch.heig.models.flyingobjects.Plane;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.models.flyingobjects.shared.FlyingObjectAction;
import ch.heig.models.flyingobjects.shared.FlyingObjectMovement;
import ch.heig.ui.TowerControlType;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.util.Duration;

import static com.almasb.fxgl.app.DSLKt.play;
import static com.almasb.fxgl.app.DSLKt.texture;

public class TowerControlFactory implements EntityFactory {

    @Spawns("slow-plane")
    public Entity newSlowPlane(SpawnData data) {
        final int SLOW_PLANE_MIN = 50;
        final int SLOW_PLANE_MAX = 150;

        return Entities
                .builder()
                .from(data)
                .type(TowerControlType.PLANE)
                .viewFromNodeWithBBox(texture("plane.png", 60, 60))
                .with(new FlyingObject(((ControlTowerGame) FXGL.getApp()).getMediator()))
                .with(new Plane())
                .with(new FlyingObjectMovement(FXGLMath.random(SLOW_PLANE_MIN, SLOW_PLANE_MAX)))
                .with(new FlyingObjectAction())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("chopper")
    public Entity newChopper(SpawnData data) {
        final int CHOPPER_MIN = 50;
        final int CHOPPER_MAX = 90;

        return Entities
                .builder()
                .from(data)
                .type(TowerControlType.CHOPPER)
                .viewFromNodeWithBBox(texture("chopper.png", 80, 40))
                .with(new FlyingObject(((ControlTowerGame) FXGL.getApp()).getMediator()))
                .with(new Chopper())
                .with(new FlyingObjectMovement(FXGLMath.random(CHOPPER_MIN, CHOPPER_MAX)))
                .with(new FlyingObjectAction())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("explosion")
    public Entity newExplosion(SpawnData data) {
        play("explosion.wav");

        return Entities.builder()
                .at(data.getX() - 40, data.getY() - 40)
                // it has 16 frames ( 80 * 16 )
                .viewFromAnimatedTexture(texture("explosion.png", 80 * 16, 80).toAnimatedTexture(16, Duration.seconds(0.5)), false, true)
                .build();
    }

    @Spawns("bird")
    public Entity newBird(SpawnData data) {

        return Entities
                .builder()
                .from(data)
                .viewFromNodeWithBBox(texture("bird.png", 80, 50))
                .with(new Bird(((ControlTowerGame) FXGL.getApp()).getMediator()))
                .build();
    }

    @Spawns("duck")
    public Entity newDuck(SpawnData data) {

        return Entities
                .builder()
                .from(data)
                .viewFromNodeWithBBox(texture("duck.png", 100, 90))
                .with(new Duck(((ControlTowerGame) FXGL.getApp()).getMediator()))
                .build();
    }

    @Spawns("pier")
    public Entity newPier(SpawnData data) {

        return Entities
                .builder()
                .from(data)
                .viewFromNodeWithBBox(texture("pier.png", 100, 100))
                .with(new Pier(((ControlTowerGame) FXGL.getApp()).getMediator()))
                .build();
    }

}