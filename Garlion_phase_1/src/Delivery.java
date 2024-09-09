import java.util.ArrayList;
import java.util.regex.Matcher;

public class Delivery {
    String username;
    String securityWord;
    String password;
    Food nowDeliveryFood;
    Restaurant nowDeliveryRestaurant;
    ArrayList<Order> orders;
    int balance;
    int location;

    Delivery(String username, String password, int location){
        this.username=username;
        this.password=password;
        this.orders = new ArrayList<>();
        this.balance = 0;
        this.location = location;
    }

    // checked
    static boolean DeliveryRegisterChecker(String username,String password){
        boolean b = false;
        if (!username.matches("^\\d*[a-zA-Z][a-zA-Z0-9]*$")) {
            System.out.println("Username must be numbers or letters and should have at least one letter!");
        }
        else if (Garlion.DeliveryNames.contains(username))
            System.out.println("Username already exists!");
        else {
            b = Garlion.passwordChecker(password);
        }
        return b;
    }
    // checked
    void forgotPassword(String word){
        if(!this.securityWord.equals(word))
            System.out.println("Your answer is wrong!");
        else{
            System.out.println("Your password is : " + this.password);
        }
    }

    void showEstimatedTime(){
        int loc1 = this.location;
        int loc2 = this.nowDeliveryRestaurant.location;
        int distance = DijkstraAlgorithm.distance(loc1,loc2);
        double time = distance/10;
        System.out.println("Estimated delivery time is " + time + " minuets");
    }

    void findBestPath(Matcher matcher){
        String ID = matcher.group("ID");
        int loc1 = this.location;
        int loc2 = 0;
        for (int i=0;i<Garlion.AllOrders.size();i++){
            if (Garlion.AllOrders.get(i).ID == Integer.parseInt(ID)){
                loc2 = Garlion.AllOrders.get(i).orderRestaurant.location;
            }
        }
        int distance = DijkstraAlgorithm.distance(loc1,loc2);
        System.out.println("Best path is " + distance + " meters!");
    }

    void showOrdersForDelivery(){
        System.out.println("Your orders list :");
        if (Garlion.AllOrders != null)
            for(int i=0;i<Garlion.AllOrders.size();i++){
                if (!Garlion.AllOrders.get(i).isSent)
                    System.out.println("Order Restaurant ID: " + Garlion.AllOrders.get(i).orderRestaurant.ID +"   Price: "
                            + Garlion.AllOrders.get(i).totalCost/10 + "    Restaurant loc: "+Garlion.AllOrders.get(i).orderRestaurant.location +
                            "   Customer loc: "+Garlion.AllOrders.get(i).orderUser.location);
            }
    }
}
