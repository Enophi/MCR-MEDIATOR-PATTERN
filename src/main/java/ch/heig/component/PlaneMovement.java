package ch.heig.component;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.entity.components.PositionComponent;

@Required(PositionComponent.class)
public class PlaneMovement extends Component {

    private int speed;

    public PlaneMovement(int speed) {
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
