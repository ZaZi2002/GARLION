package com.example.garlion_phase_2;

import java.util.Scanner;
import java.util.regex.Matcher;

public class DeliveryPanel {
    public void run(Scanner scanner) {
        Garlion.nowDelivery.showOrdersForDelivery();
        System.out.println("DeliveryPanel options:");
        System.out.println("-> SELECT ORDER <Order ID>");
        System.out.println("-> SHOW DELIVERY TIME");
        System.out.println("-> FIND BEST PATH <order ID>");
        System.out.println("-> DISPLAY ACCOUNT CHARGE");
        System.out.println("-> SHOW MAP");
        System.out.println("-> LOGOUT");

        Matcher matcher;
        String input = scanner.nextLine();
        while (true) {
            // checked
            if(input.matches("\\s*LOGOUT\\s*")){
                Garlion.Logout();
                new LoginPanel().run(scanner);
                break;
            }
            // checked
            // checked
            else if (input.equals("ACCESS ORDER HISTORY")) {
                Garlion.nowUser.showAllOrderHistory();}
            // checked
            else if (input.equals("DISPLAY CART STATUS")) {
                Garlion.nowUser.displayCartStatus();}
            // checked
            else if (input.equals("CONFIRM ORDER")) {
                Garlion.nowUser.confirmOrder();}
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.FIND_PATH)) != null) {
                Garlion.nowDelivery.findBestPath(matcher);
            }

            else if (input.equals("SHOW DELIVERY TIME")){
                Garlion.nowDelivery.showEstimatedTime();
            }
            else if (input.equals("SHOW MAP")){
                DijkstraAlgorithm.showMap();
            }
            // checked
            else System.out.println("invalid command!");
            input = scanner.nextLine();
        }
    }
}

