package ch.heig.models.flyingobjects;

import ch.heig.ui.TowerControlType;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 16:05
 * Définit les actions spécifiques liées à un avion.
 * A gérer dans la méthode onUpdate laquelle est appelé automatiquement par le Framework
 */
public class Plane extends Component {
    private final int landingScore = 10;

    private final static TowerControlType TYPE = TowerControlType.PLANE;

    public static TowerControlType getTYPE() {
        return TYPE;
    }

    public void onAllowLanding() {
        FXGL.getGameState().increment("score", landingScore);
    }

    public String identifier() {
        return "Plane #123";
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }
}
