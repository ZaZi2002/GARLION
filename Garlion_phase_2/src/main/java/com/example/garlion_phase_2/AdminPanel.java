package com.example.garlion_phase_2;

import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminPanel {
    public void run(Scanner scanner) {

        Garlion.nowAdmin.showRestaurantsForAdmin();
        Matcher matcher;
        String input = scanner.nextLine();

        System.out.println("AdminPanel options:");
        System.out.println("-> SELECT <Restaurant ID>");
        System.out.println("-> ADD RESTAURANT <name> <location> <types>");
        System.out.println("-> LOGOUT");

        while (true) {
            //checked
            if(input.matches("\\s*LOGOUT\\s*")){
                Garlion.Logout();
                new LoginPanel().run(scanner);
                break;
            }
            // checked
            else if((matcher = InputCommands.getMatcher(input, InputCommands.ADD_RESTAURANT)) != null){
                Garlion.nowAdmin.addRestaurant(matcher);
            }
            // checked
            else if ((matcher = InputCommands.getMatcher(input, InputCommands.SELECT_RESTAURANT)) != null) {
                int id = Integer.parseInt(matcher.group("ID"));
                if(Garlion.nowAdmin.selectRestaurant(id)){
                    new AdminRestaurantPanel().run(scanner);
                }
            }
            // checked
            else System.out.println("Invalid command!");
            input = scanner.nextLine();
        }
    }
}
