package com.gitlab.saylenty.chessPl.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launches the JavaFX UI defined in FXML.
 */
public class ChessBoardApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/gitlab/saylenty/chessPl/ui/board.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Chess Solutions");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
