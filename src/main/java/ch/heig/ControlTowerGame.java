package ch.heig;

import ch.heig.factory.TowerControlFactory;
import ch.heig.mediator.AbstractMediator;
import ch.heig.mediator.DayMediator;
import ch.heig.mediator.NightMediator;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.models.runways.ChopperRunway;
import ch.heig.models.runways.PlaneRunway;
import ch.heig.models.runways.Runway;
import ch.heig.ui.ControlTowerUIController;
import ch.heig.ui.MouseOverAction;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.app.DSLKt.*;

public class ControlTowerGame extends GameApplication {

    private AbstractMediator mediator;
    private List<Runway> runways = new ArrayList<>();

    public ControlTowerGame() {
        // Init the game with the DayMediator
        mediator = new DayMediator();

        for (int i = 0; i < 3; i++)
            runways.add(new PlaneRunway(0, true, mediator));

        for (int i = 0; i < 2; i++)
            runways.add(new ChopperRunway(0, true, mediator));
    }

    public AbstractMediator getmediator() {
        return mediator;
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("FXGL Control Tower");
        settings.setVersion("1.0");
        //settings.setProfilingEnabled(true); // statistique fps, etc...
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("time", 120);

        vars.put("waiting", 0);
        vars.put("crashed", 0);

        for (Runway runway : runways)
            vars.put("runway_" + runway.getID(), runway.getSpaces());

        vars.put("playerNotif", "Alert:");

        vars.put("day", true);
    }

    @Override
    protected void initUI() {

        ControlTowerUIController uiController = new ControlTowerUIController();
        getStateMachine().getPlayState().addStateListener(uiController);

        UI ui = getAssetLoader().loadUI("tower_control_ui.fxml", uiController);

        // Bind label
        uiController.getLabelWaiting().textProperty().bind(getip("waiting").asString("Score: [%d]"));
        uiController.getLabelCrashed().textProperty().bind(getip("crashed").asString("Crashed: [%d]"));

        for (Runway runway : runways)
            uiController.getNbInAirstripOne().textProperty().bind(getip("runway_" + runway.getID()).asString("Strip #" + runway.getID() + ": [%d]"));

        uiController.getPlayerNotif().textProperty().bind(getsp("playerNotif"));

        // Bind visible property day
        uiController.getNbInAirstripOne().visibleProperty().bind(getGameState().booleanProperty("day"));
        uiController.getNbInAirstripTwo().visibleProperty().bind(getGameState().booleanProperty("day"));
        uiController.getNbInAirstripFive().visibleProperty().bind(getGameState().booleanProperty("day"));
        uiController.getChopper2().visibleProperty().bind(getGameState().booleanProperty("day"));

        // Bind visible property night
        uiController.getNbInAirstripThree().visibleProperty().bind(getGameState().booleanProperty("day").not());
        uiController.getNbInAirstripFour().visibleProperty().bind(getGameState().booleanProperty("day").not());
        uiController.getChopper1().visibleProperty().bind(getGameState().booleanProperty("day").not());

        getGameScene().addUI(ui);
        getGameScene().setBackgroundColor(mediator.getBackgroundColor());
        mediator.setOpenedRunways();

        // Compteur durée de la partie
        Text timerText = getUIFactory().newText("", Color.WHITE, 28);
        timerText.layoutBoundsProperty().addListener((o, old, bounds) -> {
            timerText.setTranslateX(getWidth() / 2 - bounds.getWidth() / 2);
        });

        timerText.setTranslateX(getWidth() / 2);
        timerText.setTranslateY(60);
        timerText.textProperty().bind(getip("time").asString());

        Circle timerCircle = new Circle(40, 40, 40, null);
        timerCircle.setStrokeWidth(2);
        timerCircle.setStroke(Color.AQUA);
        timerCircle.setTranslateX(getWidth() / 2 - 40);
        timerCircle.setTranslateY(60 - 40 - 5);

        getGameScene().addUINodes(timerText, timerCircle);

    }

    @Override
    protected void initInput() {

        Input input = getInput();

        // Define the different user inputs
        input.addAction(new MouseOverAction("Land to 1", input, e -> e.getComponent(FlyingObject.class).askToLand(runways.get(0))), KeyCode.DIGIT1);
        input.addAction(new MouseOverAction("Land to 2", input, e -> e.getComponent(FlyingObject.class).askToLand(runways.get(1))), KeyCode.DIGIT2);
        input.addAction(new MouseOverAction("Land to 3", input, e -> e.getComponent(FlyingObject.class).askToLand(runways.get(2))), KeyCode.DIGIT3);
        input.addAction(new MouseOverAction("Land to 4", input, e -> e.getComponent(FlyingObject.class).askToLand(runways.get(3))), KeyCode.DIGIT4);
        input.addAction(new MouseOverAction("Land to 5", input, e -> e.getComponent(FlyingObject.class).askToLand(runways.get(4))), KeyCode.DIGIT5);
    }

    @Override
    protected void initGame() {
        // Add the plane factory to the game world
        getGameWorld().addEntityFactory(new TowerControlFactory());

        // Spawn plane every 1.5 seconds
        run(() -> {
            if (FXGLMath.randomBoolean()) {
                Entity e = getGameWorld().spawn("slow-plane", FXGLMath.random(1220) + 20, 0);
                e.getComponent(FlyingObject.class).selfAnnounce();
            } else {
                Entity e = getGameWorld().spawn("chopper", FXGLMath.random(1180) + 20, 0);
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
        }, Duration.seconds(20));

        // Timer jeu
        getMasterTimer().runAtInterval(() -> inc("time", -1), Duration.seconds(1));
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }

    @Override
    protected void onPostUpdate(double tpf) {
        if (geti("time") == 0) {
            getDisplay().showMessageBox("Demo Over. You crashed total: " + getGameState().getInt("crashed") + " objects!", this::exit);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
