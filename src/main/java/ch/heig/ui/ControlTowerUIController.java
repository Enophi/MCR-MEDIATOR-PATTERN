package ch.heig.ui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.listener.StateListener;
import com.almasb.fxgl.ui.FontType;
import com.almasb.fxgl.ui.UIController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Control tower ui controller.
 */
public class ControlTowerUIController implements UIController, StateListener, Initializable {
    private Image planeRunway;
    private Image chopperRunway;

    @FXML
    private Pane root;

    @FXML
    private Circle timeIconBackground;

    @FXML
    private Circle timeIconForeground;

    @FXML
    private Circle weatherIconBackground;

    @FXML
    private Circle weatherIconForeground;

    @FXML
    private VBox playerBox;

    @FXML
    private Label labelScore;

    @FXML
    private Label labelCrashed;

    @FXML
    private Label labelWaiting;

    @FXML
    private Label playerNotif;

    @FXML
    private ImageView planeRunwayOne;

    @FXML
    private ImageView planeRunwayTwo;

    @FXML
    private ImageView planeRunwayThree;

    @FXML
    private ImageView chopperRunwayOne;

    @FXML
    private ImageView chopperRunwayTwo;

    @FXML
    private ProgressBar nbInPlaneRunwayOne;

    @FXML
    private ProgressBar nbInPlaneRunwayTwo;

    @FXML
    private ProgressBar nbInPlaneRunwayThree;

    @FXML
    private ProgressIndicator nbInChopperRunwayOne;

    @FXML
    private ProgressIndicator nbInChopperRunwayTwo;

    @Override
    public void init() {
        playerBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: AQUA;");

        labelScore.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        labelScore.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD");
        labelScore.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t.length() > 7) {
                    int oldValue = Integer.valueOf(t.substring(8, t.length() - 1));
                    int newValue = Integer.valueOf(t1.substring(8, t1.length() - 1));
                    if (newValue > oldValue)
                        labelScore.setStyle("-fx-text-fill: GREEN; -fx-font-weight: BOLD");
                    else {
                        labelScore.setStyle("-fx-text-fill: RED; -fx-font-weight: BOLD");
                    }
                }

            }
        });
        labelCrashed.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        labelCrashed.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD");
        labelWaiting.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        labelWaiting.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD");

        playerNotif.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 36));
    }

    @Override
    public void onUpdate(double tpf) {
    }

    /**
     * Gets time icon background.
     *
     * @return the time icon background
     */
    public Circle getTimeIconBackground() {
        return timeIconBackground;
    }

    /**
     * Gets time icon foreground.
     *
     * @return the time icon foreground
     */
    public Circle getTimeIconForeground() {
        return timeIconForeground;
    }

    /**
     * Gets weather icon background.
     *
     * @return the weather icon background
     */
    public Circle getWeatherIconBackground() {
        return weatherIconBackground;
    }

    /**
     * Gets weather icon foreground.
     *
     * @return the weather icon foreground
     */
    public Circle getWeatherIconForeground() {
        return weatherIconForeground;
    }

    /**
     * Gets label score.
     *
     * @return the label score
     */
    public Label getLabelScore() {
        return labelScore;
    }

    /**
     * Gets label crashed.
     *
     * @return the label crashed
     */
    public Label getLabelCrashed() {
        return labelCrashed;
    }

    /**
     * Gets label waiting.
     *
     * @return the label waiting
     */
    public Label getLabelWaiting() {
        return labelWaiting;
    }

    /**
     * Gets player notif.
     *
     * @return the player notif
     */
    public Label getPlayerNotif() {
        return playerNotif;
    }

    /**
     * Gets plane runway one.
     *
     * @return the plane runway one
     */
    public ImageView getPlaneRunwayOne() {
        return planeRunwayOne;
    }

    /**
     * Gets plane runway two.
     *
     * @return the plane runway two
     */
    public ImageView getPlaneRunwayTwo() {
        return planeRunwayTwo;
    }

    /**
     * Gets plane runway three.
     *
     * @return the plane runway three
     */
    public ImageView getPlaneRunwayThree() {
        return planeRunwayThree;
    }

    /**
     * Gets chopper runway one.
     *
     * @return the chopper runway one
     */
    public ImageView getChopperRunwayOne() {
        return chopperRunwayOne;
    }

    /**
     * Gets chopper runway two.
     *
     * @return the chopper runway two
     */
    public ImageView getChopperRunwayTwo() {
        return chopperRunwayTwo;
    }

    /**
     * Gets nb in plane runway one.
     *
     * @return the nb in plane runway one
     */
    public ProgressBar getNbInPlaneRunwayOne() {
        return nbInPlaneRunwayOne;
    }

    /**
     * Gets nb in plane runway two.
     *
     * @return the nb in plane runway two
     */
    public ProgressBar getNbInPlaneRunwayTwo() {
        return nbInPlaneRunwayTwo;
    }

    /**
     * Gets nb in plane runway three.
     *
     * @return the nb in plane runway three
     */
    public ProgressBar getNbInPlaneRunwayThree() {
        return nbInPlaneRunwayThree;
    }

    /**
     * Gets nb in chopper runway one.
     *
     * @return the nb in chopper runway one
     */
    public ProgressIndicator getNbInChopperRunwayOne() {
        return nbInChopperRunwayOne;
    }

    /**
     * Gets nb in chopper runway two.
     *
     * @return the nb in chopper runway two
     */
    public ProgressIndicator getNbInChopperRunwayTwo() {
        return nbInChopperRunwayTwo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        planeRunway = new Image("/assets/textures/plane_runway.png", true);
        chopperRunway = new Image("/assets/textures/chopper_runway.png", true);

        planeRunwayOne.setImage(planeRunway);
        planeRunwayTwo.setImage(planeRunway);
        planeRunwayThree.setImage(planeRunway);

        chopperRunwayOne.setImage(chopperRunway);
        chopperRunwayTwo.setImage(chopperRunway);
    }
}
