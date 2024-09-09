package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;

public class addFoodPanelController {
    @FXML
    private Label AddMessage;

    @FXML
    private RadioButton activeOrnot;

    @FXML
    private TextField discount;

    @FXML
    private TextField foodName;

    @FXML
    private TextField price;

    public void checkAdd() throws IOException {
        if (foodName.getText().isBlank() || price.getText().isBlank() || discount.getText().isBlank()){
            String message = "Fill all the fields!";
            AddMessage.setText(message);
        }
        else {
            addFood();
        }
    }

    public void addFood(){
        Matcher matcher = null;
        String input = "ADD FOOD ";
        input +=foodName.getText()+" " + price.getText();
        matcher=InputCommands.getMatcher(input, InputCommands.ADD_FOOD);
        Garlion.nowAdmin.addFood(matcher);
        Garlion.nowAdmin.nowRestaurant.foods.get(Garlion.nowAdmin.nowRestaurant.foods.size()-1).discount = Integer.parseInt(discount.getText());
        if (!activeOrnot.equals("Active"))
            Garlion.nowAdmin.nowRestaurant.foods.get(Garlion.nowAdmin.nowRestaurant.foods.size()-1).isActive = false;
        String message = "Food added successfully!";
        AddMessage.setText(message);
    }

    public void addPhoto(){

    }

}
