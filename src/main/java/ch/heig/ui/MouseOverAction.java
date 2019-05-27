package ch.heig.ui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Rectangle2D;

import java.util.List;
import java.util.function.Consumer;

public class MouseOverAction extends UserAction {

    private Input _input;
    private Consumer<Entity> _c;

    /**
     * Construct the mouse over action
     *
     * @param name  The name of the action
     * @param input The game input
     */
    public MouseOverAction(String name, Input input, Consumer<Entity> c) {
        super(name);
        _input = input;
        _c = c;
    }

    @Override
    protected void onActionBegin() {

        // Get all entities in a virtual square around the cursor
        double zoneX = _input.getMousePositionWorld().getX();
        double zoneY = _input.getMousePositionWorld().getY();

        Rectangle2D zone = new Rectangle2D(zoneX, zoneY, 10, 10);

        List<Entity> entities = FXGL.getGameWorld().getEntitiesInRange(zone);
        entities.forEach(e -> _c.accept(e));
    }
}
