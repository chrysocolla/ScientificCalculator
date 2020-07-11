package calc.buttons;

import model.calculateType;
import com.jfoenix.controls.JFXButton;
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
import calc.ScientificCalculator;
import calc.Screen;
import calc.Solve;
import java.util.ArrayList;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class inputBtn {

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
        {numBtn("0"), numBtn("."), dummyBtn("Exp"), dummyBtn("Ans"), equalsBtn()}
    };

    public static VBox inputBtn() {
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
        row.setPrefWidth(350);
        row.setPadding(new Insets(0, 5, 5, 5));
        row.getChildren().addAll(btnList);
        return row;
    }

    private static HBox labelRowBuilder(Label[] labelList) {
        HBox row = new HBox(10);
        row.setPrefWidth(350);
        row.setPadding(new Insets(0, 5, 0, 5));
        row.getChildren().addAll(labelList);
        return row;
    }

    private static Label shiftLabel(String label) {
        Label shiftLabel = new Label(label);
        shiftLabel.setTextFill(Color.GOLD);
        shiftLabel.setFont(new Font("Arial", 12));
        shiftLabel.setPrefWidth(rowPrefWidth / 5);
        shiftLabel.setPadding(new Insets(0, 2, 0, 2));
        return shiftLabel;
    }

    private static JFXButton numBtn(String label) {
        JFXButton numBtn = new JFXButton(label);
        setupBaseAttribute(numBtn, "numButton");
        numBtn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText(label);
        });
        return numBtn;
    }

    private static JFXButton methodBtn(String label) {
        JFXButton methodBtn = new JFXButton(label);
        setupBaseAttribute(methodBtn, "numButton");
        methodBtn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getTypeField().setText(Screen.getResult().getText());
                Screen.getResult().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText(label);
        });
        return methodBtn;
    }

    private static JFXButton dummyBtn(String label) {
        JFXButton dummyBtn = new JFXButton(label);
        setupBaseAttribute(dummyBtn, "numButton");
        dummyBtn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return dummyBtn;
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
        setupBaseAttribute(acBtn, "delacButton");
        acBtn.setOnAction((ev) -> {
            Screen.getResult().setText("");
            Screen.getTypeField().setText("");
            calculateType.setCalculated(Boolean.FALSE);
        });
        return acBtn;
    }

    private static JFXButton equalsBtn() {
        Solve calculateSolve = new Solve();
        JFXButton equalsBtn = new JFXButton("=");
        setupBaseAttribute(equalsBtn, "numButton");
        equalsBtn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                calculateType.setCalculated(Boolean.FALSE);
            }
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText("%");
            } else if (!Screen.getTypeField().getText().equals("")) {
                Screen.getToCalculate().add(Screen.getTypeField().getText().trim());
                switch (calculateType.getType()) {
                    case "Scientific":
                        calculateSolve.solveScientific();
                        break;
                    case "Normal":
                        calculateSolve.solve();
                        break;
                    case "Combination":
                        calculateSolve.combination();
                        break;
                    case "Permutation":
                        calculateSolve.permutation();
                        break;
                }
            }
        });
        return equalsBtn;
    }

    private static void setupBaseAttribute(JFXButton btn, String styleClass) {
        btn.setButtonType(JFXButton.ButtonType.RAISED);
        btn.getStyleClass().add(styleClass);
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(rowPrefWidth / 5);
        btn.setPrefHeight(25);
    }

}
