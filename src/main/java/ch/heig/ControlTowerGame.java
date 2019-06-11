package ch.heig;

import ch.heig.factory.TowerControlFactory;
import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.mediator.time.DayTimeMediator;
import ch.heig.mediator.time.NightTimeMediator;
import ch.heig.mediator.weather.*;
import ch.heig.models.animals.Bird;
import ch.heig.models.animals.Duck;
import ch.heig.models.animals.Pier;
import ch.heig.models.flyingobjects.shared.FlyingObject;
import ch.heig.models.runways.ChopperRunway;
import ch.heig.models.runways.PlaneRunway;
import ch.heig.models.runways.Runway;
import ch.heig.ui.ControlTowerUIController;
import ch.heig.ui.MouseOverAction;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ch.heig.utils.Rand.getRandomInt;
import static com.almasb.fxgl.app.DSLKt.*;

public class ControlTowerGame extends GameApplication {

    private AbstractTimeMediator timeMediator;
    private AbstractWeatherMediator weatherMediator;
    private ControlTowerUIController uiController = new ControlTowerUIController();
    private Rectangle weatherRectangle = new Rectangle();

    private List<Runway> runways = new ArrayList<>(5);
    private int index;

    public ControlTowerGame() {
        // Init the game with the DayTimeMediator
        timeMediator = new DayTimeMediator(uiController);
        // Init the game with normal weather mediatior
        weatherMediator = new NormalWeatherMediator(this, uiController);

        runways.add(0, new ChopperRunway("runway_1", timeMediator));
        runways.add(1, new PlaneRunway("runway_2", timeMediator));
        runways.add(2, new PlaneRunway("runway_3", timeMediator));
        runways.add(3, new PlaneRunway("runway_4", timeMediator));
        runways.add(4, new ChopperRunway("runway_5", timeMediator));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public AbstractTimeMediator getMediator() {
        return timeMediator;
    }

    public Runway getRunway() {
        return runways.get(index);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("FXGL Control Tower");
        settings.setVersion("1.0");
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("time", 120);

        // Bounds for pop of animals
        vars.put("start", 0);
        vars.put("end", 2);

        vars.put("score", 0);
        vars.put("waiting", 0);
        vars.put("crashed", 0);

        for (Runway runway : runways) {
            vars.put(runway.toString() + "_open", false);
            vars.put(runway.toString() + "_places", 0.0);
        }

        vars.put("playerNotif", "Alert:");

        vars.put("day", true);
    }

    @Override
    protected void initUI() {
        getStateMachine().getPlayState().addStateListener(uiController);

        UI ui = getAssetLoader().loadUI("tower_control_ui.fxml", uiController);

        // Bind label
        uiController.getLabelScore().textProperty().bind(getip("score").asString("Score: [%d]"));
        uiController.getLabelWaiting().textProperty().bind(getip("waiting").asString("Waiting: [%d]"));
        uiController.getLabelCrashed().textProperty().bind(getip("crashed").asString("Crashed: [%d]"));

        uiController.getNbInPlaneRunwayOne().progressProperty().bind(getdp("runway_2_places"));
        uiController.getNbInPlaneRunwayTwo().progressProperty().bind(getdp("runway_3_places"));
        uiController.getNbInPlaneRunwayThree().progressProperty().bind(getdp("runway_4_places"));
        uiController.getNbInChopperRunwayOne().progressProperty().bind(getdp("runway_1_places"));
        uiController.getNbInChopperRunwayTwo().progressProperty().bind(getdp("runway_5_places"));

        uiController.getPlayerNotif().textProperty().bind(getsp("playerNotif"));

        uiController.getChopperRunwayOne().visibleProperty().bind(getGameState().booleanProperty("runway_1_open"));
        uiController.getNbInChopperRunwayOne().visibleProperty().bind(getGameState().booleanProperty("runway_1_open"));

        uiController.getPlaneRunwayOne().visibleProperty().bind(getGameState().booleanProperty("runway_2_open"));
        uiController.getNbInPlaneRunwayOne().visibleProperty().bind(getGameState().booleanProperty("runway_2_open"));

        uiController.getPlaneRunwayTwo().visibleProperty().bind(getGameState().booleanProperty("runway_3_open"));
        uiController.getNbInPlaneRunwayTwo().visibleProperty().bind(getGameState().booleanProperty("runway_3_open"));

        uiController.getPlaneRunwayThree().visibleProperty().bind(getGameState().booleanProperty("runway_4_open"));
        uiController.getNbInPlaneRunwayThree().visibleProperty().bind(getGameState().booleanProperty("runway_4_open"));

        uiController.getChopperRunwayTwo().visibleProperty().bind(getGameState().booleanProperty("runway_5_open"));
        uiController.getNbInChopperRunwayTwo().visibleProperty().bind(getGameState().booleanProperty("runway_5_open"));

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

        timeMediator.selfAnnounce(runways.get(0));
        timeMediator.selfAnnounce(runways.get(1));
        timeMediator.selfAnnounce(runways.get(2));
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

        // Spawn plane every 2.5 seconds
        run(() -> {
            int index = FXGLMath.random(1, 3);
            Entity e;
            switch (index) {
                case 1:
                    e = getGameWorld().spawn("plane", FXGLMath.random(1220) + 20, 0);
                    weatherMediator.addFlyingModifiers(e);
                    e.getComponent(FlyingObject.class).selfAnnounce();
                    break;
                case 2:
                    e = getGameWorld().spawn("chopper", FXGLMath.random(1180) + 20, 0);
                    weatherMediator.addFlyingModifiers(e);
                    e.getComponent(FlyingObject.class).selfAnnounce();
                    break;
                case 3:
                    e = getGameWorld().spawn("ovni", FXGLMath.random(1225) + 20, 0);
                    weatherMediator.addFlyingModifiers(e);
                    e.getComponent(FlyingObject.class).selfAnnounce();
                    break;
            }
        }, Duration.seconds(2.5));

        // Changing auto of mediator
        run(() -> {
            if (getGameState().getBoolean("day")) {
                timeMediator = new NightTimeMediator(timeMediator);
                timeMediator.selfAnnounce(runways.get(3));
                timeMediator.selfAnnounce(runways.get(4));

                getGameState().setValue("day", false);
                getGameState().setValue("start", 3);
                getGameState().setValue("end", 4);
            } else {
                timeMediator = new DayTimeMediator(timeMediator);
                timeMediator.selfAnnounce(runways.get(0));
                timeMediator.selfAnnounce(runways.get(1));
                timeMediator.selfAnnounce(runways.get(2));

                getGameState().setValue("day", true);
                getGameState().setValue("start", 0);
                getGameState().setValue("end", 2);
            }

            // Notify all colleagues of the timeMediator change
            timeMediator.updateAllCollegues();
            timeMediator.setTimeIcon();

            getGameScene().setBackgroundColor(timeMediator.getBackgroundColor());
        }, Duration.seconds(15));

        // Remove planes on runways auto
        run(() -> {
            for (int i = 0; i < runways.size(); i++)
                if (FXGL.getGameState().getDouble("runway_" + (i + 1) + "_places") > 0.0)
                    FXGL.getGameState().increment("runway_" + (i + 1) + "_places", -0.1);
        }, Duration.seconds(5));

        // Add animals on runways
        int pos[] = new int[]{130, 290, 440, 600, 740};
        run(() -> {
            Entity e;
            int start = getGameState().getInt("start");
            int end = getGameState().getInt("end");
            index = FXGLMath.random(start, end);

            e = getGameWorld().spawn("bird", pos[index], FXGLMath.random(420, 470));
            e.getComponent(Bird.class).selfAnnounce();
        }, Duration.seconds(getRandomInt(5, 15)));

        run(() -> {
            Entity e;
            int start = getGameState().getInt("start");
            int end = getGameState().getInt("end");
            index = FXGLMath.random(start, end);

            e = getGameWorld().spawn("duck", pos[index], FXGLMath.random(420, 470));
            e.getComponent(Duck.class).selfAnnounce();
        }, Duration.seconds(getRandomInt(3, 15)));

        run(() -> {
            Entity e;
            int start = getGameState().getInt("start");
            int end = getGameState().getInt("end");
            index = FXGLMath.random(start, end);

            e = getGameWorld().spawn("pier", pos[index], FXGLMath.random(420, 470));
            e.getComponent(Pier.class).selfAnnounce();
        }, Duration.seconds(30));

        // Game timer
        getMasterTimer().runAtInterval(() -> inc("time", -1), Duration.seconds(1));

        initWeather();
    }

    private void initWeather() {
        weatherRectangle.setWidth(getWidth());
        weatherRectangle.setHeight(getHeight());
        EntityView weatherView = new EntityView();
        weatherView.addNode(weatherRectangle);

        // Initiate once to make background image loading faster
        new NormalWeatherMediator(weatherMediator);
        new FogWeatherMediator(weatherMediator);
        new RainWeatherMediator(weatherMediator);
        new CloudyWeatherMediator(weatherMediator);
        new BrightWeatherMediator(weatherMediator);
        new SnowWeatherMediator(weatherMediator);
        new LightningWeatherMediator(weatherMediator);
        new RainbowWeatherMediator(weatherMediator);
        new HeavyRainWeatherMediator(weatherMediator);
        new BigFogWeatherMediator(weatherMediator);
        new HurricaneWeatherMediator(weatherMediator);

        Entities.builder()
                .viewFromNode(weatherView)
                .with(new IrremovableComponent())
                .buildAndAttach(getGameWorld());

        run(() -> weatherMediator.checkWeatherChange(), Duration.seconds(0.1));
    }

    public void initIncomingWeatherIcon(Circle weatherIcon) {
        getGameScene().addUINodes(weatherIcon);
    }

    public void removeIncomingWeatherIcon(Circle weatherIcon) {
        getGameScene().removeUINodes(weatherIcon);
    }

    public void setWeatherMediator(AbstractWeatherMediator weatherMediator) {
        this.weatherMediator = weatherMediator;
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
}
