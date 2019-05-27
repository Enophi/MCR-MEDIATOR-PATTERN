package ch.heig.component;

import ch.heig.mediator.AbstractMediator;
import com.almasb.fxgl.entity.component.Component;

public class MediatorComponent extends Component {

    private AbstractMediator _mediator;

    public MediatorComponent(AbstractMediator mediator) {
        _mediator = mediator;
    }

    public void askToLand(int piste) {
        _mediator.askToLand(getEntity(), piste);
    }

    public void selfAnnounce() {
        _mediator.selfAnnounce(getEntity());
    }

    public void setMediator(AbstractMediator mediator) {
        _mediator = mediator;
    }

    @Override
    public void onRemoved() {
        super.onRemoved();
        _mediator.selfDestroy(getEntity());
    }
}
