package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class userSelectedPanelController implements Initializable{



    @FXML
    private AnchorPane cardLayout;

    @FXML
    private GridPane grid;

    private ArrayList<Restaurant> restaurants = new ArrayList<>();


    private ArrayList<Restaurant> getData(){
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant;

        for (int i=0;i<Garlion.AllRestaurants.size();i++){
            //ArrayList<String> types = new ArrayList<>();
            //types.add("sonati");
            //restaurant = new Restaurant("mercan",types,9);
            restaurants.add(Garlion.AllRestaurants.get(i));
            //restaurants.add(restaurant);
        }
        return restaurants;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restaurants.addAll(getData());
        int column = 0;
        try {
            for (int i = 0; i < restaurants.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("userRestaurantCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                userRestaurantCardController r = fxmlLoader.getController();
                r.setData(restaurants.get(i));
                grid.add(anchorPane,0,column);
                column++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Back() throws IOException {
        Main.userPanel();
    }

    public void referesh() throws IOException {
        Main.userSelectedPanel();
    }

    public void shoppingCart(){

    }
}
