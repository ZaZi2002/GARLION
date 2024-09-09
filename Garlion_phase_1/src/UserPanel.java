import java.util.Scanner;
import java.util.regex.Matcher;

public class UserPanel {
    public void run(Scanner scanner) {
        Garlion.nowUser.showRestaurantsForUser();
        System.out.println("UserPanel options:");
        System.out.println("-> SEARCH RESTAURANT <restaurant name>");
        System.out.println("-> SELECT <Restaurant ID>");
        System.out.println("-> ACCESS ORDER HISTORY");
        System.out.println("-> SELECT ORDER <Order ID>");
        System.out.println("-> DISPLAY CART STATUS");
        System.out.println("-> CONFIRM ORDER");
        System.out.println("-> SHOW ESTIMATED DELIVERY TIME");
        System.out.println("-> CHARGE ACCOUNT <amount>");
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
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.SEARCH_RESTAURANT_USER)) != null){
                Garlion.nowUser.searchRestaurantByName(matcher);
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.SELECT_RESTAURANT)) != null) {
                if(Garlion.nowUser.selectRestaurant(matcher)){
                    new userRestaurantPanel().run(scanner);
                }
            }
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
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.SELECT_ORDER)) != null) {
                Garlion.nowUser.showSelectedOrderInformation(matcher);
            }
            // checked
            else if (( matcher= InputCommands.getMatcher(input, InputCommands.CHARGE_ACCOUNT)) != null) {
                Garlion.nowUser.chargeAccount(matcher);
            }
            // checked
            else if (input.equals("DISPLAY ACCOUNT CHARGE")) {
                Garlion.nowUser.showAccountCharge();}

            else if (input.equals("SHOW ESTIMATED DELIVERY TIME")){
                Garlion.nowUser.showEstimatedTime();
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
