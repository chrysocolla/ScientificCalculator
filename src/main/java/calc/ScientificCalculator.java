package calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Idris Opeyemi
 */
public class ScientificCalculator extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(ScientificCalculator.class.getResource("/fonts/digital-7.ttf").toExternalForm(), 10);
        Parent root = FXMLLoader.load(getClass().getResource("/templates/calc.fxml"));
        root.getStyleClass().add("anchorPane");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setX(500.0);
        primaryStage.setY(80.0);
        primaryStage.setWidth(350);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
