package viewcontroller;

import viewcontroller.components.SciBtn;
import viewcontroller.components.InputBtn;
import viewcontroller.components.Screen;
import model.calculateType;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class ViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    VBox panelBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panelBox = new VBox(5);
        panelBox.setPrefWidth(350);
        panelBox.setPadding(new Insets(20));
        panelBox.setOnKeyPressed((act) -> {
            if (act.getCode() == KeyCode.UP) {
                getNext();
            } else if (act.getCode() == KeyCode.DOWN) {
                getPrev();
            } else if (act.getCode() == KeyCode.RIGHT) {
                Screen.getTypeField().setEditable(true);
                Screen.getTypeField().requestFocus();
            } else if (act.getCode() == KeyCode.LEFT) {
                Screen.getTypeField().setEditable(true);
                Screen.getTypeField().selectEnd();
                Screen.getTypeField().setFocusTraversable(true);
            }
        });

        CubicCurve curve = new CubicCurve(-175, 0, 0, 25, 50, 17.5, 175, 0);
        curve.setFill(Color.TRANSPARENT);

        VBox calculate = Screen.screenView();

        HBox topBox = new HBox();
        topBox.setPadding(new Insets(20, 5, 0, 5));
        topBox.setPrefWidth(panelBox.getPrefWidth());
        topBox.getChildren().addAll(leftTopBox(), replay(), rightTopBox());

        VBox numBox = InputBtn.inputBtnView();

        Region reg = new Region();
        VBox.setVgrow(reg, Priority.ALWAYS);

        VBox sciBox = SciBtn.sciBtnView();

        panelBox.getChildren().add(curve);
        panelBox.getChildren().addAll(calculate, topBox, sciBox, reg, numBox);

        AnchorPane.setLeftAnchor(panelBox, 1.0);
        AnchorPane.setRightAnchor(panelBox, 1.0);
        AnchorPane.setTopAnchor(panelBox, 1.0);
        AnchorPane.setBottomAnchor(panelBox, 1.0);
        AnchorPane.setLeftAnchor(curve, 18.952728271484375);
        AnchorPane.setRightAnchor(curve, 18.952728271484375);
        AnchorPane.setBottomAnchor(curve, -1.0);
        anchorPane.getChildren().add(panelBox);
    }

    private BorderPane replay() {
        BorderPane replay = new BorderPane();
        replay.setPrefHeight(0);
        replay.setPrefWidth(panelBox.getPrefWidth() / 3);

        JFXButton left = new JFXButton();

        left.setButtonType(JFXButton.ButtonType.RAISED);
        Text leftIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_LEFT, "20px");
        leftIcon.setFill(Color.DARKGREY);
        left.setGraphic(leftIcon);

        JFXButton right = new JFXButton();
        right.setButtonType(JFXButton.ButtonType.RAISED);
        Text rightIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_RIGHT, "20px");
        rightIcon.setFill(Color.DARKGREY);
        right.setGraphic(rightIcon);

        JFXButton center = new JFXButton("REPLAY");
        center.setTextFill(Color.DARKGREY);
        center.getStyleClass().add("replay");
        center.setPrefWidth(panelBox.getPrefWidth());
        center.setFont(new Font("verdana", 12));
        center.setPadding(new Insets(-5));


        JFXButton top = new JFXButton();
        top.setButtonType(JFXButton.ButtonType.RAISED);
        Text topIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_UP, "20px");
        topIcon.setFill(Color.DARKGREY);
        top.setGraphic(topIcon);
        top.setOnAction((ev) -> {
            getNext();
        });

        JFXButton down = new JFXButton();
        down.setButtonType(JFXButton.ButtonType.RAISED);
        Text downIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CARET_DOWN, "20px");
        downIcon.setFill(Color.DARKGREY);
        down.setGraphic(downIcon);
        down.setOnAction((ev) -> {
            getPrev();
        });

        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setAlignment(down, Pos.CENTER);
        replay.setLeft(left);
        replay.setTop(top);
        replay.setBottom(down);
        replay.setRight(right);
        replay.setCenter(center);
        replay.getStyleClass().add("borderPane");
        return replay;
    }

    private void getPrev() {
        Integer size = Screen.getToCalculate().size();
        if (size == 0) {
            return;
        } else {
            String prevToCal = Screen.getToCalculate().get(Screen.getToCalculate().size() - 1);
            String prevResult = Screen.getResultList().get(Screen.getResultList().size() - 1);
            if (Screen.getToCalculate().contains(Screen.getTypeField().getText())) {
                Integer index = Screen.getToCalculate().indexOf(Screen.getTypeField().getText());
                prevToCal = Screen.getToCalculate().get(index <= 0 ? 0 : index - 1);
                prevResult = Screen.getResultList().get(index <= 0 ? 0 : index - 1);
                Screen.getTypeField().setText(prevToCal);
                Screen.getResult().setText(prevResult);
            } else {
                Screen.getTypeField().setText(prevToCal);
                Screen.getResult().setText(prevResult);
            }
        }
    }

    private void getNext() {
        if (Screen.getToCalculate().contains(Screen.getTypeField().getText())) {
            Integer index = Screen.getToCalculate().indexOf(Screen.getTypeField().getText());
            Integer size = Screen.getToCalculate().size();
            String nextToCal = Screen.getToCalculate().get(index >= size - 1 ? size - 1 : index + 1);
            String nextResult = Screen.getResultList().get(index >= size - 1 ? size - 1 : index + 1);
            Screen.getTypeField().setText(nextToCal);
            Screen.getResult().setText(nextResult);
        }
    }

    private VBox leftTopBox() {
        JFXButton shift = new JFXButton();
        VBox.setMargin(shift, new Insets(0, (panelBox.getPrefWidth() / 3) / 2, 0, 0));
        shift.getStyleClass().add("modeButton");
        shift.getStyleClass().add("modeColor");
        shift.setPrefWidth((panelBox.getPrefWidth() / 3) / 3);
        shift.setOnAction((ev) -> {
            if (shift.getStyleClass().contains("modeColor")) {
                calculateType.setShiftMode(Boolean.TRUE);
                shift.getStyleClass().remove("modeColor");
                shift.getStyleClass().add("shiftModeColor");
            } else if (shift.getStyleClass().contains("shiftModeColor")) {
                calculateType.setShiftMode(Boolean.FALSE);
                shift.getStyleClass().remove("shiftModeColor");
                shift.getStyleClass().add("modeColor");
            }
        });

        JFXButton alpha = new JFXButton();
        VBox.setMargin(alpha, new Insets(-10, 0, 0, (panelBox.getPrefWidth() / 3) / 2 - 5));
        alpha.getStyleClass().add("modeButton");
        alpha.getStyleClass().add("modeColor");
        alpha.setPrefWidth((panelBox.getPrefWidth() / 3) / 3);

        Region reg = new Region();
        VBox.setVgrow(reg, Priority.ALWAYS);

        VBox shiftAlpha = new VBox();
        shiftAlpha.setPrefWidth(panelBox.getPrefWidth() / 3);
        shiftAlpha.getChildren().addAll(
            shiftLeftTopBox(),
            shift, alpha, reg,
            shiftLeftHalfSciRow(),
            SciBtn.leftRow()
        );
        return shiftAlpha;
    }

    private VBox shiftLeftTopBox() {
        Label shift = new Label("SHIFT");
        shift.setFont(new Font("verdana", 13));
        shift.setTextFill(Color.GOLDENROD);
        VBox.setMargin(shift, new Insets(5, 0, 0, 0));

        Label alpha = new Label("ALPHA");
        alpha.setFont(new Font("verdana", 13));
        alpha.setTextFill(Color.MEDIUMVIOLETRED);
        VBox.setMargin(alpha, new Insets(-10, 0, 0, (panelBox.getPrefWidth() / 3) / 2 - 10));

        VBox row = new VBox();
        row.setPadding(Insets.EMPTY);
        row.setPrefWidth(panelBox.getPrefWidth() / 3);
        row.getChildren().addAll(shift, alpha);
        row.setAlignment(Pos.BOTTOM_LEFT);
        return row;
    }

    private HBox shiftLeftHalfSciRow() {
        Label factorial = new Label("x!");
        factorial.setFont(Font.font("verdana", 12));
        factorial.setTextFill(Color.DARKGOLDENROD);
        factorial.setPadding(new Insets(0, (panelBox.getPrefWidth() / 3) / 3 - 10, 0, 15));

        Label permute = new Label("nPr");
        permute.setFont(Font.font("verdana", 12));
        permute.setTextFill(Color.DARKGOLDENROD);
        permute.setPadding(new Insets(0, 0, 0, 0));

        HBox row = new HBox();
        row.setPrefWidth(panelBox.getPrefWidth() / 3);
        row.getChildren().addAll(factorial, permute);
        return row;
    }

    private VBox rightTopBox() {
        JFXButton mode = new JFXButton();
        VBox.setMargin(mode, new Insets(-10, (panelBox.getPrefWidth() / 3) / 2 - 5, 0, 0));
        mode.getStyleClass().add("modeButton");
        mode.getStyleClass().add("modeColor");
        mode.setPrefWidth((panelBox.getPrefWidth() / 3) / 3);

        JFXButton on = new JFXButton();
        VBox.setMargin(on, new Insets(0, 0, 0, (panelBox.getPrefWidth() / 3) / 2));
        on.getStyleClass().add("modeButton");
        on.getStyleClass().add("modeColor");
        on.setPrefWidth((panelBox.getPrefWidth() / 3) / 3);

        Region reg = new Region();
        VBox.setVgrow(reg, Priority.ALWAYS);

        VBox modeOn = new VBox();
        modeOn.setPrefWidth(panelBox.getPrefWidth() / 3);
        modeOn.setAlignment(Pos.TOP_RIGHT);
        modeOn.getChildren().addAll(
            shiftRightTopBox(),
            on, mode, reg,
            shiftRightHalfSciRow(),
            SciBtn.rightRow()
        );
        return modeOn;
    }

    private VBox shiftRightTopBox() {
        Label on = new Label("ON");
        on.setTextFill(Color.GRAY);
        on.setFont(new Font("verdana", 13));
        VBox.setMargin(on, new Insets(5, 0, 0, (panelBox.getPrefWidth() / 3) / 2 + 15));

        Label mode = new Label("MODE");
        mode.setTextFill(Color.GRAY);
        mode.setFont(new Font("verdana", 13));
        VBox.setMargin(mode, new Insets(-10, 0, 0, 10));

        VBox row = new VBox();
        row.setPrefWidth(panelBox.getPrefWidth() / 3);
        row.getChildren().addAll(on, mode);
        return row;
    }

    private HBox shiftRightHalfSciRow() {
        Label rec = new Label("Rec(");
        rec.setFont(Font.font("verdana", 12));
        rec.setTextFill(Color.DARKGOLDENROD);
        rec.setPadding(new Insets(0, (panelBox.getPrefWidth() / 3) / 12, 0, 10));

        Label alpha = new Label(":");
        alpha.setFont(Font.font("verdana", 12));
        alpha.setTextFill(Color.MEDIUMVIOLETRED);
        alpha.setPadding(new Insets(0, (panelBox.getPrefWidth() / 3) / 4, 0, 0));

        Label cuberoot = new Label("∛");
        cuberoot.setFont(Font.font("verdana", 12));
        cuberoot.setTextFill(Color.DARKGOLDENROD);
        cuberoot.setPadding(new Insets(0, 0, 0, 0));

        HBox row = new HBox();
        row.setPrefWidth(panelBox.getPrefWidth() / 3);
        row.getChildren().addAll(rec, alpha, cuberoot);
        return row;
    }

}
