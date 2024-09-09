package com.example.garlion_phase_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminRestaurantPanel {
    public void run(Scanner scanner) {
        String result = "";
        Matcher matcher;
        String s1="";
        System.out.println("RestaurantPanel options:");
        System.out.println("-> SHOW LOCATION");
        System.out.println("-> EDIT LOCATION <location>");
        System.out.println("-> SHOW FOODTYPE");
        System.out.println("-> EDIT FOODTYPE <types>");
        System.out.println("-> SELECT MENU");
        System.out.println("-> EDIT FOOD <FOOD ID> NAME <NEW NAME>");
        System.out.println("-> EDIT FOOD <FOOD ID> PRICE <NEW PRICE>");
        System.out.println("-> ADD FOOD <FOOD NAME> <FOOD PRICE>");
        System.out.println("-> DELETE FOOD <FOOD ID>");
        System.out.println("-> ACTIVE FOOD <FOOD ID>");
        System.out.println("-> DEACTIVE FOOD <FOOD ID>");
        System.out.println("-> DISCOUNT FOOD <FOOD ID> <DISCOUNT PERCENT> <TIMESTAMP>");
        System.out.println("-> SELECT FOOD <FOOD ID>");
        System.out.println("-> DISPLAY OPEN ORDERS");
        System.out.println("-> EDIT ORDER <ORDER ID> STATUS SENT");
        System.out.println("-> SHOW ORDER HISTORY");
        System.out.println("-> BACK");
        System.out.println("-> LOGOUT");
        String input = scanner.nextLine();

        while (true) {
            // checked
            if(input.matches("\\s*LOGOUT\\s*")){
                Garlion.Logout();
                new LoginPanel().run(scanner);
                break;
            }
            // checked
            else if(input.matches("\\s*BACK\\s*")){
                new AdminPanel().run(scanner);
                break;
            }
            // checked
            else if(input.matches("\\s*(DISPLAY)\\s+(OPEN)\\s+(ORDERS)\\s*")){
                Garlion.nowAdmin.showOpenOrders();
            }
            // checked
            else if((matcher = InputCommands.getMatcher(input, InputCommands.EDIT_ORDER)) != null){
                Garlion.nowAdmin.editOrderStatus(matcher);
            }
            // checked
            else if(input.matches("\\s*(SHOW)\\s+(ORDER)\\s+(HISTORY)\\s*")){
                Garlion.nowAdmin.showOrderHistory();
            }
            // checked
            else if(input.matches("\\s*SHOW\\s+FOODTYPE\\s*")){
                Garlion.nowAdmin.showRestaurantType();
            }
            // checked
            else if((matcher = InputCommands.getMatcher(input, InputCommands.EDIT_RESTAURANT_TYPE)) != null){
                String [] s = matcher.group("types").split("\\s+");
                ArrayList<String> Types= new ArrayList<>(Arrays.asList(s));
                if(Garlion.nowAdmin.editRestaurantType(Types)){
                    System.out.println("ARE YOU SURE YOU WANT TO CHANGE YOUR RESTAURANT TYPE? (YES , NO)");
                    s1 = scanner.nextLine();
                    if(s1.equalsIgnoreCase("YES")){
                        Garlion.nowAdmin.editRestaurantTypeConfirming(Types);
                    }
                    else {
                        run(scanner);
                    }
                }
            }
            // checked
            else if(input.matches("\\s*SELECT\\s+MENU\\s*")){
                Garlion.nowAdmin.showRestaurantFoodsForAdmin();
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.EDIT_FOOD_NAME)) != null){
                Garlion.nowAdmin.editFoodName(matcher);
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.EDIT_FOOD_PRICE)) != null){
                Garlion.nowAdmin.editFoodPrice(matcher);
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.ADD_FOOD)) != null) {
                Garlion.nowAdmin.addFood(matcher);
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.DELETE_FOOD)) != null) {
                if(Garlion.nowAdmin.deleteRestaurantFood(matcher)){
                    System.out.println("ARE YOU SURE YOU WANT TO DELETE THIS FOOD? (YES , NO)");
                    s1 = scanner.nextLine();
                    if(s1.equalsIgnoreCase("YES")){
                        Garlion.nowAdmin.deleteRestaurantFoodConfirming(matcher);
                        System.out.println("Food deleted successfully!");
                    }
                }
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.ACTIVE_FOOD)) != null){
                Garlion.nowAdmin.activeRestaurantFood(matcher);
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.DEACTIVE_FOOD)) != null){
                Garlion.nowAdmin.deactiveRestaurantFood(matcher);
            }
            // checked
            else if(( matcher= InputCommands.getMatcher(input, InputCommands.DISCOUNT_FOOD)) != null){
                Garlion.nowAdmin.setDiscountForFood(matcher);
            }
            // checked
            else if(( matcher= InputCommands.getMatcher(input, InputCommands.SELECT_FOOD)) != null){
                Garlion.nowAdmin.selectFood(matcher);
                new AdminFoodPanel().run(scanner);
            }
            else if (input.equals("SHOW LOCATION")){
                Garlion.nowAdmin.restaurantShowLocation();
            }
            else if(( matcher= InputCommands.getMatcher(input, InputCommands.EDIT_LOCATION)) != null){
                Garlion.nowAdmin.restaurantEditLocation(matcher);
            }
            // checked
            else if(input.matches("\\s*LOGOUT\\s*")){
                Garlion.Logout();
                new LoginPanel().run(scanner);
                break;
            }
            // checked
            else System.out.println("invalid command!");

            input = scanner.nextLine();
        }
    }
}
