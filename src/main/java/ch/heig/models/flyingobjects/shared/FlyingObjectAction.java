package ch.heig.models.flyingobjects.shared;


import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.app.DSLKt.play;
import static com.almasb.fxgl.app.DSLKt.spawn;

/**
 * Définit les actions liées à un objet volant.
 * A gérer dans la méthode onUpdate laquelle est appelé automatiquement par le Framework
 */
public class FlyingObjectAction extends Component {

    @Override
    public void onUpdate(double tpf) {
        if (getEntity().getPosition().getY() + getEntity().getHeight() > 500)
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
