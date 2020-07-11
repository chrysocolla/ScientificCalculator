package calc.buttons;

import model.calculateType;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import calc.ScientificCalculator;
import calc.Screen;
import calc.Solve;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class NumberButtons {

    HBox row;

    public NumberButtons() {
        row = new HBox(10);
        row.setPrefWidth(350);
        row.setPadding(new Insets(0, 5, 5, 5));
    }


    public HBox row4() {
        HBox row4 = new HBox(10);
        row4.setPrefWidth(350);
        row4.setPadding(new Insets(0, 5, 5, 5));
        row4.getChildren().addAll(numBtn("0"), numBtn("."), dummyBtn("Exp"), dummyBtn("Ans"), equalsBtn());
        return row4;
    }

    public HBox row3() {
        HBox row3 = new HBox(10);
        row3.setPrefWidth(350);
        row3.setPadding(new Insets(0, 5, 5, 5));
        row3.getChildren().addAll(numBtn("1"), numBtn("2"), numBtn("3"), methodBtn("+"), methodBtn("-"));
        return row3;
    }

    public HBox row2() {
        HBox row2 = new HBox(10);
        row2.setPrefWidth(350);
        row2.setPadding(new Insets(10, 5, 5, 5));
        row2.getChildren().addAll(numBtn("4"), numBtn("5"), numBtn("6"), methodBtn("×"), methodBtn("÷"));
        return row2;
    }

    public HBox row1() {
        HBox row1 = new HBox(10);
        row1.setPrefWidth(350);
        row1.setPadding(new Insets(0, 5, 10, 5));
        row1.getChildren().addAll(numBtn("7"), numBtn("8"), numBtn("9"), delBtn(), acBtn());
        return row1;
    }

    // TODO
    // private HBox rowBuilder() {
    //     HBox row = new HBox(10);
    // }
    // TODO
    // private HBox rowBuilder() {
    //     HBox row = new HBox(10);
    // }

    private JFXButton numBtn(String label) {
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

    private JFXButton methodBtn(String label) {
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

    private JFXButton dummyBtn(String label) {
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

    private JFXButton delBtn() {
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
        delBtn.setPrefWidth(row.getPrefWidth() / 5);
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

    private JFXButton acBtn() {
        JFXButton acBtn = new JFXButton("AC");
        setupBaseAttribute(acBtn, "delacButton");
        acBtn.setOnAction((ev) -> {
            Screen.getResult().setText("");
            Screen.getTypeField().setText("");
            calculateType.setCalculated(Boolean.FALSE);
        });
        return acBtn;
    }

    private JFXButton equalsBtn() {
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

    private void setupBaseAttribute(JFXButton btn, String styleClass) {
        btn.setButtonType(JFXButton.ButtonType.RAISED);
        btn.getStyleClass().add(styleClass);
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(row.getPrefWidth() / 5);
        btn.setPrefHeight(25);
    }

}
