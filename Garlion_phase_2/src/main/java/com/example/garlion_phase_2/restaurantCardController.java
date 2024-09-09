package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class restaurantCardController{

    @FXML
    private ImageView restaurantImage;

    @FXML
    private Label restaurantName;

    @FXML
    private Label restaurantRating;

    @FXML
    private AnchorPane restaurantCardPane;

    private Restaurant restaurant;

    public void setData(Restaurant restaurant){
        this.restaurant = restaurant;
        restaurantName.setText(restaurant.name);
        restaurantRating.setText(String.valueOf(restaurant.rating.nowRate));
    }

    public void observe() throws IOException {
        Garlion.nowAdmin.nowRestaurant = restaurant;
        Main.adminRestaurantPanel();
    }
}
