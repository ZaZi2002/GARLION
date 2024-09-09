package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.regex.Matcher;

public class ForgotPassController {

    @FXML
    private ToggleGroup forgetType;
    @FXML
    private TextField forgetName;
    @FXML
    private TextField securityword;
    @FXML
    private Label forgetMessage;
    @FXML
    private Label password;

    public void checkForget() throws IOException {
        if (forgetName.getText().isBlank() || securityword.getText().isBlank()){
            String message = "Some of the fields are empty!";
            forgetMessage.setText(message);
        }
        else {
            recoverPassword();
        }
    }

    public void recoverPassword(){
        Matcher matcher = null;
        RadioButton type = (RadioButton) forgetType.getSelectedToggle();
        String userType=type.getText();
        String input = "";
        if(userType.equals("Admin")){
            input +="FORGOT ADMIN PASSWORD ";
        }
        else if(userType.equals("User")){
            input +="FORGOT USER PASSWORD ";
        }
        else {
            input +="FORGOT DELIVERY PASSWORD ";
        }
        input +=forgetName.getText();
        if(userType.equals("Admin")){
            matcher=InputCommands.getMatcher(input, InputCommands.FORGET_PASSWORD_ADMIN);
            String username = matcher.group("username");
            if(!Garlion.AdminsNames.contains(username)) {
                String message = "Username doesn't exist!";
                forgetMessage.setText(message);
            }
            else{
                int id = Garlion.AdminsNames.indexOf(username);
                if (Garlion.Admins.get(id).securityWord.equals(securityword.getText())) {
                    password.setText(Garlion.Admins.get(id).password);
                }
                else {
                    String message = "Wrong answer!";
                    forgetMessage.setText(message);
                }
            }
        }
        else if(userType.equals("User")) {
            matcher = InputCommands.getMatcher(input, InputCommands.FORGET_PASSWORD_USER);
            String username = matcher.group("username");
            if(!Garlion.UsersNames.contains(username)) {
                String message = "Username doesn't exist!";
                forgetMessage.setText(message);
            }
            else{
                int id = Garlion.UsersNames.indexOf(username);
                if (Garlion.Users.get(id).securityWord.equals(securityword.getText())) {
                    password.setText(Garlion.Users.get(id).password);
                }
                else {
                    String message = "Wrong answer!";
                    forgetMessage.setText(message);
                }
            }
        }
        else if(userType.equals("Delivery")) {
            matcher = InputCommands.getMatcher(input, InputCommands.FORGET_PASSWORD_DELIVERY);
            String username = matcher.group("username");
            if(!Garlion.DeliveryNames.contains(username)) {
                String message = "Username doesn't exist!";
                forgetMessage.setText(message);
            }
            else{
                int id = Garlion.DeliveryNames.indexOf(username);
                if (Garlion.Deliveries.get(id).securityWord.equals(securityword.getText())) {
                    password.setText(Garlion.Deliveries.get(id).password);
                }
                else {
                    String message = "Wrong answer!";
                    forgetMessage.setText(message);
                }
            }
        }
    }

    public void Back() throws IOException {
        Main.loginPanel();
    }
}
