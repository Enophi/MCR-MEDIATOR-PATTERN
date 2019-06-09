package ch.heig.models.flyingobjects;

import ch.heig.ui.TowerControlType;
import com.almasb.fxgl.entity.component.Component;


/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 16:06
 * Définit les actions spécifiques liées à un hélicoptère.
 * A gérer dans la méthode onUpdate laquelle est appelé automatiquement par le Framework
 */
public class Chopper extends Component {

    private final static TowerControlType TYPE = TowerControlType.PLANE;

    public static TowerControlType getTYPE() {
        return TYPE;
    }

    public String identifier() {
        return "Chopper 456546";
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }
}
