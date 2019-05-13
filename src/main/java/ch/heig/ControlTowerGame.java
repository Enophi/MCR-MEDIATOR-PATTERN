package ch.heig;

import ch.heig.component.PlaneAction;
import ch.heig.factory.TowerControlFactory;
import ch.heig.ui.ControlTowerUIController;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.app.DSLKt.run;

public class ControlTowerGame extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("waiting", 0);
        vars.put("crashed", 0);
    }

    @Override
    protected void initUI() {

        ControlTowerUIController uiController = new ControlTowerUIController();
        getStateMachine().getPlayState().addStateListener(uiController);

        UI ui = getAssetLoader().loadUI("tower_control_ui.fxml", uiController);

        uiController.getLabelWaiting().textProperty().bind(getGameState().intProperty("waiting").asString("Score: [%d]"));
        uiController.getLabelCrashed().textProperty().bind(getGameState().intProperty("crashed").asString("Crashed : [%d]"));

        getGameScene().addUI(ui);

    }

    @Override
    protected void initInput() {

        Input input = getInput();

        // Add the plane to waiting zone when press W
        input.addAction(new UserAction("Ask to wait") {
            @Override
            protected void onActionBegin() {

                double size = 30;
                // Get all entities in a virtual square around the cursor
                double zoneX = input.getMousePositionWorld().getX() - (size / 2);
                double zoneY = input.getMousePositionWorld().getY() - (size / 2);

                Rectangle2D zone = new Rectangle2D(zoneX, zoneY, size, size);
                List<Entity> entities = getGameWorld().getEntitiesInRange(zone);
                entities.forEach(e -> e.getComponent(PlaneAction.class).enterWaitingZone());

            }
        }, KeyCode.W);

    }

    @Override
    protected void initGame() {
        // Add the plane factory to the game world
        getGameWorld().addEntityFactory(new TowerControlFactory());

        // Spawn plane every 1.5 seconds
        run(() -> {
            if (FXGLMath.randomBoolean())
                getGameWorld().spawn("slow-plane", FXGLMath.random(760) + 20, 0);
            else
                getGameWorld().spawn("chopper", FXGLMath.random(760) + 20, 0);

        }, Duration.seconds(1.5));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
