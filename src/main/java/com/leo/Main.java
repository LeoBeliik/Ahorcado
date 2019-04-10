package com.leo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene main = new Scene(root);
        primaryStage.setScene(main);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    void fin(String lbl){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(400);
        window.setHeight(120);

        Label label = new Label();
        Button btn = new Button("Ok");
        VBox box = new VBox(10);
        box.getChildren().addAll(label, btn);
        box.setAlignment(Pos.CENTER);

        Scene sc = new Scene(box);
        window.setScene(sc);

        if (lbl.equals("win")){
            window.setTitle("Winner, Winner blah bla gay dinner");
            label.setText("pue Haz GANAO");
        } else {
            window.setTitle("JAA LOOSEEERRRRR");
            label.setText("PERDEDOR!");
        }

        btn.setOnAction(e->window.close());
        window.showAndWait();
    }
}
