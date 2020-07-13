package viewcontroller;

import model.calculateType;
import viewcontroller.components.Solve;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
// import javafx.scene.input.KeyCode; // FIXME
import javafx.stage.Stage;
import javafx.stage.StageStyle;
// import viewcontroller.ViewController; // FIXME
// import viewcontroller.components.Screen; // FIXME

/**
 *
 * @author Idris Opeyemi
 */
public class ScientificCalculator extends Application {

    private static Stage primaryStage;
    private static Double x1;
    private static Double y1;
    private static Double x_stage;
    private static Double y_stage;

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
        scene.setOnMouseDragged((ev) -> {
            primaryStage.setX(x_stage + ev.getScreenX() - x1);
            primaryStage.setY(y_stage + ev.getScreenY() - y1);
        });
        scene.setOnDragEntered(null);
        scene.setOnMousePressed((ev) -> {
            x1 = ev.getScreenX();
            y1 = ev.getScreenY();
            x_stage = primaryStage.getX();
            y_stage = primaryStage.getY();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
