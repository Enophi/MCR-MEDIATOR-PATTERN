package ch.heig.component;


import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.app.DSLKt.play;
import static com.almasb.fxgl.app.DSLKt.spawn;

public class PlaneAction extends Component {

    @Override
    public void onUpdate(double tpf) {
        if (getEntity().getPosition().getY() > 550)
            crash();
    }

    public void enterWaitingZone() {
        getEntity().removeFromWorld();
        FXGL.getGameState().increment("waiting", 1);

        play("roger.wav");
    }

    public void crash() {
        FXGL.getGameState().increment("crashed", 1);
        getEntity().removeFromWorld();

        spawn("explosion", getEntity().getCenter());
    }
}
