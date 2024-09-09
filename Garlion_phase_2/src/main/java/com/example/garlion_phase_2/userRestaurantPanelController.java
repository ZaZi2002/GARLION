package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class userRestaurantPanelController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private Label restaurantName;

    @FXML
    private TextField rateFoodAmount;

    @FXML
    private Label rateAlert;

    private ArrayList<Food> foods = new ArrayList<>();


    public void Back() throws IOException {
        Main.userPanel();
    }

    public void rateFood() throws IOException {
        String rate = rateFoodAmount.getText();
        if (!rate.isBlank()) {
            Garlion.nowUser.nowUserResturant.rating.newRating(Garlion.nowUser, Double.parseDouble(rate));
            rateAlert.setText("Rated successfully!");
        }
        else {
            rateAlert.setText("Please fill the text bar!");
        }
    }

    public void showComments(){

    }

    public void referesh() throws IOException {
        Main.userRestaurantPanel();
    }

    public void shoppingCart(){

    }

    private ArrayList<Food> getData(){
        ArrayList<Food> foods = new ArrayList<>();
        Food food;
        restaurantName.setText(Garlion.nowUser.nowUserResturant.name);
        for (int i=0;i<Garlion.nowUser.nowUserResturant.foods.size();i++){
            //ArrayList<String> types = new ArrayList<>();
            //types.add("sonati");
            //restaurant = new Restaurant("mercan",types,9);
            foods.add(Garlion.nowUser.nowUserResturant.foods.get(i));
            //restaurants.add(restaurant);
        }
        return foods;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        foods.addAll(getData());
        int column = 0;
        try {
            for (int i = 0; i < foods.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("userFoodCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                userFoodCardController r = fxmlLoader.getController();
                r.setData(foods.get(i));
                grid.add(anchorPane,0,column);
                column++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
