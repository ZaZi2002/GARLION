package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class adminFoodCardController {

    @FXML
    private Label active;

    @FXML
    private Label discount;

    @FXML
    private AnchorPane foodCardPane;

    @FXML
    private ImageView foodImage;

    @FXML
    private Label foodName;

    @FXML
    private Label foodRating;

    @FXML
    private Label price;

    private Food food;

    public void setData(Food food){
        this.food = food;
        foodName.setText(food.name);
        foodRating.setText(String.valueOf(food.rating.nowRate));
        discount.setText(String.valueOf(food.discount));
        price.setText(String.valueOf(food.price));
        if (food.isActive){
            active.setText("Not Active!");
        }
    }

    public void editFood(){

    }

    public void showComments(){

    }
}
