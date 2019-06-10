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

    public Circle getTimeIconBackground() {
        return timeIconBackground;
    }

    public Circle getTimeIconForeground() {
        return timeIconForeground;
    }

    public Circle getWeatherIconBackground() {
        return weatherIconBackground;
    }

    public Circle getWeatherIconForeground() {
        return weatherIconForeground;
    }

    public Label getLabelScore() {
        return labelScore;
    }

    public Label getLabelCrashed() {
        return labelCrashed;
    }

    public Label getLabelWaiting() {
        return labelWaiting;
    }

    public Label getPlayerNotif() {
        return playerNotif;
    }

    public ImageView getPlaneRunwayOne() {
        return planeRunwayOne;
    }

    public ImageView getPlaneRunwayTwo() {
        return planeRunwayTwo;
    }

    public ImageView getPlaneRunwayThree() {
        return planeRunwayThree;
    }

    public ImageView getChopperRunwayOne() {
        return chopperRunwayOne;
    }

    public ImageView getChopperRunwayTwo() {
        return chopperRunwayTwo;
    }

    public ProgressBar getNbInPlaneRunwayOne() {
        return nbInPlaneRunwayOne;
    }

    public ProgressBar getNbInPlaneRunwayTwo() {
        return nbInPlaneRunwayTwo;
    }

    public ProgressBar getNbInPlaneRunwayThree() {
        return nbInPlaneRunwayThree;
    }

    public ProgressIndicator getNbInChopperRunwayOne() {
        return nbInChopperRunwayOne;
    }

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
