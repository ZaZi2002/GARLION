import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    int ID;
    String time;
    boolean isSent;
    ArrayList<Food> orderFoods;
    ArrayList<Integer> orderFoodsIDs;
    Restaurant orderRestaurant;
    User orderUser;
    int totalCost;
    Order(User user){
        this.ID=user.orders.size()+1;
        this.orderRestaurant=user.nowUserResturant;
        this.orderFoods=(ArrayList<Food>) user.cart.clone();
        this.orderFoodsIDs=new ArrayList<>();
        this.addFoodIDs(user);
        this.orderUser=user;
        this.isSent=false;
        this.totalCost=this.setTotalCost();
        this.time=this.setTimeInStringForm();
    }
    void addFoodIDs(User user){
        for (Food f : user.cart) this.orderFoodsIDs.add(f.ID);
    }
    int setTotalCost(){
        int cost=0;
        for (Food f : this.orderFoods) {
            cost += f.price;
            cost-=(f.discount*f.price/100);
        }
        return cost;
    }
    @Override
    public String toString(){
        String s="";
        s+="Order ID: "+this.ID+"    Order Restaurant Name: "+this.orderRestaurant.name+"\n";
        s+="Order Foods:\n";
        for(Food f : this.orderFoods){
            s+="ID: "+f.ID+"    name: "+f.name+"    price: "+f.price+"\n";
        }
        s+="total cost: "+this.totalCost;
        return  s;
    }
    // checked
    void showOrderDetailsForRestaurant(){
        System.out.println("Order ID:" + this.ID);
        for(int i=0;i<this.orderFoods.size();i++){
            System.out.println(this.orderFoods.get(i).toString());
        }
    }
    String setTimeInStringForm(){
        DateFormat dateFormat = new SimpleDateFormat(" yyyy/MM/dd    HH:mm:ss");
        Date date = new Date();
        String s=dateFormat.format(date).toString();
        return s;
    }

}
