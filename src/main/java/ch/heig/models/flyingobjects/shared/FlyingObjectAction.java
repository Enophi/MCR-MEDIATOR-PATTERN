package ch.heig.models.flyingobjects.shared;


import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.app.DSLKt.play;
import static com.almasb.fxgl.app.DSLKt.spawn;

/**
 * Définit les actions liées à un objet volant.
 * A gérer dans la méthode onUpdate laquelle est appelé automatiquement par le Framework
 */
public class FlyingObjectAction extends Component {
    /**
     * The Random crash.
     */
    int randomCrash = 0;

    @Override
    public void onUpdate(double tpf) {
        if (getEntity().getPosition().getY() + getEntity().getHeight() > 500)
            crash();

        if (randomCrash > 0 && FXGLMath.random(10000) <= randomCrash)
            crash();
    }

    /**
     * Enter waiting zone.
     */
    public void enterWaitingZone() {
        getEntity().removeFromWorld();
        FXGL.getGameState().increment("waiting", 1);

        play("roger.wav");
    }

    private void crash() {
        FXGL.getGameState().increment("crashed", 1);
        getEntity().removeFromWorld();

        if (FXGL.getGameState().getInt("score") > 10)
            FXGL.getGameState().increment("score", -10);

        spawn("explosion", getEntity().getCenter());
    }

    /**
     * Sets random crash.
     *
     * @param randomCrash the random crash
     */
    public void setRandomCrash(int randomCrash) {
        this.randomCrash = randomCrash;
    }
}
