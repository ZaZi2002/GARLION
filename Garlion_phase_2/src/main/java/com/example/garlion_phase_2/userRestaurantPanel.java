package com.example.garlion_phase_2;

import java.util.Scanner;
import java.util.regex.Matcher;

public class userRestaurantPanel {
    public void run(Scanner scanner) {
        Garlion.nowUser.showRestaurantsForUser();
        System.out.println("RestaurantPanel options:");
        System.out.println("-> SEARCH FOOD <Food Name>");
        System.out.println("-> SELECT FOOD <Food ID>");
        System.out.println("-> DISPLAY COMMENTS");
        System.out.println("-> ADD NEW COMMENT");
        System.out.println("-> EDIT COMMENT <Comment ID>");
        System.out.println("-> DISPLAY RATING");
        System.out.println("-> SUBMIT RATING");
        System.out.println("-> EDIT RATING");
        System.out.println("-> BACK");
        System.out.println("-> LOGOUT");
        Garlion.nowUser.showFoodsForUser();
        String result = "";
        Matcher matcher;
        String s1 = "";
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
                new UserPanel().run(scanner);
                break;
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.SEARCH_FOOD_USER)) != null) {
                Garlion.nowUser.searchFoodByName(matcher);
            }
            // checked
            else if(input.equals("ADD NEW COMMENT")){
                System.out.println("Comment's content:");
                s1=scanner.nextLine();
                Garlion.nowUser.addCommentForRestaurant(s1);
            }
            // checked
            else if(input.equals("DISPLAY COMMENTS")){
                Garlion.nowUser.displayCommentsForRestaurant();
            }
            // checked
            else if(input.equals("DISPLAY RATING")){
                Garlion.nowUser.displayRestaurantRating();
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.SUBMIT_RATING)) != null) {
                Garlion.nowUser.submitRestaurantRating(matcher);
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.EDIT_RATING)) != null) {
                Garlion.nowUser.editRestaurantRating(matcher);
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.EDIT_COMMENT_USER)) != null) {
                int ID=Integer.parseInt(matcher.group("ID"));
                if(!Garlion.nowUser.editCommentForRestaurantErrors(ID)){
                    System.out.println("Comment's new content:");
                    s1=scanner.nextLine();
                    Garlion.nowUser.editCommentForRestaurantConfirming(ID,s1);
                }
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.SELECT_FOOD)) != null) {
                if(Garlion.nowUser.selectFood(matcher)){
                    new userFoodPanel().run(scanner);
                }
            }
            // else if    *SHOW LOCATION*;
            else System.out.println("invalid command!");
            input = scanner.nextLine();
        }
    }
}
