package ch.heig.models.flyingobjects.shared;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.entity.components.PositionComponent;

/**
 * Définit les mouvements liées à un objet volant, à gérer dans la méthode onUpdate
 * laquelle est appelé automatiquement par le Framework
 */
@Required(PositionComponent.class)
public class FlyingObjectMovement extends Component {

    private int speed;

    private double speedMultiplier = 0;

    private double deviationX;

    /**
     * Instantiates a new Flying object movement.
     *
     * @param speed the speed
     */
    public FlyingObjectMovement(int speed) {
        this.speed = speed;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets deviation x.
     *
     * @param deviationX the deviation x
     */
    public void setDeviationX(double deviationX) {
        this.deviationX = deviationX;
    }

    @Override
    public void onUpdate(double tpf) {
        double translateY = tpf * speed;
        if (speedMultiplier > 0)
            translateY *= speedMultiplier;
        entity.translateY(translateY);

        if (deviationX > 0) {
            double newX = tpf * speed * FXGLMath.random(deviationX);
            if (FXGLMath.randomBoolean())
                newX *= -1;
            entity.translateX(newX);
        }
    }

    /**
     * Sets speed multiplier.
     *
     * @param speedMultiplier the speed multiplier
     */
    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }
}
