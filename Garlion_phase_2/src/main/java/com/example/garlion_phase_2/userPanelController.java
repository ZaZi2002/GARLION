package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class userPanelController {

    @FXML
    private Label alert;

    public void restaurantSelected() throws IOException {
        Main.userSelectedPanel();
    }

    public void setMessage(){
        String message = "Nothing to show at the moment!";
        alert.setText(message);
    }

    public void Back() throws IOException {
        Main.loginPanel();
    }

    public void shoppingCart(){

    }
}
