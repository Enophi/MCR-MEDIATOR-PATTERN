package ch.heig.ui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.listener.StateListener;
import com.almasb.fxgl.ui.FontType;
import com.almasb.fxgl.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

public class ControlTowerUIController implements UIController, StateListener {

    @FXML
    private Pane root;

    @FXML
    private VBox playerBox;

    @FXML
    private Label labelCrashed;

    @FXML
    private Label labelWaiting;

    @FXML
    private Label nbInAirstripOne;

    @FXML
    private Label nbInAirstripTwo;

    @FXML
    private Label nbInAirstripThree;

    @FXML
    private Label nbInAirstripFour;

    @FXML
    private Polygon chopper1;

    @FXML
    private Label nbInAirstripFive;

    @FXML
    private Polygon chopper2;

    @FXML
    private Label playerNotif;

    @Override
    public void init() {
        playerBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: AQUA;");

        labelCrashed.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        labelCrashed.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD");
        labelWaiting.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        labelWaiting.setStyle("-fx-text-fill: WHITE; -fx-font-weight: BOLD");

        nbInAirstripOne.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        nbInAirstripTwo.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        nbInAirstripThree.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        nbInAirstripFour.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));
        nbInAirstripFive.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 18));

        playerNotif.setFont(FXGL.getUIFactory().newFont(FontType.GAME, 36));
    }

    @Override
    public void onUpdate(double tpf) {
    }

    public Label getLabelCrashed() {
        return labelCrashed;
    }

    public Label getLabelWaiting() {
        return labelWaiting;
    }

    public Label getNbInAirstripOne() {
        return nbInAirstripOne;
    }

    public Label getNbInAirstripTwo() {
        return nbInAirstripTwo;
    }

    public Label getNbInAirstripThree() {
        return nbInAirstripThree;
    }

    public Label getNbInAirstripFour() {
        return nbInAirstripFour;
    }

    public Polygon getChopper1() {
        return chopper1;
    }

    public Label getNbInAirstripFive() {
        return nbInAirstripFive;
    }

    public Polygon getChopper2() {
        return chopper2;
    }

    public Label getPlayerNotif() {
        return playerNotif;
    }
}
