package ch.heig.models.flyingobjects;

import com.almasb.fxgl.entity.component.Component;

/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 16:05
 * Définit les actions spécifiques liées à un avion.
 * A gérer dans la méthode onUpdate laquelle est appelé automatiquement par le Framework
 */
public class Plane extends Component {

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }

    public String identifier() {
        return "Plane #123";
    }
}
