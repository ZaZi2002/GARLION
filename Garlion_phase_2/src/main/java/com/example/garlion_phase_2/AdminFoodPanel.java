package com.example.garlion_phase_2;

import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminFoodPanel {
    public void run(Scanner scanner) {

        Matcher matcher;
        String s1="";
        System.out.println("FoodPanel options:");
        System.out.println("-> DISPLAY RATING");
        System.out.println("-> DISPLAY ALL RATINGS");
        System.out.println("-> DISPLAY COMMENTS");
        System.out.println("-> ADD NEW RESPONSE <COMMENT ID> <MESSAGE>");
        System.out.println("-> EDIT RESPONSE <COMMENT ID> <MESSAGE>");
        System.out.println("-> GO TO RESTAURANT SELECT PAGE");
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
                new AdminRestaurantPanel().run(scanner);
                break;
            }
            // checked
            else if(input.matches("\\s*(GO)\\s+(TO)\\s+(RESTAURANT)\\s+(SELECT)\\s+(PAGE)\\s*")){
                new AdminPanel().run(scanner);
                break;
            }
            // checked
            else if(input.equals("DISPLAY RATING")){
                Garlion.nowAdmin.diSplayFoodRating();
            }
            // checked
            else if(input.equals("DISPLAY ALL RATINGS")){
                Garlion.nowAdmin.diSplayFoodAllRatings();
            }
            // checked
            else if(input.equals("DISPLAY COMMENTS")){
                Garlion.nowAdmin.displayCommentsForFood();
            }
            // checked
            else if((matcher = InputCommands.getMatcher(input, InputCommands.ADD_RESPONSE)) != null){
                int ID=Integer.parseInt(matcher.group("ID"));
                if(Garlion.nowAdmin.responseForFoodErrors(ID)){
                    s1=scanner.nextLine();
                    Garlion.nowAdmin.responseForFoodConfirming(ID,s1);
                }
            }
            // checked
            else if((matcher = InputCommands.getMatcher(input, InputCommands.EDIT_RESPONSE)) != null){
                int ID=Integer.parseInt(matcher.group("ID"));
                if(Garlion.nowAdmin.editResponseForFoodErrors(ID)){
                    s1=scanner.nextLine();
                    Garlion.nowAdmin.responseForFoodConfirming(ID,s1);
                }
            }
            // checked
            else System.out.println("invalid command!");

            input = scanner.nextLine();
        }
    }
}
