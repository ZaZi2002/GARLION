
import java.util.ArrayList;
import java.util.regex.Matcher;

public class User {
    String username;
    String securityWord;
    String password;
    Food nowUserFood;
    Restaurant nowUserResturant;
    ArrayList<Food> cart;
    ArrayList<Order> orders;
    int balance;
    int location;

    User(String username, String password, int location){
        this.username=username;
        this.password=password;
        this.location=location;
        this.cart = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.balance = 0;
    }

    // checked
    static boolean UserRegisterChecker(String username,String password){
        boolean b = false;
        if (!username.matches("^\\d*[a-zA-Z][a-zA-Z0-9]*$")) {
            System.out.println("Username must be only numbers or letters and should have at least one letter!");
        }
        else if (Garlion.UsersNames.contains(username))
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
    void showRestaurantsForUser(){
        System.out.println("Restaurants :");
        for(Restaurant r : Garlion.AllRestaurants){
            System.out.println("Restaurant Name: "+r.name+"  *  Restaurant ID: "+r.ID+"  * type: "+r.type.toString());
        }
    }
    // checked
    void searchRestaurantByName(Matcher matcher) {
        String name = matcher.group("name");
        boolean b = false;
        for (Restaurant restaurant : Garlion.AllRestaurants) {
            if (restaurant.name.equals(name)) {
                System.out.println("Restaurant name: " + name + "  *  ID: " + restaurant.ID + "  *  foodType: " + restaurant.type.toString());
                b = true;
            }
        }
        if (!b) System.out.println("Restaurant with the given name doesn't exist!");
    }
    // checked
    boolean selectRestaurant(Matcher matcher) {
        int ID = Integer.parseInt(matcher.group("ID"));
        boolean b = false;
        if (Garlion.AllRestaurants.size() < ID) System.out.println("Restaurant with the given ID doesn't exist!");
        else {
            this.nowUserResturant = Garlion.AllRestaurants.get(ID - 1);
            b = true;
        }
        return b;
    }
    // checked
    void showFoodsForUser(){
        System.out.println("foods :");
        for(Food f : this.nowUserResturant.menu){System.out.println(f.toStringForUser());}
    }
    // checked
    void searchFoodByName(Matcher matcher) {
        String name = matcher.group("name");
        boolean b = false;
        for (Food food : this.nowUserResturant.menu) {
            if (food.name.equals(name)) {
                System.out.println("Food name: " + name + "  *  ID: " + food.ID + "  *  Price: " + food.price);
                b = true;
            }
        }
        if (!b) System.out.println("Food with the given name doesn't exist.");
    }
    // checked
    boolean selectFood(Matcher matcher) {
        int ID = Integer.parseInt(matcher.group("ID"));
        int n = this.nowUserResturant.getIndexOfFoodInMenuForUserByID(ID);
        boolean b = false;
        if (n == -1) System.out.println("Food with the given ID doesn't exist in Restaurant!");
        else {
            this.nowUserFood = this.nowUserResturant.menu.get(n);
            b = true;
        }
        return b;
    }
    // checked
    void chargeAccount(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        this.balance += amount;
        System.out.println("Account charged successfully!");
    }
    // checked
    void showAccountCharge() {
        System.out.println("balance: " + this.balance);
    }
    // checked
    void addCommentForFood(String content) {
        Comment comment = new Comment(content, this);
        Garlion.AllComments.add(comment);
        this.nowUserFood.comments.add(comment);
        this.nowUserFood.commentsIDs.add(comment.ID);
        System.out.println("Comment with ID " + comment.ID + " added for this food successfully!");
    }
    // checked
    boolean editCommentForFoodErrors(int ID) {
        boolean b = true;
        if (!this.nowUserFood.commentsIDs.contains(ID))
            System.out.println("Comment with the given ID doesn't exist for this food!");
        else {
            int index = this.nowUserFood.commentsIDs.indexOf(ID);
            if (this.nowUserFood.comments.get(index).commentOwner != this) {
                System.out.println("This comment is not yours.you can only edit your comments!");
            } else {
                b = false;
            }
        }
        return b;
    }
    // checked
    void editCommentForFoodConfirming(int ID, String newComment) {
        int index = this.nowUserFood.commentsIDs.indexOf(ID);
        this.nowUserFood.comments.get(index).editContent(newComment);
    }
    // checked
    void displayCommentsForFood() {
        System.out.println("Comments:");
        for (Comment comment : this.nowUserFood.comments) {
            System.out.println("comment ID: " + comment.ID + " -> " + comment.content);
            comment.getCommentResponse();
        }
    }
    // checked
    void addCommentForRestaurant(String content) {
        Comment comment = new Comment(content, this);
        Garlion.AllComments.add(comment);
        this.nowUserResturant.comments.add(comment);
        this.nowUserResturant.commentsIDs.add(comment.ID);
        System.out.println("comment with ID " + comment.ID + " added for this restaurant successfully.");
    }
    // checked
    boolean editCommentForRestaurantErrors(int ID) {
        boolean b = true;
        if (!this.nowUserResturant.commentsIDs.contains(ID))
            System.out.println("Comment with the given ID doesn't exist for this restaurant!");
        else {
            int index = this.nowUserResturant.commentsIDs.indexOf(ID);
            if (this.nowUserResturant.comments.get(index).commentOwner != this) {
                System.out.println("This comment is not yours.you can only edit your comments!");
            } else {
                b = false;
            }
        }
        return b;
    }
    // checked
    void editCommentForRestaurantConfirming(int ID, String newComment) {
        int index = this.nowUserResturant.commentsIDs.indexOf(ID);
        this.nowUserResturant.comments.get(index).editContent(newComment);
    }
    // checked
    void displayCommentsForRestaurant() {
        System.out.println("Comments:");
        for (Comment comment : this.nowUserResturant.comments) {
            System.out.println("comment ID: " + comment.ID + " -> " + comment.content);
            comment.getCommentResponse();
        }
    }
    // checked
    void addFoodToCart() {
        this.cart.add(this.nowUserFood);
        System.out.println("this food added to your cart successfully.");
    }
    // checked
    void displayCartStatus() {
        if (this.cart.size() == 0) System.out.println("There is no food in your cart!");
        else {
            System.out.println("Your cart status:");
            for (Food food : this.cart) {
                System.out.println("Food ID: " + food.ID + "    name: " + food.name + "    price: " + food.price+"    discount: "+food.discount+"%");
            }
            int discount = discountOffer();
            System.out.println("You have a special offer on your next order up to " + discount + " amount of charge!!!");
        }
    }
    // checked
    void confirmOrder() {
        Order order = new Order(this);
        Garlion.AllOrders.add(order);
        int discount = discountOffer();
        if(this.balance + discount <order.totalCost) System.out.println("Your balance is not enough.");
        else {
            if (order.totalCost - discount > 0)
                this.balance-=(order.totalCost-discount);
            this.orders.add(order);
            this.nowUserResturant.activeOrders.add(order);
            this.nowUserResturant.activeOrdersIDs.add(order.ID);
            this.cart.clear();
            System.out.println("Order confirmed successfully.");
        }
    }
    void showEstimatedTime(){
        int loc1 = this.location;
        int loc2 = this.nowUserResturant.location;
        int distance = DijkstraAlgorithm.distance(loc1,loc2);
        double time = distance/10;
        System.out.println("Estimated delivery time is " + time + " minuets");
    }
    // checked
    void showAllOrderHistory() {
        if (this.orders.size() == 0) System.out.println("You didn't have any orders until now;");
        else {
            for (Order order : this.orders) {
                System.out.println("Order ID: " + order.ID + "    date & time: " + order.time);
            }
        }
    }
    // checked
    void showSelectedOrderInformation(Matcher matcher) {
        int ID = Integer.parseInt(matcher.group("ID"));
        int index = this.getOrderIndexByID(ID);
        if (index == -1) System.out.println("There is no order with the given ID.");
        else {
            System.out.println(this.orders.get(index).toString());
        }
    }
    // checked
    int getOrderIndexByID(int ID) {
        int n = -1;
        for (Order order : this.orders) {
            if (order.ID == ID) {
                n = this.orders.indexOf(order);
                break;
            }
        }
        return n;
    }
    // checked
    void displayFoodRating() {
        if(this.nowUserFood.rating.usersRated.size()==0) System.out.println("No one rate this food yet.");
        else{System.out.println(this.nowUserFood.rating.toString());}
    }
    // checked
    void submitFoodRating(Matcher matcher) {
        if(this.nowUserFood.rating.usersRated.contains(this)) System.out.println("You have already rated this food before.");
        else {
            double amount = Double.parseDouble(matcher.group("amount"));
            this.nowUserFood.rating.newRating(this, amount);
            System.out.println("you rate this food successfully.");
        }
    }
    // checked
    void editFoodRating(Matcher matcher) {
        if(!this.nowUserFood.rating.usersRatedNames.contains(this.username)) System.out.println("You have not rated this food before.");
        else {
            double amount = Double.parseDouble(matcher.group("amount"));
            this.nowUserFood.rating.editRating(this, amount);
            System.out.println("Your rate edited successfully..");
        }
    }
    // checked
    void submitRestaurantRating(Matcher matcher) {
        if(this.nowUserResturant.rating.usersRated.contains(this)) System.out.println("You have already rated this restaurant before.");
        else {
            double amount = Double.parseDouble(matcher.group("amount"));
            this.nowUserResturant.rating.newRating(this, amount);
            System.out.println("You rated this restaurant successfully.");
        }
    }
    // checked
    void editRestaurantRating(Matcher matcher) {
        if(!this.nowUserResturant.rating.usersRatedNames.contains(this.username)) System.out.println("You have not rated this restaurant before.");
        else {
            double amount = Double.parseDouble(matcher.group("amount"));
            this.nowUserResturant.rating.editRating(this, amount);
            System.out.println("Your rate edited successfully.");
        }
    }
    // checked
    void displayRestaurantRating() {
        if(this.nowUserResturant.rating.usersRated.size()==0)
            System.out.println("No one rate this restaurant yet.");
        else{
            System.out.println(this.nowUserResturant.rating.toString());
        }
    }
    // checked
    int discountOffer(){
        int discount=0;
        if (this.orders.size() >= 4) {
            if (this.orders.size() % 4 == 0) {
                for (int i = this.orders.size() - 1; i >= this.orders.size() - 4; i--) {
                    discount += this.orders.get(i).totalCost;
                }
                discount = (int) Math.round((double) discount / 10);
            } else if (this.orders.get(this.orders.size() - 1).totalCost >= 400) {
                discount = 50;
            }
        }
        else if (this.orders.get(this.orders.size() - 1).totalCost >= 400) {
            discount = 50;
        }
        return discount;
    }

}

