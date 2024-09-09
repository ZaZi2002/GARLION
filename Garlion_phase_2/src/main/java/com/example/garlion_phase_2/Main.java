package com.example.garlion_phase_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        Main.stage = stage;
        stage.setTitle("Garlion");
        stage.getIcons().add(new Image("C:\\Users\\Zahedi\\IdeaProjects\\Garlion_phase_2\\src\\main\\resources\\com\\example\\garlion_phase_2\\Logo.png"));
        stage.setScene(scene);
        stage.show();
        Scanner scanner = new Scanner(System.in);
        //new LoginPanel().run(scanner);
    }

    public static void loginPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        Main.stage = stage;
        stage.setTitle("Garlion");
        stage.getIcons().add(new Image("C:\\Users\\Zahedi\\IdeaProjects\\Garlion_phase_2\\src\\main\\resources\\com\\example\\garlion_phase_2\\Logo.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void forgotPass() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forgotPass.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void adminPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("adminPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.close();
        stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\Zahedi\\IdeaProjects\\Garlion_phase_2\\src\\main\\resources\\com\\example\\garlion_phase_2\\Logo.png"));
        stage.setTitle("Garlion");
        stage.setScene(scene);
        stage.show();
    }

    public static void addRestaurantPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addRestaurantPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        Stage stage1 = new Stage();
        stage1.getIcons().add(new Image("C:\\Users\\Zahedi\\IdeaProjects\\Garlion_phase_2\\src\\main\\resources\\com\\example\\garlion_phase_2\\Logo.png"));
        stage1.setTitle("Add Restaurant");
        stage1.setScene(scene);
        stage1.show();
    }

    public static void adminRestaurantPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("adminRestaurantPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static void userPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("userPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.close();
        stage = new Stage();
        stage.getIcons().add(new Image("C:\\Users\\Zahedi\\IdeaProjects\\Garlion_phase_2\\src\\main\\resources\\com\\example\\garlion_phase_2\\Logo.png"));
        stage.setTitle("Garlion");
        stage.setScene(scene);
        stage.show();
    }

    public static void userSelectedPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("userSelectedPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void userRestaurantPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("userRestaurantPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void addFoodPanel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addFoodPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        Stage stage1 = new Stage();
        stage1.getIcons().add(new Image("C:\\Users\\Zahedi\\IdeaProjects\\Garlion_phase_2\\src\\main\\resources\\com\\example\\garlion_phase_2\\Logo.png"));
        stage1.setTitle("Add Food");
        stage1.setScene(scene);
        stage1.show();
    }

}