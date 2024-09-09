package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class adminRestaurantPanelController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private Label restaurantName;

    private ArrayList<Food> foods = new ArrayList<>();


    public void Back() throws IOException {
        Main.adminPanel();
    }

    public void addFood() throws IOException {
        Main.addFoodPanel();
    }

    public void showComments(){

    }

    public void RefreSh() throws IOException {
        Main.adminRestaurantPanel();
    }

    private ArrayList<Food> getData(){
        ArrayList<Food> foods = new ArrayList<>();
        Food food;
        restaurantName.setText(Garlion.nowAdmin.nowRestaurant.name);
        System.out.println(Garlion.nowAdmin.nowRestaurant.foods.size());
        for (int i=0;i<Garlion.nowAdmin.nowRestaurant.foods.size();i++){
            //ArrayList<String> types = new ArrayList<>();
            //types.add("sonati");
            //restaurant = new Restaurant("mercan",types,9);
            foods.add(Garlion.nowAdmin.nowRestaurant.foods.get(i));
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
                fxmlLoader.setLocation(getClass().getResource("adminFoodCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                adminFoodCardController r = fxmlLoader.getController();
                r.setData(foods.get(i));
                grid.add(anchorPane,0,column);
                column++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
