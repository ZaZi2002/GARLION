package com.example.garlion_phase_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.regex.Matcher;

public class LoginPanelController {
    @FXML
    private ToggleGroup signUpType;
    @FXML
    private ToggleGroup signInType;
    @FXML
    private TextField signUpName;
    @FXML
    private TextField signInName;
    @FXML
    private TextField signUpPassword;
    @FXML
    private TextField signInPassword;
    @FXML
    private TextField securityWord;
    @FXML
    private TextField logInQ;
    @FXML
    private Label signUpMessage;
    @FXML
    private Label signUpExplain;
    @FXML
    public Label signInMessage;

    public void checkSignUp() throws IOException {
        if (signUpName.getText().isBlank() || signUpPassword.getText().isBlank() || securityWord.getText().isBlank()){
            String message = "Some of the fields are empty!";
            signUpMessage.setText(message);
        }
        else {
            SignUp();
        }
    }

    public void SignUp() throws IOException {
        Matcher matcher = null;
        RadioButton type = (RadioButton) signUpType.getSelectedToggle();
        String userType=type.getText();
        String input = "";
        if(userType.equals("Admin")){
            input +="ADD ADMIN ";
        }
        else if(userType.equals("User")){
            input +="ADD USER ";
        }
        else {
            input +="ADD DELIVERY";
        }
        input +=signUpName.getText()+" "+signUpPassword.getText();
        if(userType.equals("Admin")){
            matcher=InputCommands.getMatcher(input, InputCommands.ADD_ADMIN);
            if (Garlion.AddAdmin(matcher)) {
                Garlion.setSecurityWordForAdmin(signUpName.getText(), securityWord.getText());
                String message = "Signed up successfully!";
                signUpMessage.setText(message);
                delay();
                Main.adminPanel();
            }
            else {
                String message = "Wrong username or password format!";
                signUpMessage.setText(message);
                String explain = "USERNAME must be only numbers or letters with at least one letter!" +
                        " PASSWORD must contain at least 8 characters, one number, one uppercase and one lowercase letter! ";
                signUpExplain.setText(explain);
            }
        }
        else if(userType.equals("User")){
            matcher=InputCommands.getMatcher(input, InputCommands.ADD_USER);
            if (Garlion.AddUser(matcher)) {
                Garlion.setSecurityWordForUser(signUpName.getText(), securityWord.getText());
                String message = "Signed up successfully!";
                signUpMessage.setText(message);
                delay();
                Main.userPanel();
            }
            else {
                String message = "Wrong password format!";
                signUpMessage.setText(message);
            }
        }
        else {
            matcher=InputCommands.getMatcher(input, InputCommands.ADD_DELIVERY);
            if (Garlion.AddDelivery(matcher)) {
                Garlion.setSecurityWordForDelivery(signUpName.getText(), securityWord.getText());
                String message = "Signed up successfully!";
                signUpMessage.setText(message);
            }
            else {
                String message = "Wrong password format!";
                signUpMessage.setText(message);
            }
        }
    }

    public void checkSignIn() throws IOException {
        if (signInName.getText().isBlank() || signInPassword.getText().isBlank() || logInQ.getText().isBlank()){
            String message = "Some of the fields are empty!";
            signInMessage.setText(message);
        }
        else {
            SignIn();
        }
    }

    public void SignIn() throws IOException {
        Matcher matcher = null;
        RadioButton type = (RadioButton) signInType.getSelectedToggle();
        String userType=type.getText();
        String input = "";
        if(userType.equals("Admin")){
            input +="LOGIN ADMIN ";
        }
        else if(userType.equals("User")){
            input +="LOGIN USER ";
        }
        else {
            input +="LOGIN DELIVERY";
        }
        input +=signInName.getText()+" "+signInPassword.getText();
        if(userType.equals("Admin")){
            matcher=InputCommands.getMatcher(input, InputCommands.LOGIN_ADMIN);
            if (logInQ.getText().equals("129")) {
                if (Garlion.LoginAdmin(matcher)) {
                    String message = "Signed in successfully!";
                    signInMessage.setText(message);
                    delay();
                    Main.adminPanel();
                } else {
                    String username = matcher.group("username");
                    if (!Garlion.AdminsNames.contains(username)) {
                        String message = "Username doesn't exist!";
                        signInMessage.setText(message);
                    }
                    else {
                        String message = "Password is wrong!";
                        signInMessage.setText(message);
                    }
                }
            }
            else {
                String message = "Wrong answer!";
                signInMessage.setText(message);
            }
        }
        else if(userType.equals("User")) {
            matcher = InputCommands.getMatcher(input, InputCommands.LOGIN_USER);
            if (logInQ.getText().equals("129")) {
                if (Garlion.LoginUser(matcher)) {
                    String message = "Signed in successfully!";
                    signInMessage.setText(message);
                    delay();
                    Main.userPanel();
                } else {
                    String username = matcher.group("username");
                    if (!Garlion.UsersNames.contains(username)) {
                        String message = "Username doesn't exist!";
                        signInMessage.setText(message);
                    } else {
                        String message = "Password is wrong!";
                        signInMessage.setText(message);
                    }
                }
            } else {
                String message = "Wrong answer!";
                signInMessage.setText(message);
            }
        }
        else if(userType.equals("Delivery")) {
            matcher = InputCommands.getMatcher(input, InputCommands.LOGIN_DELIVERY);
            if (logInQ.getText().equals("129")) {
                if (Garlion.LoginDelivery(matcher)) {
                    String message = "Signed in successfully!";
                    signInMessage.setText(message);
                } else {
                    String username = matcher.group("username");
                    if (!Garlion.DeliveryNames.contains(username)) {
                        String message = "Username doesn't exist!";
                        signInMessage.setText(message);
                    } else {
                        String message = "Password is wrong!";
                        signInMessage.setText(message);
                    }
                }
            } else {
                String message = "Wrong answer!";
                signInMessage.setText(message);
            }
        }
    }

    public void forgotPassword() throws IOException {
        Main.forgotPass();
    }

    public void delay(){
        int i;
        for (i=0;i<Integer.MAX_VALUE;i++){
        }
    }
}