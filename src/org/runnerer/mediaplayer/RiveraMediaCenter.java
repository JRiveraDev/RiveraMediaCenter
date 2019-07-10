package org.runnerer.mediaplayer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RiveraMediaCenter
        extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(final Stage stage) throws Exception
    {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rivera Media Center");
        scene.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent me)
            {
                if (me.getClickCount() == 2)
                {
                    stage.setFullScreen(true);
                }
            }
        });
        stage.show();
    }
}
