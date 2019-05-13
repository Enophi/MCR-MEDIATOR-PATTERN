package ch.heig;

import ch.heig.component.PlaneComponent;
import ch.heig.ui.ControlTowerUIController;
import ch.heig.ui.TowerControlType;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.app.DSLKt.run;
import static com.almasb.fxgl.app.DSLKt.texture;

public class ControlTowerGame extends GameApplication {

    private ControlTowerUIController uiController;

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

        uiController = new ControlTowerUIController();
        getStateMachine().getPlayState().addStateListener(uiController);

        UI ui = getAssetLoader().loadUI("tower_control_ui.fxml", uiController);

        uiController.getLabelWaiting().textProperty().bind(getGameState().intProperty("waiting").asString("Score: [%d]"));
        uiController.getLabelCrashed().textProperty().bind(getGameState().intProperty("crashed").asString("Crashed : [%d]"));

        getGameScene().addUI(ui);

    }

    @Override
    protected void initGame() {
        run(() -> spawnPlane(), Duration.seconds(2));
    }

    private Entity spawnPlane() {
        return Entities
                .builder()
                .type(TowerControlType.PLANE)
                .at(FXGLMath.random(800), 0)
                .viewFromNodeWithBBox(texture("plane.png", 60, 60))
                .with(new CollidableComponent(true))
                .with(new PlaneComponent(FXGLMath.random(100)))
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
