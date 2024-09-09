package com.example.garlion_phase_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;

public class Admin {

    // checked
    String username;
    String password;
    String securityWord;
    Restaurant nowRestaurant = null;
    Food nowFood = null;
    ArrayList<Restaurant> restaurants;
    ArrayList<Integer> restaurantIDs;

    // checked
    Admin(String username, String password){
        if(Admin.AdminRegisterChecker(username,password)) {
            this.username = username;
            this.password = password;
            this.restaurants = new ArrayList<>();
            this.restaurantIDs = new ArrayList<>();
        }
    }

    // checked
    static boolean AdminRegisterChecker(String username,String password){
        boolean b = false;
        if(!username.matches("^\\d*[a-zA-Z][a-zA-Z0-9]*$")){
            System.out.println("Username must be numbers or letters and should have at least one letter!");
        }
        else if(Garlion.AdminsNames.contains(username))
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
    // checked
    void addRestaurant(Matcher matcher){
        String name = matcher.group("name");
        String location = matcher.group("location");
        String [] s1 = matcher.group("types").split("\\s+");
        ArrayList<String> types = new ArrayList<>(Arrays.asList(s1));
        Restaurant restaurant = new Restaurant(name,types,Integer.parseInt(location));
        int ID = Garlion.AllRestaurants.size()+1;
        restaurant.ID = ID;
        restaurant.owner = this;
        this.restaurants.add(restaurant);
        this.restaurantIDs.add(ID);
        Garlion.AllRestaurants.add(restaurant);
        System.out.println("Restaurant with name " + name + " and ID " + ID + " added successfully!");
    }
    // checked
    boolean selectRestaurant(int ID){
        boolean b = false;
        if(!this.restaurantIDs.contains(ID))
            System.out.println("Restaurant with the given ID doesn't exist!");
        else {
            int n = 0;
            for (int i=0;i<this.restaurants.size();i++) {
                if (this.restaurants.get(i).ID == ID) {
                    n = this.restaurants.indexOf(this.restaurants.get(i));
                    break;
                }
            }
            this.nowRestaurant = this.restaurants.get(n);
            b = true;
        }
        return b;
    }
    // checked
    void showRestaurantType(){
        System.out.println(this.nowRestaurant.showFoodType());
    }
    //checked
    void showRestaurantsForAdmin(){
        if (this.restaurants != null)
            Collections.sort(this.restaurants, Restaurant.restaurantComparator);
        System.out.println("Your restaurants list :");
        if (this.restaurants != null)
            for(int i=0;i<this.restaurants.size();i++){
                System.out.println("Restaurant Name: " + this.restaurants.get(i).name+"   Restaurant ID: " + this.restaurants.get(i).ID);
            }
    }
    // checked
    boolean editRestaurantType(ArrayList<String> type){
        boolean b=true;
        if(!this.nowRestaurant.activeOrders.isEmpty()){
            System.out.println("There are active orders in your restaurant. Please first complete them and then try again!");
            b = false;
        }
        else{
            this.editRestaurantTypeConfirming(type);
        }
        return b;
    }
    // checked
    void editRestaurantTypeConfirming(ArrayList<String> type){
        this.nowRestaurant.type.clear();
        this.nowRestaurant.type=(ArrayList<String>) type.clone();
        this.nowRestaurant.foods.clear();
        this.nowRestaurant.menu.clear();
        this.nowRestaurant.foodIDs.clear();
        System.out.println("FoodType edited successfully.");
    }
    // checked
    void showRestaurantFoodsForAdmin(){
        this.nowRestaurant.showFoods();
    }
    // checked
    void addFood(Matcher matcher){
        String name = matcher.group("name");
        int price = Integer.parseInt(matcher.group("price"));
        Food food = new Food(name,price,this.nowRestaurant);
        Garlion.AllFoods.add(food);
        this.nowRestaurant.newFood(food);
        System.out.println("Food with ID " + food.ID + " added to your restaurant successfully!");
    }
    // checked
    boolean deleteRestaurantFood(Matcher matcher){
        int ID = Integer.parseInt(matcher.group("ID"));
        boolean b = false;
        if(!this.nowRestaurant.foodIDs.contains(ID))
            System.out.println("Food with the given ID doesn't exist in restaurant!");
        else if(this.nowRestaurant.isInActiveOrders(ID)){
            System.out.println("There are active orders in your restaurant. Please first complete them and then try again!");
        }
        else{
            this.deleteRestaurantFoodConfirming(matcher);
            b = true;
        }
        return b;
    }
    // checked
    void deleteRestaurantFoodConfirming(Matcher matcher){
        int ID = Integer.parseInt(matcher.group("ID"));
        this.nowRestaurant.deleteFood(ID);
    }
    // checked
    void editFoodName(Matcher matcher){
        int ID = Integer.parseInt(matcher.group("ID"));
        String newName = matcher.group("newName");
        if(!this.nowRestaurant.foodIDs.contains(ID))
            System.out.println("Food with the given ID doesn't exist in the restaurant!");
        else if(this.nowRestaurant.isInActiveOrders(ID)){
            System.out.println("There are some active orders of this food in the restaurant. please complete them and then try again!");
        }
        else{
            this.nowRestaurant.newFoodName(ID,newName);
            System.out.println("Food name edited successfully!");
        }
    }
    // checked
    void editFoodPrice(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        int newPrice=Integer.parseInt(matcher.group("newPrice"));
        if(!this.nowRestaurant.foodIDs.contains(ID))
            System.out.println("Food with the given ID doesn't exist in the restaurant!");
        else if(this.nowRestaurant.isInActiveOrders(ID)){
            System.out.println("There are some active orders of this food in the restaurant. please complete them and then try again!");
        }
        else {
            this.nowRestaurant.newFoodPrice(ID,newPrice);
            System.out.println("Food price edited successfully!");
        }
    }
    // checked
    void deactiveRestaurantFood(Matcher matcher){
        int ID = Integer.parseInt(matcher.group("ID"));
        if(!this.nowRestaurant.foodIDs.contains(ID))
            System.out.println("Food with the given ID doesn't exist in restaurant!");
        else if(this.nowRestaurant.isInActiveOrders(ID)){
            System.out.println("There are active orders in your restaurant. Please first complete them and then try again!");
        }
        else if(this.nowRestaurant.deactiveFood(ID)) {
            System.out.println("Deactivation done successfully!");
        }
    }
    // checked
    void activeRestaurantFood(Matcher matcher){
        int ID = Integer.parseInt(matcher.group("ID"));
        if(!this.nowRestaurant.foodIDs.contains(ID))
            System.out.println("Food with the given ID doesn't exist in restaurant!");
        else if(this.nowRestaurant.isInActiveOrders(ID)){
            System.out.println("There are active orders in your restaurant. Please first complete them and then try again!");
        }
        else if(this.nowRestaurant.activeFood(ID)) {
            System.out.println("Activation done successfully!");
        }
    }
    // checked
    void setDiscountForFood (Matcher matcher){
        int ID = Integer.parseInt(matcher.group("ID"));
        int percent = Integer.parseInt(matcher.group("percent"));
        int hour = Integer.parseInt(matcher.group("hour"));
        int minute = Integer.parseInt(matcher.group("minute"));
        int second = Integer.parseInt(matcher.group("second"));
        long time = Garlion.setInputTimeToSecond(hour,minute,second);
        if(!this.nowRestaurant.foodIDs.contains(ID))
            System.out.println("Food with the given ID doesn't exist in the restaurant!");
        else if(this.nowRestaurant.isInActiveOrders(ID)){
            System.out.println("There are active orders in your restaurant. Please first complete them and then try again!");
        }
        else if(percent > 50)
            System.out.println("Discount percent should be smaller than 50!");
        else if(this.nowRestaurant.discountFood(ID,percent,time)) {
            System.out.println("Discount activated successfully!");
        }
    }
    // checked
    void selectFood(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        this.nowFood=this.nowRestaurant.foods.get(ID);
        System.out.println("Food selected successfully!");
    }
    // checked
    void diSplayFoodRating() {
        if(this.nowFood.rating.usersRated.size()==0)
            System.out.println("This food has no rate yet!");
        else{
            System.out.println(this.nowFood.rating.toString());
        }
    }
    // checked
    void diSplayFoodAllRatings() {
        if(this.nowFood.rating.usersRated.size()==0) System.out.println("This food has no rate yet!");
        else{ this.nowFood.rating.showAllRatingsForAdmin();}
    }
    // checked
    void displayCommentsForFood() {
        System.out.println("comments:");
        for (Comment comment : this.nowFood.comments) {
            System.out.println("Comment writer username: "+comment.commentOwner.username+"  *  comment ID: " + comment.ID + " -> " + comment.content);
            comment.getCommentResponse();
        }
    }
    // checked
    boolean responseForFoodErrors(int ID){
        boolean b=false;
        if(!this.nowFood.commentsIDs.contains(ID))
            System.out.println("There is no comment with the given ID for this food of your restaurant!");
        else{
            int index=this.nowFood.commentsIDs.indexOf(ID);
            if(this.nowFood.comments.get(index).response!=null)
                System.out.println("You can only response to a comment once!");
            else{
                b=true;
                System.out.println("Your response content for comment with ID "+ID+":");
            }
        }
        return b;
    }
    // checked
    boolean editResponseForFoodErrors(int ID){
        boolean b=false;
        if(!this.nowFood.commentsIDs.contains(ID))
            System.out.println("There is no comment with the given ID for this food of your restaurant!");
        else{
            int index=this.nowFood.commentsIDs.indexOf(ID);
            if(this.nowFood.comments.get(index).response!=null)
                System.out.println("You have not responded this comment yet!");
            else{
                b=true;
                System.out.println("Your new response content for comment with ID "+ID+":");
            }
        }
        return b;
    }
    // checked
    void responseForFoodConfirming(int ID,String response){
        int index=this.nowFood.commentsIDs.indexOf(ID);
        this.nowFood.comments.get(index).setResponse(response);
        System.out.println("Your response registered for this comment successfully.");
    }
    // checked
    void showOpenOrders(){
        this.nowRestaurant.displayActiveOrders();
    }
    // checked
    void editOrderStatus(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        this.nowRestaurant.sendingOrder(ID);
    }
    // checked
    void showOrderHistory(){
        this.nowRestaurant.displayAllOrders();
    }

    void restaurantShowLocation(){
        System.out.println("Restaurant's location is " + this.nowRestaurant.location + " !");
    }

    void restaurantEditLocation(Matcher matcher){
        this.nowRestaurant.location = Integer.parseInt(matcher.group("location"));
    }

}
