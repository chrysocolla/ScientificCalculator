package calc.buttons;

import model.calculateType;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Font;
import calc.Screen;
import java.util.ArrayList;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class SciBtn {

    private static Integer rowPrefWidth = 350;
    private static Label[][] labelGraph = {
        {
            bigShiftLabel("d/c"),
            bigShiftLabel(""),
            bigShiftLabel(""),
            bigShiftLabel("x√"),
            bigShiftLabel("10^x"),
            shiftLabel("e^x", Boolean.TRUE), shiftLabel("e", Boolean.FALSE)
        }, {
            shiftLabel("", Boolean.TRUE), shiftLabel("A", Boolean.FALSE),
            shiftLabel("←", Boolean.TRUE), shiftLabel("B", Boolean.FALSE),
            shiftLabel("", Boolean.TRUE), shiftLabel("C", Boolean.FALSE),
            shiftLabel("sin^-1", Boolean.TRUE), shiftLabel("D", Boolean.FALSE),
            shiftLabel("cos^-1", Boolean.TRUE), shiftLabel("E", Boolean.FALSE),
            shiftLabel("tan^-1", Boolean.TRUE), shiftLabel("F", Boolean.FALSE)
        }, {
            bigShiftLabel("STO"),
            bigShiftLabel("←"),
            bigShiftLabel(""),
            shiftLabel("", Boolean.TRUE), shiftLabel("X", Boolean.FALSE),
            shiftLabel(";", Boolean.TRUE), shiftLabel("Y", Boolean.FALSE),
            shiftLabel("M-", Boolean.TRUE), shiftLabel("M", Boolean.FALSE)
        }
    };
    private static JFXButton[][] btnGraph = {
        {
            mixedFrac(),                // mixedFrac
            btn("√", "√(", "√("),       // sqrt
            btn("x^2", "^2", "^2"),     // sqr
            btn("^", "^", "^"),         // caret
            btn("log", "log(", "log("), // log
            btn("ln", "", "")           // in
        },{
            btn("(-)", "", ""),             // hygen
            btn("·,,,", "", ""),            // dot
            btn("hyp", "", ""),             // hyp
            btn("sin", "sin(", "sin-1("),   // sin
            btn("cos", "cos(", "cos-1("),   // cos
            btn("tan", "tan(", "tan-1(")    // tan
        },{
            btn("RCL", "", ""), // rcl
            btn("ENG", "", ""), // eng
            btn("(", "(", "("), // bracketOpen
            btn(")", ")", ")"), // bracketClosed
            btn(",", "", ""),   // coma
            btn("M+", "", "")   // mplus
        }
    };

    public static VBox sciBtnView() {
        ArrayList<HBox> hboxArrayList = new ArrayList<HBox>();
        for(Integer i = 0; i < 3; i++) {
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

    private static Label shiftLabel(String label, Boolean shift) {
        Label shiftLabel = new Label(label);
        shiftLabel.setTextFill(shift ? Color.GOLD : Color.VIOLET);
        shiftLabel.setFont(new Font("verdana", 12));
        shiftLabel.setPrefWidth(rowPrefWidth / 12);
        shiftLabel.setPadding(shift ? new Insets(0, 3, 0, 0) : new Insets(0, 0, 0, 3));
        return shiftLabel;
    }

    private static Label bigShiftLabel(String label) {
        Label bigShiftLabel = shiftLabel(label, Boolean.TRUE);
        bigShiftLabel.setPrefWidth(rowPrefWidth / 6);
        bigShiftLabel.setPadding(new Insets(0, 3, 0, 3));
        return bigShiftLabel;
    }

// VBox sciBox = new VBox();
// sciBox.getChildren().addAll(
//     sciShiftRow2(),
//     new sciBtn().row2(),
//     sciShiftRow3(),
//     new sciBtn().row3(),
//     sciShiftRow4(),
//     new sciBtn().row4()
// );
// return sciBox;

// TODO Redo this
// region sci_buttons
    public static HBox leftRow() {
        HBox row = new HBox(10);
        row.setPrefWidth(rowPrefWidth / 3);
        row.setAlignment(Pos.BASELINE_LEFT);
        row.getChildren().addAll(minusPower(), comb());
        return row;
    }

    public static HBox rightRow() {
        HBox row = new HBox(10);
        row.setPrefWidth(rowPrefWidth / 3);
        row.setAlignment(Pos.BASELINE_RIGHT);
        row.getChildren().addAll(pol(), cbrt());
        return row;
    }
    // TODO 这个迟早要删
    // private HBox sciShiftRow2() {

    //     Label improperFract = new Label("d/c");
    //     improperFract.setTextFill(Color.DARKGOLDENROD);
    //     improperFract.setPadding(new Insets(0, 95, 0, 12));

    //     Label root = new Label("x√");
    //     root.setTextFill(Color.DARKGOLDENROD);
    //     root.setPadding(new Insets(0, 15, 0, 40));

    //     Label tenExp = new Label("10^x");
    //     tenExp.setTextFill(Color.DARKGOLDENROD);
    //     tenExp.setPadding(new Insets(0, 15, 0, 15));

    //     Label eExp = new Label("e^x");
    //     eExp.setTextFill(Color.DARKGOLDENROD);
    //     eExp.setPadding(new Insets(0, 15, 0, 20));
    //     row1.getChildren().addAll(improperFract, root, tenExp, eExp);

    //     HBox row1 = new HBox();
    //     row1.setPrefWidth(panelBox.getPrefWidth());
    //     return row1;
    // }

    // private HBox sciShiftRow3() {
    //     HBox row1 = new HBox();
    //     row1.setPrefWidth(panelBox.getPrefWidth());

    //     Label alphaA = new Label("A");
    //     alphaA.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaA.setPadding(new Insets(0, 0, 0, 35));

    //     Label alphaB = new Label("B");
    //     alphaB.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaB.setPadding(new Insets(0, 0, 0, 45));

    //     Label alphaC = new Label("C");
    //     alphaC.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaC.setPadding(new Insets(0, 0, 0, 45));

    //     Label sinInv = new Label("sin-1");
    //     sinInv.setTextFill(Color.DARKGOLDENROD);
    //     sinInv.setPadding(new Insets(0, 0, 0, 10));

    //     Label alphaD = new Label("D");
    //     alphaD.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaD.setPadding(new Insets(0, 0, 0, 7));

    //     Label cosInv = new Label("cos-1");
    //     cosInv.setTextFill(Color.DARKGOLDENROD);
    //     cosInv.setPadding(new Insets(0, 0, 0, 8));

    //     Label alphaE = new Label("E");
    //     alphaE.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaE.setPadding(new Insets(0, 0, 0, 7));

    //     Label tanInv = new Label("tan-1");
    //     tanInv.setTextFill(Color.DARKGOLDENROD);
    //     tanInv.setPadding(new Insets(0, 0, 0, 8));

    //     Label alphaF = new Label("F");
    //     alphaF.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaF.setPadding(new Insets(0, 0, 0, 7));
    //     row1.getChildren().addAll(alphaA, alphaB, alphaC, sinInv, alphaD, cosInv, alphaE, tanInv, alphaF);
    //     return row1;
    // }

    // private HBox sciShiftRow4() {
    //     HBox row1 = new HBox();
    //     row1.setPrefWidth(panelBox.getPrefWidth());

    //     Label sto = new Label("STO");
    //     sto.setTextFill(Color.DARKGOLDENROD);
    //     sto.setPadding(new Insets(0, 0, 0, 10));

    //     Label alphaX = new Label("X");
    //     alphaX.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaX.setPadding(new Insets(0, 0, 0, (panelBox.getPrefWidth() / 2) - 13));

    //     Label colon = new Label(":");
    //     colon.setTextFill(Color.DARKGOLDENROD);
    //     colon.setPadding(new Insets(0, 0, 0, 8));

    //     Label alphaY = new Label("Y");
    //     alphaY.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaY.setPadding(new Insets(0, 0, 0, 30));

    //     Label alphaM = new Label("M");
    //     alphaM.setTextFill(Color.MEDIUMVIOLETRED);
    //     alphaM.setPadding(new Insets(0, 0, 0, 40));
    //     row1.getChildren().addAll(sto, alphaX, colon, alphaY, alphaM);
    //     return row1;
    // }

    private static JFXButton minusPower() {
        JFXButton btn = btn("x^-1", "^-1", "");
        btn.setPrefWidth((rowPrefWidth / 3) / 3);
        return btn;
    }

    private static JFXButton comb() {
        calculateType.setWorkType(Boolean.TRUE);
        JFXButton btn = btn("nCr", "C", "P");
        btn.setPrefWidth((rowPrefWidth / 3) / 3);
        return btn;
    }

    private static JFXButton cbrt() {
        JFXButton btn = btn("x^3", "^3", "^3");
        btn.setPrefWidth((rowPrefWidth / 3) / 3);
        return btn;
    }

    private static JFXButton pol() {
        JFXButton btn = btn("pol(", "", "∛");
        btn.setPrefWidth((rowPrefWidth / 3) / 3);
        return btn;
    }

    // 下方为按钮组

    private static JFXButton mixedFrac() {
        Text biga = new Text("a");
        Text small = new Text(" b/c");
        small.setFill(Color.WHITE);
        biga.setFill(Color.WHITE);
        TextFlow fract = new TextFlow(biga, small);

        JFXButton mixedFrac = new JFXButton();
        mixedFrac.setButtonType(JFXButton.ButtonType.RAISED);
        mixedFrac.getStyleClass().add("sciButton");
        mixedFrac.setGraphic(fract);
        mixedFrac.setTextFill(Color.WHITE);
        mixedFrac.setPrefWidth(rowPrefWidth / 6);
        mixedFrac.setPrefHeight(20);
        mixedFrac.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
        });
        return mixedFrac;
    }

    private static JFXButton btn(String label, String text, String shiftText) {
        JFXButton btn = new JFXButton(label);
        btn.setButtonType(JFXButton.ButtonType.RAISED);
        btn.getStyleClass().add("sciButton");
        btn.setTextFill(Color.WHITE);
        btn.setPrefWidth(rowPrefWidth / 6);
        btn.setPrefHeight(20);
        btn.setOnAction((ev) -> {
            if (calculateType.getCalculated()) {
                Screen.getResult().setText("");
                Screen.getTypeField().setText("");
                calculateType.setCalculated(Boolean.FALSE);
            }
            if (calculateType.getShiftMode()) {
                Screen.getTypeField().appendText(shiftText);
            } else {
                Screen.getTypeField().appendText(text);
            }
            calculateType.setWorkType(Boolean.FALSE);
        });
        return btn;
    }
}
