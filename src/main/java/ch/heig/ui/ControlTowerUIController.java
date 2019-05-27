package ch.heig.ui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.listener.StateListener;
import com.almasb.fxgl.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ControlTowerUIController implements UIController, StateListener {

    @FXML
    private Pane root;

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

    @Override
    public void init() {
        labelCrashed.setFont(FXGL.getUIFactory().newFont(18));
        labelWaiting.setFont(FXGL.getUIFactory().newFont(18));
        nbInAirstripOne.setFont(FXGL.getUIFactory().newFont(18));
        nbInAirstripTwo.setFont(FXGL.getUIFactory().newFont(18));
        nbInAirstripThree.setFont(FXGL.getUIFactory().newFont(18));
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
}
