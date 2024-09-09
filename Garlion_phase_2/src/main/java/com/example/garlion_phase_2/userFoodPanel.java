package com.example.garlion_phase_2;

import java.util.Scanner;
import java.util.regex.Matcher;

public class userFoodPanel {
    public void run(Scanner scanner) {
        Matcher matcher;
        String s1 = "";
        System.out.println("FoodPanel options:");
        System.out.println("-> DISPLAY COMMENTS");
        System.out.println("-> ADD NEW COMMENT");
        System.out.println("-> EDIT COMMENT <Comment ID>");
        System.out.println("-> DISPLAY RATING");
        System.out.println("-> SUBMIT RATING <amount>");
        System.out.println("-> EDIT RATING");
        System.out.println("-> ADD THIS FOOD TO CART");
        System.out.println("-> GO TO USER PANEL");
        System.out.println("-> BACK");
        System.out.println("-> LOGOUT");
        String input = scanner.nextLine();
        while (true) {
            // checked
            if (input.matches("\\s*LOGOUT\\s*")) {
                Garlion.Logout();
                new LoginPanel().run(scanner);
                break;
            }
            // checked
            else if (input.matches("\\s*BACK\\s*")) {
                new userRestaurantPanel().run(scanner);
                break;
            }
            // checked
            else if(input.matches("\\s*(GO)\\s+(TO)\\s+(USER)\\s+(PANEL)\\s*")){
                new UserPanel().run(scanner);
                break;
            }
            // checked
            else if(input.equals("ADD NEW COMMENT")){
                System.out.println("Comment's content:");
                s1=scanner.nextLine();
                Garlion.nowUser.addCommentForFood(s1);
            }
            // checked
            else if(input.equals("DISPLAY COMMENTS")){
                Garlion.nowUser.displayCommentsForFood();}
            // checked
            else if(input.equals("DISPLAY RATING")){
                Garlion.nowUser.displayFoodRating();}
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.SUBMIT_RATING)) != null) {
                Garlion.nowUser.submitFoodRating(matcher);}
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.EDIT_RATING)) != null) {
                Garlion.nowUser.editFoodRating(matcher);}
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.EDIT_COMMENT_USER)) != null) {
                int ID=Integer.parseInt(matcher.group("ID"));
                if(!Garlion.nowUser.editCommentForFoodErrors(ID)){
                    System.out.println("Comment's new content:");
                    s1=scanner.nextLine();
                    Garlion.nowUser.editCommentForFoodConfirming(ID,s1);
                }
            }
            // checked
            else if (input.equals("ADD THIS FOOD TO CART")) {
                Garlion.nowUser.addFoodToCart();
            }
            // checked
            else System.out.println("invalid command!");
            input = scanner.nextLine();
        }
    }
}
