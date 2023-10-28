package dev.libjam.demo;

import dev.libjam.gfx.canvas.JavaFxCanvas;
import dev.libjam.gfx.drawable.DefaultGfxNode;
import dev.libjam.gfx.drawable.DefaultGfxParent;
import dev.libjam.gfx.drawable.GfxParent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ParticleDemo extends Application {


    public static void main(String[] args) {

        Application.launch(args);
    }

    public void createParticles(int count, GfxParent parent) {

        for (int i = 0; i < count; i++) {

            // fails if width / height from parent is 0
            DefaultGfxNode n =  new DefaultGfxNode(
                Math.random() * parent.getWidth(),
                Math.random() * parent.getHeight(),
                1, 1
            );
            n.setBgColor(Color.ANTIQUEWHITE);
            n.setVisible(true);
            parent.add(n);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final BorderPane border = new BorderPane();
        Scene scene = new Scene(border);
        primaryStage.setTitle(this.getClass().getCanonicalName());

        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        JavaFxCanvas rootCanvas = new JavaFxCanvas();
        rootCanvas.widthProperty().bind(border.widthProperty());
        rootCanvas.heightProperty().bind(border.heightProperty());

        border.setCenter(rootCanvas);

        DefaultGfxParent parent = new DefaultGfxParent(0, 0, 1, 1);
        // set visible should be set to true by default
        parent.setVisible(true);
        parent.setBorderColor(Color.YELLOW);
        parent.setBgColor(Color.BLACK);
        parent.widthProperty().bind(rootCanvas.widthProperty());
        parent.heightProperty().bind(rootCanvas.heightProperty());

        rootCanvas.add(parent);

        primaryStage.setScene(scene);
        primaryStage.show();

        createParticles(100, parent);
        rootCanvas.draw();


    }
}
