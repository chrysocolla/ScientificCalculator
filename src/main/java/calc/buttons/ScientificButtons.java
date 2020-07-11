package calc.buttons;

import model.calculateType;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import calc.Screen;
import calc.Solve;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class ScientificButtons {

    HBox row;

    public ScientificButtons() {
        row = new HBox(10);
        row.setPrefWidth(350);
    }

    public HBox row2() {
        HBox row2 = new HBox(10);
        row2.setPrefWidth(350);
        row2.setPadding(new Insets(0, 5, 5, 5));
        row2.getChildren().addAll(mixedFraction(), sqrt(), sqr(), caret(), log(), in());
        return row2;
    }

    public HBox row3() {
        HBox row3 = new HBox(10);
        row3.setPrefWidth(350);
        row3.setPadding(new Insets(0, 5, 5, 5));
        row3.getChildren().addAll(hypen(), dot(), hyp(), sin(), cos(), tan());
        return row3;
    }

    public HBox row4() {
        HBox row4 = new HBox(10);
        row4.setPrefWidth(350);
        row4.setPadding(new Insets(0, 5, 5, 5));
        row4.getChildren().addAll(rcl(), eng(), bracketOpen(), bracketClosed(), coma(), mplus());
        return row4;
    }

// region sci_buttons
    public HBox halfRow1() {
        HBox row1 = new HBox(10);
        row1.setPrefWidth(350 / 3);
        row1.getChildren().addAll(minusPower(), comb());
        return row1;

    }

    public HBox half2Row() {
        HBox row1 = new HBox(10);
        row1.setPrefWidth(350 / 3);
        row1.setAlignment(Pos.BASELINE_RIGHT);
        row1.getChildren().addAll(pol(), cbrt());
        return row1;
    }

    private JFXButton minusPower() {
        Solve solve = new Solve();
        JFXButton minusPower = new JFXButton("");
        minusPower.setButtonType(JFXButton.ButtonType.RAISED);
        minusPower.getStyleClass().add("sciButton");
        minusPower.setTextFill(Color.WHITE);
        minusPower.setPrefWidth((row.getPrefWidth() / 3) / 3);
        minusPower.setPrefHeight(20);
        minusPower.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            solve.solveFactorial();
            Screen.getTypeField().appendText("!");
            Screen.getToCalculate().add(Screen.getTypeField().getText());
        });
        return minusPower;
    }

    private JFXButton comb() {
        JFXButton comb = new JFXButton("nCr");
        comb.setButtonType(JFXButton.ButtonType.RAISED);
        comb.getStyleClass().add("sciButton");
        comb.setTextFill(Color.WHITE);
        comb.setPrefWidth((row.getPrefWidth() / 3) / 3);
        comb.setPrefHeight(20);
        comb.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText("P");
                calculateType.setType("Permutation");
            } else {
                Screen.getTypeField().appendText("C");
                calculateType.setType("Combination");
            }
        });
        return comb;
    }

    private JFXButton cbrt() {
        JFXButton cbrt = new JFXButton("x^3");
        cbrt.getStyleClass().add("sciButton");
        cbrt.setTextFill(Color.WHITE);
        cbrt.setPrefWidth((row.getPrefWidth() / 3) / 3);
        cbrt.setPrefHeight(20);
        cbrt.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText("^3");
            calculateType.setType("Normal");
        });
        return cbrt;
    }

    private JFXButton pol() {
        JFXButton pol = new JFXButton("pol(");
        pol.setButtonType(JFXButton.ButtonType.RAISED);
        pol.getStyleClass().add("sciButton");
        pol.setTextFill(Color.WHITE);
        pol.setPrefWidth((row.getPrefWidth() / 3) / 3);
        pol.setPrefHeight(20);
        pol.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText("∛");
                calculateType.setType("Normal");
            } else {
                // Screen.getTypeField().appendText("C");
                // calculateType.setType("Combination");
            }
        });
        return pol;
    }

    private JFXButton mixedFraction() {
        Text biga = new Text("a");
        Text small = new Text(" b/c");
        TextFlow fract = new TextFlow(biga, small);
        small.setFill(Color.WHITE);
        biga.setFill(Color.WHITE);

        JFXButton mixedFrac = new JFXButton();
        mixedFrac.setButtonType(JFXButton.ButtonType.RAISED);
        mixedFrac.getStyleClass().add("sciButton");
        mixedFrac.setGraphic(fract);
        mixedFrac.setTextFill(Color.WHITE);
        mixedFrac.setPrefWidth(row.getPrefWidth() / 6);
        mixedFrac.setPrefHeight(20);
        mixedFrac.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            // typeField.appendText("7");
        });
        return mixedFrac;
    }

    private JFXButton sqrt() {
        JFXButton sqroot = new JFXButton("√");
        sqroot.setButtonType(JFXButton.ButtonType.RAISED);
        sqroot.getStyleClass().add("sciButton");
        sqroot.setTextFill(Color.WHITE);
        sqroot.setPrefWidth(row.getPrefWidth() / 6);
        sqroot.setPrefHeight(20);
        sqroot.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            calculateType.setType("Normal");
            Screen.getTypeField().appendText("√(");
        });
        return sqroot;
    }

    private JFXButton sqr() {
        JFXButton sqr = new JFXButton("x^2");
        sqr.setButtonType(JFXButton.ButtonType.RAISED);
        sqr.getStyleClass().add("sciButton");
        sqr.setTextFill(Color.WHITE);
        sqr.setPrefWidth(row.getPrefWidth() / 6);
        sqr.setPrefHeight(20);
        sqr.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            calculateType.setType("Normal");
            Screen.getTypeField().appendText("^2");
        });
        return sqr;
    }

    private JFXButton caret() {
        JFXButton caret = new JFXButton("˄");
        caret.getStyleClass().add("sciButton");
        caret.setTextFill(Color.WHITE);
        caret.setPrefWidth(row.getPrefWidth() / 6);
        caret.setPrefHeight(20);
        caret.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            calculateType.setType("Normal");
            Screen.getTypeField().appendText("^");
        });
        return caret;
    }

    private JFXButton in() {
        JFXButton in = new JFXButton("ln");
        in.setButtonType(JFXButton.ButtonType.RAISED);
        in.getStyleClass().add("sciButton");
        in.setTextFill(Color.WHITE);
        in.setPrefWidth(row.getPrefWidth() / 6);
        in.setPrefHeight(20);
        in.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return in;
    }

    private JFXButton log() {
        JFXButton log = new JFXButton("log");
        log.setButtonType(JFXButton.ButtonType.RAISED);
        log.getStyleClass().add("sciButton");
        log.setTextFill(Color.WHITE);
        log.setPrefWidth(row.getPrefWidth() / 6);
        log.setPrefHeight(20);
        log.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            calculateType.setType("Scientific");
            Screen.getTypeField().appendText("log(");
        });
        return log;
    }

    private JFXButton rcl() {
        JFXButton rcl = new JFXButton("RCL");
        rcl.setButtonType(JFXButton.ButtonType.RAISED);
        rcl.getStyleClass().add("sciButton");
        rcl.setTextFill(Color.WHITE);
        rcl.setPrefWidth(row.getPrefWidth() / 6);
        rcl.setPrefHeight(20);
        rcl.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return rcl;
    }

    private JFXButton eng() {
        JFXButton eng = new JFXButton("ENG");
        eng.setButtonType(JFXButton.ButtonType.RAISED);
        eng.getStyleClass().add("sciButton");
        eng.setTextFill(Color.WHITE);
        eng.setPrefWidth(row.getPrefWidth() / 6);
        eng.setPrefHeight(20);
        eng.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return eng;
    }

    private JFXButton bracketOpen() {
        JFXButton bracketOpen = new JFXButton("(");
        bracketOpen.setButtonType(JFXButton.ButtonType.RAISED);
        bracketOpen.getStyleClass().add("sciButton");
        bracketOpen.setTextFill(Color.WHITE);
        bracketOpen.setPrefWidth(row.getPrefWidth() / 6);
        bracketOpen.setPrefHeight(20);
        bracketOpen.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }           
            Screen.getTypeField().appendText("(");
        });
        return bracketOpen;
    }

    private JFXButton bracketClosed() {
        JFXButton bracketClosed = new JFXButton(")");
        bracketClosed.setButtonType(JFXButton.ButtonType.RAISED);
        bracketClosed.getStyleClass().add("sciButton");
        bracketClosed.setTextFill(Color.WHITE);
        bracketClosed.setPrefWidth(row.getPrefWidth() / 6);
        bracketClosed.setPrefHeight(20);
        bracketClosed.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            Screen.getTypeField().appendText(")");
        });
        return bracketClosed;
    }

    private JFXButton coma() {
        JFXButton coma = new JFXButton("٬");
        coma.setButtonType(JFXButton.ButtonType.RAISED);
        coma.getStyleClass().add("sciButton");
        coma.setTextFill(Color.WHITE);
        coma.setPrefWidth(row.getPrefWidth() / 6);
        coma.setPrefHeight(20);
        coma.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return coma;
    }

    private JFXButton mplus() {
        JFXButton mplus = new JFXButton("M+");
        mplus.setButtonType(JFXButton.ButtonType.RAISED);
        mplus.getStyleClass().add("sciButton");
        mplus.setTextFill(Color.WHITE);
        mplus.setPrefWidth(row.getPrefWidth() / 6);
        mplus.setPrefHeight(20);
        mplus.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return mplus;
    }

    private JFXButton hypen() {
        JFXButton hypen = new JFXButton("(-)");
        hypen.setButtonType(JFXButton.ButtonType.RAISED);
        hypen.getStyleClass().add("sciButton");
        hypen.setTextFill(Color.WHITE);
        hypen.setPrefWidth(row.getPrefWidth() / 6);
        hypen.setPrefHeight(20);
        hypen.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return hypen;
    }

    private JFXButton dot() {
        JFXButton dot = new JFXButton("۰٬٬٬");
        dot.setButtonType(JFXButton.ButtonType.RAISED);
        dot.getStyleClass().add("sciButton");
        dot.setTextFill(Color.WHITE);
        dot.setPrefWidth(row.getPrefWidth() / 6);
        dot.setPrefHeight(20);
        dot.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return dot;
    }

    private JFXButton hyp() {
        JFXButton hyp = new JFXButton("hyp");
        hyp.setButtonType(JFXButton.ButtonType.RAISED);
        hyp.getStyleClass().add("sciButton");
        hyp.setTextFill(Color.WHITE);
        hyp.setPrefWidth(row.getPrefWidth() / 6);
        hyp.setPrefHeight(20);
        hyp.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return hyp;
    }

    private JFXButton sin() {
        JFXButton sin = new JFXButton("sin");
        sin.setButtonType(JFXButton.ButtonType.RAISED);
        sin.getStyleClass().add("sciButton");
        sin.setTextFill(Color.WHITE);
        sin.setPrefWidth(row.getPrefWidth() / 6);
        sin.setPrefHeight(20);
        sin.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            calculateType.setType("Scientific");
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText("sin-1(");
            } else {
                Screen.getTypeField().appendText("sin(");
            }
        });
        return sin;
    }

    private JFXButton tan() {
        JFXButton tan = new JFXButton("tan");
        tan.setButtonType(JFXButton.ButtonType.RAISED);
        tan.getStyleClass().add("sciButton");
        tan.setTextFill(Color.WHITE);
        tan.setPrefWidth(row.getPrefWidth() / 6);
        tan.setPrefHeight(20);
        tan.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            calculateType.setType("Scientific");
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText("sin-1(");
            } else {
                Screen.getTypeField().appendText("sin(");
            }
        });
        return tan;
    }

    private JFXButton cos() {
        JFXButton cos = new JFXButton("cos");
        cos.setButtonType(JFXButton.ButtonType.RAISED);
        cos.getStyleClass().add("sciButton");
        cos.setTextFill(Color.WHITE);
        cos.setPrefWidth(row.getPrefWidth() / 6);
        cos.setPrefHeight(20);
        cos.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            calculateType.setType("Scientific");
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText("cos-1(");
            } else {
                Screen.getTypeField().appendText("cos(");
            }
        });
        return cos;
    }
// endregion
}
