package dev.libjam.demo.snowFall;

import dev.libjam.gfx.canvas.JavaFxCanvas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SnowFallDemo extends Application {



    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        JavaFxCanvas rootCanvas = new JavaFxCanvas();
        configure(primaryStage, rootCanvas);

        primaryStage.show();

        SnowWorld world = new SnowWorld(0, 0, 800, 600);
        SnowFallController ctrl = new SnowFallController(world, rootCanvas);
        ctrl.init();
    }



    private void configure(Stage primaryStage, JavaFxCanvas rootCanvas) {
        final BorderPane border = new BorderPane();
        Scene scene = new Scene(border);
        primaryStage.setTitle(this.getClass().getCanonicalName());

        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        rootCanvas.widthProperty().bind(border.widthProperty());
        rootCanvas.heightProperty().bind(border.heightProperty());

        border.setCenter(rootCanvas);

        primaryStage.setScene(scene);
    }


}
