package ch.heig.models.flyingobjects.shared;

import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.models.runways.Runway;
import com.almasb.fxgl.entity.component.Component;

/**
 * created by Aleksandar Milenkovic
 * 30.05.2019
 * 01:05
 * Définit les méthodes liées à un objet volant.
 * Ces méthodes sont liées aux écouteurs des touches ou au
 * mouvement de la souris directement dans UI
 */
public class FlyingObject extends Component implements FlyingObjectMediator {
    private AbstractTimeMediator _mediator;

    /**
     * Constructer
     *
     * @param mediator médiateur
     */
    public FlyingObject(AbstractTimeMediator mediator) {
        _mediator = mediator;
    }

    /**
     * Demande la permission d’atterrir à son médiateur
     *
     * @param runway la piste d'atterrissage
     */
    @Override
    public void askToLand(Runway runway) {
        _mediator.askToLand(getEntity(), runway);
    }

    /**
     * S’annonce auprès de son médiateur
     */
    @Override
    public void selfAnnounce() {
        _mediator.selfAnnounce(getEntity());
    }

    /**
     * Set le médiateur
     *
     * @param mediator médiateur
     */
    @Override
    public void setMediator(AbstractTimeMediator mediator) {
        _mediator = mediator;
    }

    /**
     * Est appelé lorsqu’un objet est détruit
     * On communique aussi au médiateur de supprimer l'objet
     */
    @Override
    public void onRemoved() {
        super.onRemoved();
        _mediator.selfDestroy(getEntity());
    }
}
