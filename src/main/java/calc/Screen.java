package calc;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Idris Opeyemi
 * @author Aristolochic, wangruory@bupt.edu.cn
 */
public class Screen {

    private static JFXTextField typeField;
    private static JFXTextField result;
    private static ObservableList<String> toCalculate = FXCollections.observableArrayList();
    private static ObservableList<String> resultList = FXCollections.observableArrayList();

    public static VBox screenView() {
        VBox calculate = new VBox();
        Text mode = new Text("DEG");

        typeField = new JFXTextField();
        typeField.getStyleClass().add("typefont");
        typeField.setFocusColor(Color.WHITE);
        typeField.setUnFocusColor(Color.WHITE);
        typeField.setEditable(false);

        result = new JFXTextField();
        result.setFocusTraversable(false);
        result.setEditable(false);
        result.getStyleClass().add("resultfont");
        result.setFocusColor(Color.WHITE);
        result.setUnFocusColor(Color.WHITE);
        result.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        calculate.getChildren().addAll(mode, typeField, result);
        calculate.setPadding(new Insets(5, 5, 5, 5));
        calculate.setStyle("-fx-background-color: white");
        return calculate;
    }

    public static JFXTextField getTypeField() {
        return typeField;
    }

    public static JFXTextField getResult() {
        return result;
    }

    public static ObservableList<String> getToCalculate() {
        return toCalculate;
    }

    public static ObservableList<String> getResultList() {
        return resultList;
    }

}
