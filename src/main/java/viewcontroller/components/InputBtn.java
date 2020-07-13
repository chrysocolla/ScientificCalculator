package viewcontroller.components;

import model.calculateType;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import viewcontroller.ScientificCalculator;
import viewcontroller.components.Screen;
import viewcontroller.components.Solve;
import java.util.ArrayList;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class InputBtn {

    private static Integer rowPrefWidth = 350;
    private static Label[][] labelGraph = {
        {shiftLabel(""), shiftLabel(""), shiftLabel(""), shiftLabel("INS"), shiftLabel("OFF")},
        {shiftLabel(""), shiftLabel(""), shiftLabel(""), shiftLabel(""), shiftLabel("")},
        {shiftLabel("S-SUM"), shiftLabel("S-VAR"), shiftLabel(""), shiftLabel(""), shiftLabel("")},
        {shiftLabel("Rnd"), shiftLabel("Ran#"), shiftLabel("π"), shiftLabel("DRG"), shiftLabel("%")}
    };
    private static JFXButton[][] btnGraph = {
        {numBtn("7"), numBtn("8"), numBtn("9"), delBtn(), acBtn()},
        {numBtn("4"), numBtn("5"), numBtn("6"), methodBtn("×"), methodBtn("÷")},
        {numBtn("1"), numBtn("2"), numBtn("3"), methodBtn("+"), methodBtn("-")},
        {numBtn("0"), numBtn("."), expBtn(), ansBtn(), equalsBtn()}
    };

    public static VBox inputBtnView() {
        ArrayList<HBox> hboxArrayList = new ArrayList<HBox>();
        for(Integer i = 0; i < 4; i++) {
            hboxArrayList.add(labelRowBuilder(labelGraph[i]));
            hboxArrayList.add(btnRowBuilder(btnGraph[i]));
        }
        return colBuilder(hboxArrayList.toArray(new HBox[hboxArrayList.size()]));
    }

    private static VBox colBuilder(HBox[] hboxList) {
        VBox col = new VBox();
        col.getChildren().addAll(hboxList);
        return col;
    }

    private static HBox btnRowBuilder(JFXButton[] btnList) {
        HBox row = new HBox(10);
        row.setPrefWidth(rowPrefWidth);
        row.setPadding(new Insets(0, 5, 5, 5));
        row.getChildren().addAll(btnList);
        return row;
    }

    private static HBox labelRowBuilder(Label[] labelList) {
        HBox row = new HBox(10);
        row.setPrefWidth(rowPrefWidth);
        row.setPadding(new Insets(0, 5, 0, 5));
        row.getChildren().addAll(labelList);
        return row;
    }

    private static Label shiftLabel(String label) {
        Label shiftLabel = new Label(label);
        shiftLabel.setTextFill(Color.GOLD);
        shiftLabel.setFont(new Font("verdana", 12));
        shiftLabel.setPrefWidth(rowPrefWidth / 5);
        shiftLabel.setPadding(new Insets(0, 5, 0, 5));
        return shiftLabel;
    }

    private static JFXButton btn(String label, String text, String styleClass) {
        JFXButton btn = new JFXButton(label);
        btn.setButtonType(JFXButton.ButtonType.RAISED);
        btn.getStyleClass().add(styleClass);
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(rowPrefWidth / 5);
        btn.setPrefHeight(25);
        btn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getTypeField().setText("");
                Screen.getResult().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText(text);
        });
        return btn;
    }

    private static JFXButton numBtn(String label) {
        return btn(label, label, "numButton");
    }

    private static JFXButton methodBtn(String label) {
        JFXButton btn = new JFXButton(label);
        btn.setButtonType(JFXButton.ButtonType.RAISED);
        btn.getStyleClass().add("numButton");
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(rowPrefWidth / 5);
        btn.setPrefHeight(25);
        btn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getTypeField().setText(Screen.getResult().getText());
                Screen.getResult().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText(label);
        });
        return btn;
    }

    private static JFXButton expBtn() {
        JFXButton expBtn = new JFXButton("Exp");
        expBtn.setButtonType(JFXButton.ButtonType.RAISED);
        expBtn.getStyleClass().add("numButton");
        expBtn.setTextFill(Color.WHITE);
        expBtn.setPrefWidth(rowPrefWidth / 5);
        expBtn.setPrefHeight(25);
        expBtn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getTypeField().setText("");
                Screen.getResult().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText("π");
            }
        });
        return expBtn;
    }

    private static JFXButton ansBtn() {
        return btn("Ans", "", "numButton");
    }

    private static JFXButton delBtn() {
        JFXButton delBtn = new JFXButton();

        ImageView delIcon = new ImageView(new Image(ScientificCalculator.class.getResource("/images/delete.png").toExternalForm()));
        delIcon.setFitWidth(20);
        delIcon.setFitHeight(20);
        ColorAdjust white = new ColorAdjust();
        white.setBrightness(200.0);

        delIcon.setEffect(white);
        delIcon.setCache(true);
        delIcon.setCacheHint(CacheHint.SPEED);

        delBtn.setGraphic(delIcon);
        delBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        delBtn.setButtonType(JFXButton.ButtonType.RAISED);
        delBtn.getStyleClass().add("delacButton");
        delBtn.setPrefWidth(rowPrefWidth / 5);
        delBtn.setPrefHeight(25);
        delBtn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            String screenText = Screen.getTypeField().getText().toString();
            if (screenText.length() >= 1)
                Screen.getTypeField().setText(screenText.substring(0, screenText.length() - 1));
        });
        return delBtn;
    }

    private static JFXButton acBtn() {
        JFXButton acBtn = new JFXButton("AC");
        acBtn.setButtonType(JFXButton.ButtonType.RAISED);
        acBtn.getStyleClass().add("delacButton");
        acBtn.setTextFill(Color.WHITE);
        acBtn.setPrefWidth(rowPrefWidth / 5);
        acBtn.setPrefHeight(25);
        acBtn.setOnAction((ev) -> {
            Screen.getResult().setText("");
            Screen.getTypeField().setText("");
            calculateType.setCalculated(Boolean.FALSE);
            if (calculateType.getShiftMode()) {
                Platform.exit();
            }
        });
        return acBtn;
    }

    private static JFXButton equalsBtn() {
        JFXButton equalsBtn = new JFXButton("=");
        equalsBtn.setButtonType(JFXButton.ButtonType.RAISED);
        equalsBtn.getStyleClass().add("numButton");
        equalsBtn.setTextFill(Color.WHITE);
        equalsBtn.setPrefWidth(rowPrefWidth / 5);
        equalsBtn.setPrefHeight(25);
        equalsBtn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                calculateType.setCalculated(Boolean.FALSE);
            }
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText("%");
            } else if (!Screen.getTypeField().getText().equals("")) {
                Screen.getToCalculate().add(Screen.getTypeField().getText().trim());
                if (calculateType.getWorkType()) {
                    Solve.combPerm();
                } else {
                    Solve.solveSci();
                }
            }
        });
        return equalsBtn;
    }

}
