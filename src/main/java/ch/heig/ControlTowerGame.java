package ch.heig;

import ch.heig.factory.TowerControlFactory;
import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.mediator.time.DayTimeMediator;
import ch.heig.mediator.time.NightTimeMediator;
import ch.heig.mediator.weather.*;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.ui.ControlTowerUIController;
import ch.heig.ui.MouseOverAction;
import ch.heig.utils.WeightedCollection;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.app.DSLKt.*;

public class ControlTowerGame extends GameApplication {

    private AbstractTimeMediator timeMediator;
    private AbstractWeatherMediator weatherMediator;
    private ControlTowerUIController uiController = new ControlTowerUIController();
    private Rectangle weatherRectangle = new Rectangle();

    public ControlTowerGame() {
        // Init the game with the DayTimeMediator
        timeMediator = new DayTimeMediator(uiController);
        // Init the game with normal weather mediatior
        weatherMediator = new NormalWeatherMediator(this, uiController);
    }

    public AbstractTimeMediator getmediator() {
        return timeMediator;
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

        vars.put("nbInOne", 0);
        vars.put("nbInTwo", 0);
        vars.put("nbInThree", 0);
        vars.put("nbInFour", 0);
        vars.put("nbInFive", 0);

        vars.put("playerNotif", "Alert:");

        vars.put("day", true);
    }

    @Override
    protected void initUI() {
        getStateMachine().getPlayState().addStateListener(uiController);

        UI ui = getAssetLoader().loadUI("tower_control_ui.fxml", uiController);

        // Bind label
        uiController.getLabelWaiting().textProperty().bind(getip("waiting").asString("Score: [%d]"));
        uiController.getLabelCrashed().textProperty().bind(getip("crashed").asString("Crashed: [%d]"));

        uiController.getNbInAirstripOne().textProperty().bind(getip("nbInOne").asString("Strip One: [%d]"));
        uiController.getNbInAirstripTwo().textProperty().bind(getip("nbInTwo").asString("Strip Two: [%d]"));
        uiController.getNbInAirstripThree().textProperty().bind(getip("nbInThree").asString("Strip Three: [%d]"));
        uiController.getNbInAirstripFour().textProperty().bind(getip("nbInFour").asString("Strip Four: [%d]"));
        uiController.getNbInAirstripFive().textProperty().bind(getip("nbInFive").asString("Strip Five: [%d]"));

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
        getGameScene().setBackgroundColor(timeMediator.getBackgroundColor());

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

        // Time Icon
        Circle timeIconBackground = uiController.getTimeIconBackground();
        Circle timeIconForeground = uiController.getTimeIconForeground();
        double timeIconX = getWidth() / 2 - 40 * 2;
        double timeIconY = 35.0;

        timeIconBackground.setCenterX(timeIconX);
        timeIconBackground.setCenterY(timeIconY);
        timeIconBackground.setFill(Color.AQUAMARINE);
        timeIconForeground.setCenterX(timeIconX);
        timeIconForeground.setCenterY(timeIconY);
        timeMediator.setTimeIcon();

        // Weather Icon
        Circle weatherIconBackground = uiController.getWeatherIconBackground();
        Circle WeatherIconForeground = uiController.getWeatherIconForeground();
        double weatherIconX = getWidth() / 2 + 40 * 2;
        double weatherIconY = timeIconY;

        weatherIconBackground.setCenterX(weatherIconX);
        weatherIconBackground.setCenterY(weatherIconY);
        weatherIconBackground.setFill(Color.AQUAMARINE);
        WeatherIconForeground.setCenterX(weatherIconX);
        WeatherIconForeground.setCenterY(weatherIconY);
        weatherMediator.setWeatherIcon();
        weatherMediator.setWeatherBackground();
    }

    @Override
    protected void initInput() {

        Input input = getInput();

        // Define the different user inputs
        input.addAction(new MouseOverAction("Land to 1", input, e -> e.getComponent(FlyingObject.class).askToLand(1)), KeyCode.DIGIT1);
        input.addAction(new MouseOverAction("Land to 2", input, e -> e.getComponent(FlyingObject.class).askToLand(2)), KeyCode.DIGIT2);
        input.addAction(new MouseOverAction("Land to 3", input, e -> e.getComponent(FlyingObject.class).askToLand(3)), KeyCode.DIGIT3);
        input.addAction(new MouseOverAction("Land to 4", input, e -> e.getComponent(FlyingObject.class).askToLand(4)), KeyCode.DIGIT4);
        input.addAction(new MouseOverAction("Land to 5", input, e -> e.getComponent(FlyingObject.class).askToLand(5)), KeyCode.DIGIT5);
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
                timeMediator = new NightTimeMediator(timeMediator);
                getGameState().setValue("day", false);
            } else {
                timeMediator = new DayTimeMediator(timeMediator);
                getGameState().setValue("day", true);
            }

            // Notify all colleagues of the timeMediator change
            timeMediator.updateAllCollegues();
            timeMediator.setTimeIcon();

            getGameScene().setBackgroundColor(timeMediator.getBackgroundColor());
        }, Duration.seconds(8));

        initWeather();
    }

    private void initWeather() {
        weatherRectangle.setWidth(getWidth());
        weatherRectangle.setHeight(getHeight());
        EntityView weatherView = new EntityView();
        weatherView.addNode(weatherRectangle);

        Entities.builder()
                .viewFromNode(weatherView)
                .with(new IrremovableComponent())
                .buildAndAttach(getGameWorld());

        run(() -> {
            // Populate weather collection with different weights
            WeightedCollection<AbstractWeatherMediator> weatherCollection  = new WeightedCollection<>();
            weatherCollection.add(5, new NormalWeatherMediator(this, uiController));
            weatherCollection.add(5, new FogWeatherMediator(this, uiController));
            weatherCollection.add(5, new RainWeatherMediator(this, uiController));
            weatherCollection.add(4, new CloudyWeatherMediator(this, uiController));
            weatherCollection.add(4, new BrightWeatherMediator(this, uiController));
            weatherCollection.add(3, new SnowWeatherMediator(this, uiController));
            weatherCollection.add(3, new LightningWeatherMediator(this, uiController));
            weatherCollection.add(3, new RainbowWeatherMediator(this, uiController));
            weatherCollection.add(2, new HeavyRainWeatherMediator(this, uiController));
            weatherCollection.add(2, new BigFogWeatherMediator(this, uiController));
            weatherCollection.add(1, new HurricaneWeatherMediator(this, uiController));

            weatherMediator = weatherCollection.next();
            weatherMediator.setWeatherIcon();
            weatherMediator.setWeatherBackground();

        }, Duration.seconds(weatherMediator.getDuration()));
    }

    public void setWeatherBackground(ImagePattern weatherImagePattern) {
        weatherRectangle.setFill(weatherImagePattern);
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
