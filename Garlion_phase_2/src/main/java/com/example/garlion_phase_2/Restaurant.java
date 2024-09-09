package com.example.garlion_phase_2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Restaurant {
    String name;
    int ID;
    Admin owner;
    Rating rating;
    ArrayList<User> Costumers;
    ArrayList<String> type;
    ArrayList<Comment> comments;
    ArrayList<Integer> commentsIDs;
    ArrayList<Food> foods;
    ArrayList<Food> menu;
    ArrayList<Integer> foodIDs;
    ArrayList<Order> activeOrders;
    ArrayList<Integer> activeOrdersIDs;
    ArrayList<Order> allOrders;
    int location;

    // checked
    Restaurant(String name, ArrayList<String> type,int location){
        this.name = name;
        this.foods = new ArrayList<>();
        this.foodIDs = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.activeOrders = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.commentsIDs = new ArrayList<>();
        this.type = type;
        this.allOrders = new ArrayList<>();
        this.activeOrdersIDs = new ArrayList<>();
        this.rating = new Rating();
        this.location = location;
    }
    // checked
    void newFood(Food food){
        this.foods.add(food);
        this.foodIDs.add(food.ID);
        this.menu.add(food);
    }
    // checked
    boolean deactiveFood(int ID){
        int n = 0;
        boolean b = false;
        for(Food f : this.foods){
            if(f.ID == ID){
                if(this.isFoodInActiveOrders(f)){
                    System.out.println("There are active orders in your restaurant. Please first complete them and then try again!");
                    break;
                }
                else if(!f.isActive){
                    System.out.println("This food is already deactive!");
                    break;
                }
                else {
                    n = this.menu.indexOf(f);
                    this.menu.remove(n);
                    f.isActive = false;
                    b = true;
                    break;
                }
            }
        }
        return b;
    }
    // checked
    boolean isFoodInActiveOrders(Food f){
        boolean b = false;
        for(Order order : this.activeOrders){
            if(order.orderFoodsIDs.contains(f.ID)){
                b = true;
                break;
            }
        }
        return b;
    }
    // checked
    boolean activeFood(int ID){
        int n=0;
        boolean b = false;
        for(Food f : this.foods){
            if(f.ID==ID){
                if(f.isActive){
                    System.out.println("This food is already active!");
                    break;
                }
                else {
                    f.isActive = true;
                    this.menu.add(f);
                    b = true;
                    break;
                }
            }
        }
        return b;
    }
    // checked
    void deleteFood(int ID){
        int n = 0;
        int n1 = 0;
        for(Food f : this.menu) {
            if (f.ID == ID) {
                n1 = this.menu.indexOf(f);
                break;
            }
        }
        for(Food f : this.foods){
            if(f.ID == ID){
                int n2 = this.foods.indexOf(f);
                this.foods.remove(f);
                this.menu.remove(n1);
                this.foodIDs.remove(n2);
                break;
            }
        }
    }
    // checked
    void showFoods(){
        Collections.sort(this.foods, Food.foodComparator);
        System.out.println("Foods:");
        for(Food f : this.foods){
            System.out.println(f.toString());}
    }
    // checked
    void newFoodName(int ID,String  newName){
        int n = this.getIndexOfFoodByID(ID);
        this.foods.get(n).name = newName;
    }
    // checked
    void newFoodPrice(int ID,int price){
        int n = this.getIndexOfFoodByID(ID);
        this.foods.get(n).price = price;
    }
    // checked
    boolean isInActiveOrders(int ID){
        boolean b = false;
        for(Food f : this.foods) {
            if (f.ID == ID) {
                if (this.isFoodInActiveOrders(f)) {
                    b = true;
                    return b;
                }
            }
        }

        return b;
    }
    // checked
    boolean discountFood(int ID,int percent, long time){
        int n=this.getIndexOfFoodByID(ID);
        boolean b=false;
        if(this.foods.get(n).hasDiscount==null){
            int t1=(int) (time*1000);
            this.foods.get(n).hasDiscount=new Timed<>(Boolean.TRUE,Boolean.FALSE,time*1000);
            this.foods.get(n).hasDiscount.tick();
            this.foods.get(n).discount=percent;
            this.setDiscountPercentRelatedToTime(this,n,t1);
            b=true;
        }
        else {System.out.println("This food already has an active discount!");}
        return b;
    }
    // checked
    void setDiscountPercentRelatedToTime(Restaurant resturant, int index, int time){
        Timer timer = new Timer(time, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                resturant.foods.get(index).hasDiscount=null;
                resturant.foods.get(index).discount=0;
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.start(); // Go!
    }
    // checked
    int getIndexOfFoodByID(int ID){
        int n=-1;
        for(Food f : this.foods) {
            if (f.ID == ID) {
                n= this.foods.indexOf(f);
                break;
            }
        }
        return n;
    }
    // checked
    int getIndexOfFoodInMenuForUserByID(int ID){
        int n=-1;
        for(Food f : this.menu) {
            if (f.ID == ID) {
                n= this.foods.indexOf(f);
                break;
            }
        }
        return n;
    }
    // checked
    String showFoodType(){
        return this.type.toString();
    }
    // checked
    void displayActiveOrders(){
        System.out.println("Active Orders:");
        for(int i=0;i<activeOrders.size();i++){
            activeOrders.get(i).showOrderDetailsForRestaurant();
        }
    }
    // checked
    void displayAllOrders(){
        System.out.println("All Orders:");
        for(int i=0;i<allOrders.size();i++){
            allOrders.get(i).showOrderDetailsForRestaurant();
        }
    }
    // checked
    void sendingOrder(int ID){
        int index=this.activeOrdersIDs.indexOf(ID);
        this.activeOrders.get(ID).isSent=true;
        this.allOrders.add(this.activeOrders.get(index));
        this.activeOrders.remove(index);
        this.activeOrdersIDs.remove(index);
        System.out.println("The order is sent successfully!");
    }
    //checked
    public static Comparator<Restaurant> restaurantComparator = new Comparator<Restaurant>() {
        public int compare(Restaurant r1, Restaurant r2) {
            if(!r1.name.equals(r2.name)) {
                return r1.name.compareTo(r2.name);
            }
            else{
                return r1.ID-r2.ID;
            }
        }
    };

}
