package ch.heig;

import ch.heig.factory.TowerControlFactory;
import ch.heig.mediator.AbstractMediator;
import ch.heig.mediator.DayMediator;
import ch.heig.mediator.NightMediator;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.ui.ControlTowerUIController;
import ch.heig.ui.MouseOverAction;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.app.DSLKt.run;

public class ControlTowerGame extends GameApplication {

    private AbstractMediator mediator;

    public ControlTowerGame() {
        // Init the game with the DayMediator
        mediator = new DayMediator();
    }

    public AbstractMediator getmediator() {
        return mediator;
    }

    @Override
    protected void initSettings(GameSettings settings) {

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("waiting", 0);
        vars.put("crashed", 0);
        vars.put("nbInOne", 0);
        vars.put("nbInTwo", 0);
        vars.put("nbInThree", 0);
        vars.put("day", true);
    }

    @Override
    protected void initUI() {

        ControlTowerUIController uiController = new ControlTowerUIController();
        getStateMachine().getPlayState().addStateListener(uiController);

        UI ui = getAssetLoader().loadUI("tower_control_ui.fxml", uiController);

        // Bind label
        uiController.getLabelWaiting().textProperty().bind(getGameState().intProperty("waiting").asString("Score: [%d]"));
        uiController.getLabelCrashed().textProperty().bind(getGameState().intProperty("crashed").asString("Crashed : [%d]"));
        uiController.getNbInAirstripOne().textProperty().bind(getGameState().intProperty("nbInOne").asString("Strip One : [%d]"));
        uiController.getNbInAirstripTwo().textProperty().bind(getGameState().intProperty("nbInTwo").asString("Strip Two : [%d]"));
        uiController.getNbInAirstripThree().textProperty().bind(getGameState().intProperty("nbInThree").asString("Strip Three : [%d]"));

        // Bind visible property
        uiController.getNbInAirstripOne().disableProperty().bind(getGameState().booleanProperty("day").not());
        uiController.getNbInAirstripTwo().disableProperty().bind(getGameState().booleanProperty("day").not());
        uiController.getNbInAirstripThree().disableProperty().bind(getGameState().booleanProperty("day"));

        getGameScene().addUI(ui);
        getGameScene().setBackgroundColor(mediator.getBackgroundColor());

    }

    @Override
    protected void initInput() {

        Input input = getInput();

        // Define the different user inputs
        input.addAction(new MouseOverAction("Land to 1", input, e -> e.getComponent(FlyingObject.class).askToLand(1)), KeyCode.DIGIT1);
        input.addAction(new MouseOverAction("Land to 2", input, e -> e.getComponent(FlyingObject.class).askToLand(2)), KeyCode.DIGIT2);
        input.addAction(new MouseOverAction("Land to 3", input, e -> e.getComponent(FlyingObject.class).askToLand(3)), KeyCode.DIGIT3);
    }

    @Override
    protected void initGame() {
        // Add the plane factory to the game world
        getGameWorld().addEntityFactory(new TowerControlFactory());

        // Spawn plane every 1.5 seconds
        run(() -> {
            if (FXGLMath.randomBoolean()) {
                Entity e = getGameWorld().spawn("slow-plane", FXGLMath.random(760) + 20, 0);
                e.getComponent(FlyingObject.class).selfAnnounce();
            } else {
                Entity e = getGameWorld().spawn("chopper", FXGLMath.random(760) + 20, 0);
                e.getComponent(FlyingObject.class).selfAnnounce();
            }

        }, Duration.seconds(1.5));

        run(() -> {
            if (getGameState().getBoolean("day")) {
                mediator = new NightMediator(mediator);
                getGameState().setValue("day", false);
            } else {
                mediator = new DayMediator(mediator);
                getGameState().setValue("day", true);
            }

            // Notify all colleagues of the mediator change
            mediator.updateAllCollegues();

            getGameScene().setBackgroundColor(mediator.getBackgroundColor());
        }, Duration.seconds(8));
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
