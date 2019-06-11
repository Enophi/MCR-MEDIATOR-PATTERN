/**
 * created by Aleksandar Milenkovic
 * 06.06.2019
 * 00:26
 */

package ch.heig.models.flyingobjects;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;

/**
 * The type Ovni.
 */
public class Ovni extends Component {

    private final int landingScore = 100;

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }

    /**
     * On allow landing.
     *
     * @param penalities the penalities
     */
    public void onAllowLanding(int penalities) {
        FXGL.getGameState().increment("score", landingScore - penalities);
    }

}
