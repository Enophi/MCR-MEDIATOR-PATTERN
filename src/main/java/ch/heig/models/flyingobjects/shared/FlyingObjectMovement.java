package ch.heig.models.flyingobjects.shared;

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

    public FlyingObjectMovement(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateY(tpf * speed);
    }
}
