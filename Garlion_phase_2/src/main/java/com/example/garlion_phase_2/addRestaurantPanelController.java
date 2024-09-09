package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;

public class addRestaurantPanelController {

    @FXML
    private TextField restaurantLocation;

    @FXML
    private Label AddMessage;

    @FXML
    private TextField restaurantName;

    @FXML
    private TextField restaurantTypes;

    public void checkAdd() throws IOException {
        if (restaurantName.getText().isBlank() || restaurantTypes.getText().isBlank() || restaurantLocation.getText().isBlank()){
            String message = "Fill all the fields!";
            AddMessage.setText(message);
        }
        else {
            addRestaurant();
        }
    }

    public void addRestaurant(){
        Matcher matcher = null;
        String input = "ADD RESTAURANT ";
        input +=restaurantName.getText()+" " + restaurantLocation.getText() + " " +restaurantTypes.getText();
        matcher=InputCommands.getMatcher(input, InputCommands.ADD_RESTAURANT);
        Garlion.nowAdmin.addRestaurant(matcher);
        String message = "Restaurant added successfully!";
        AddMessage.setText(message);
    }

    public void addPhoto(){

    }
}
