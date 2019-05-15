package ch.heig.factory;

import ch.heig.component.MissileMovement;
import ch.heig.component.PlaneAction;
import ch.heig.component.PlaneMovement;
import ch.heig.component.PlayerAction;
import ch.heig.ui.TowerControlType;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
                .with(new PlaneMovement(FXGLMath.random(SLOW_PLANE_MIN, SLOW_PLANE_MAX)))
                .with(new PlaneAction())
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
                .type(TowerControlType.PLANE)
                .viewFromNodeWithBBox(texture("chopper.png", 40, 40))
                .with(new PlaneMovement(FXGLMath.random(CHOPPER_MIN, CHOPPER_MAX)))
                .with(new PlaneAction())
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

    @Spawns("invader")
    public Entity newInvader(SpawnData data) {
        return Entities
                .builder()
                .from(data)
                .type(TowerControlType.INVADER)
                .viewFromNodeWithBBox(texture("invader.png", 50, 50))
                .with(new CollidableComponent(true))
                .with(new PlayerAction())
                .build();

    }

    @Spawns("missile")
    public Entity newMissile(SpawnData data) {
        return Entities
                .builder()
                .at(data.getX() - 1, data.getY() - 25)
                .viewFromNodeWithBBox(new Rectangle(2, 25, Color.BLACK))
                .type(TowerControlType.MISSILE)
                .with(new MissileMovement(100))
                .with(new CollidableComponent(true))
                .build();

    }
}