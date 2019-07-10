package org.runnerer.mediaplayer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController
        implements Initializable
{

    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private String filePath;
    @FXML
    private Slider slider;
    @FXML
    private HBox menuBar;
    @FXML
    private Slider seekSlider;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Play What?", new String[]{"*.mp4"});
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        this.filePath = file.toURI().toString();
        if (this.filePath != null)
        {
            Media media = new Media(this.filePath);
            this.mediaPlayer = new MediaPlayer(media);
            this.mediaView.setMediaPlayer(this.mediaPlayer);
            DoubleProperty width = this.mediaView.fitWidthProperty();
            DoubleProperty height = this.mediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(this.mediaView.sceneProperty(), new String[]{"width"}));
            height.bind(Bindings.select(this.mediaView.sceneProperty(), new String[]{"height"}));
            this.slider.setValue(this.mediaPlayer.getVolume() * 100.0D);
            this.slider.valueProperty().addListener(new InvalidationListener()
            {
                public void invalidated(Observable observable)
                {
                    FXMLDocumentController.this.mediaPlayer.setVolume(FXMLDocumentController.this.slider.getValue() / 100.0D);
                }
            });


            this.mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>()
            {
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
                {
                    FXMLDocumentController.this.seekSlider.setValue(newValue.toSeconds());
                }
            });

            this.seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent event)
                {
                    FXMLDocumentController.this.mediaPlayer.seek(Duration.seconds(FXMLDocumentController.this.seekSlider.getValue()));
                }
            });
            this.mediaPlayer.play();
        }
    }


    @FXML
    private void pauseVideo(ActionEvent event)
    {
        this.mediaPlayer.pause();
    }


    @FXML
    private void exitVideo(ActionEvent event)
    {
        System.exit(0);
    }


    @FXML
    private void stopVideo(ActionEvent event)
    {
        this.mediaPlayer.stop();
    }


    @FXML
    private void playVideo(ActionEvent event)
    {
        this.mediaPlayer.play();
        this.mediaPlayer.setRate(1.0D);
    }


    @FXML
    private void fastVideo(ActionEvent event)
    {
        this.mediaPlayer.setRate(1.5D);
    }


    @FXML
    private void fasterVideo(ActionEvent event)
    {
        this.mediaPlayer.setRate(2.0D);
    }


    @FXML
    private void slowVideo(ActionEvent event)
    {
        this.mediaPlayer.setRate(0.75D);
    }


    @FXML
    private void slowerVideo(ActionEvent event)
    {
        this.mediaPlayer.setRate(0.5D);
    }

    public void initialize(URL url, ResourceBundle rb)
    {
    }
}
