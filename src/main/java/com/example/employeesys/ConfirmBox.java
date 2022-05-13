package com.example.employeesys;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {
    private static boolean answer;

    private static VBox layout;




    public static boolean display(String title, String message, int num){

        Stage window = new Stage();

        //basically makes it so you can only use this window until
        //you do what you need here first
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label label = new Label();
        label.setText(message);

        //creating two buttons
        if(num == 1){
            Button yesButton = new Button("yes");
            Button noButton = new Button ("no");

            yesButton.setOnAction(e ->{
                answer = true;
                window.close();

            });
            noButton.setOnAction(e ->{
                answer = false;
                window.close();

            });

            layout = new VBox(20);
            layout.setPadding(new Insets(20,20,20,20));
            layout.getChildren().addAll(label, yesButton, noButton);
            layout.setAlignment(Pos.CENTER);

        }else if(num==2){

            Button okayButton = new Button ("okay");

            okayButton.setOnAction(e ->{
                answer = false;
                window.close();

            });

            layout = new VBox(20);
            layout.setPadding(new Insets(20,20,20,20));
            layout.getChildren().addAll(label, okayButton);
            layout.setAlignment(Pos.CENTER);
        }


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        //display window and before it returns it needs to be closed

        return answer;


    }
}
