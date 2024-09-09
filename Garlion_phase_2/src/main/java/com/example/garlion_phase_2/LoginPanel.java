package com.example.garlion_phase_2;

import java.util.Scanner;
import java.util.regex.Matcher;
public class LoginPanel {
    public void run(Scanner scanner) {
        DataReadWrite dataReadWrite = new DataReadWrite();
        dataReadWrite.readData();

        Matcher matcher;
        String s1 = "";
        System.out.println("LoginPanel options:");
        System.out.println("-> ADD ADMIN <username> <password>");
        System.out.println("-> ADD USER <username> <password> <location>");
        System.out.println("-> ADD DELIVERY <username> <password> <location>");
        System.out.println("-> LOGIN ADMIN <username> <password>");
        System.out.println("-> LOGIN USER <username> <password>");
        System.out.println("-> LOGIN DELIVERY <username> <password>");
        System.out.println("-> FORGOT ADMIN PASSWORD <username>");
        System.out.println("-> FORGOT USER PASSWORD <username>");
        System.out.println("-> FORGOT DELIVERY PASSWORD <username>");
        System.out.println("-> EXIT");
        String input = scanner.nextLine();

        while (!input.matches("\\s*EXIT\\s*")) {
            // checked
            if (( matcher = InputCommands.getMatcher(input, InputCommands.ADD_USER)) != null) {
                if(Garlion.AddUser(matcher)) {
                    System.out.println("What is your favorite animal?");
                    s1 = scanner.nextLine();
                    Garlion.setSecurityWordForUser(matcher.group("username"),s1);
                    new UserPanel().run(scanner);
                }
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.ADD_ADMIN)) != null) {
                if(Garlion.AddAdmin(matcher)) {
                    System.out.println("What is your favorite animal?");
                    s1 = scanner.nextLine();
                    Garlion.setSecurityWordForAdmin(matcher.group("username"),s1);
                    new AdminPanel().run(scanner);
                }
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.ADD_DELIVERY)) != null) {
                if(Garlion.AddDelivery(matcher)) {
                    System.out.println("What is your favorite animal?");
                    s1 = scanner.nextLine();
                    Garlion.setSecurityWordForDelivery(matcher.group("username"),s1);
                    new DeliveryPanel().run(scanner);
                }
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.LOGIN_USER)) != null) {
                if(Garlion.LoginUser(matcher)) {
                    String sum = Garlion.randomNumbersForLogin();
                    s1 = scanner.nextLine();
                    if(sum.equals(s1))
                        new UserPanel().run(scanner);
                }
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.LOGIN_ADMIN)) != null) {
                if(Garlion.LoginAdmin(matcher)) {
                    String sum = Garlion.randomNumbersForLogin();
                    s1 = scanner.nextLine();
                    if(sum.equals(s1))
                        new AdminPanel().run(scanner);
                }
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.LOGIN_DELIVERY)) != null) {
                if(Garlion.LoginDelivery(matcher)) {
                    String sum = Garlion.randomNumbersForLogin();
                    s1 = scanner.nextLine();
                    if(sum.equals(s1))
                        new DeliveryPanel().run(scanner);
                }
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.FORGET_PASSWORD_USER)) != null) {
                String s = matcher.group("username");
                if(!Garlion.UsersNames.contains(s))
                    System.out.println("Username doesn't exist!");
                else{
                    System.out.println("What is your favorite animal?");
                    s1 = scanner.nextLine();
                    Garlion.restorePassForUser(s,s1);
                }
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.FORGET_PASSWORD_ADMIN)) != null) {
                String s = matcher.group("username");
                if(!Garlion.AdminsNames.contains(s))
                    System.out.println("Username doesn't exist!");
                else{
                    System.out.println("What is your favorite animal?");
                    s1 = scanner.nextLine();
                    Garlion.restorePassForAdmin(s,s1);
                }
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.FORGET_PASSWORD_DELIVERY)) != null) {
                String s = matcher.group("username");
                if(!Garlion.DeliveryNames.contains(s))
                    System.out.println("Username doesn't exist!");
                else{
                    System.out.println("What is your favorite animal?");
                    s1 = scanner.nextLine();
                    Garlion.restorePassForDelivery(s,s1);
                }
            }
            // checked
            else System.out.println("Invalid command!");

            input = scanner.nextLine();
        }

        dataReadWrite.writeData();
    }
}