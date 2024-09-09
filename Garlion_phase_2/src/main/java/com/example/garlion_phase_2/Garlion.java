package com.example.garlion_phase_2;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

public class Garlion {

    public static User nowUser = null;
    public static Admin nowAdmin = null;
    public static Delivery nowDelivery = null;
    public static ArrayList<Admin> Admins = new ArrayList<>();
    public static ArrayList<User> Users = new ArrayList<>();
    public static ArrayList<Delivery> Deliveries = new ArrayList<>();
    public static ArrayList<Restaurant> AllRestaurants = new ArrayList<>();
    public static ArrayList<Food> AllFoods = new ArrayList<>();
    public static ArrayList<Comment> AllComments = new ArrayList<>();
    public static ArrayList<Order> AllOrders = new ArrayList<>();
    public static ArrayList<String> AdminsNames = new ArrayList<>();
    public static ArrayList<String> UsersNames = new ArrayList<>();
    public static ArrayList<String> DeliveryNames = new ArrayList<>();

    // checked
    public static boolean AddUser(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(User.UserRegisterChecker(username,password)) {
            User user = new User(username,password);
            Garlion.Users.add(user);
            Garlion.UsersNames.add(username);
            Garlion.nowUser = user;
            Garlion.nowAdmin = null;
            Garlion.nowDelivery = null;
            return true;
        }
        else{
            return false;
        }
    }
    // checked
    public static boolean AddAdmin(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(Admin.AdminRegisterChecker(username,password)) {
            Admin admin = new Admin(username, password);
            Garlion.Admins.add(admin);
            Garlion.AdminsNames.add(username);
            Garlion.nowUser = null;
            Garlion.nowAdmin = admin;
            Garlion.nowDelivery = null;
            return true;
        }
        else{
            return false;
        }
    }
    // checked
    public static boolean AddDelivery(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(Delivery.DeliveryRegisterChecker(username,password)) {
            Delivery delivery = new Delivery(username, password);
            Garlion.Deliveries.add(delivery);
            Garlion.DeliveryNames.add(username);
            Garlion.nowUser = null;
            Garlion.nowAdmin = null;
            Garlion.nowDelivery = delivery;
            return true;
        }
        else{
            return false;
        }
    }
    // checked
    public static void setSecurityWordForUser(String username,String word){
        int id = Garlion.UsersNames.indexOf(username);
        Garlion.Users.get(id).securityWord = word;
        System.out.println("User account created successfully!");
    }
    // checked
    public static void setSecurityWordForAdmin(String username,String word){
        int id = Garlion.AdminsNames.indexOf(username);
        Garlion.Admins.get(id).securityWord = word;
        System.out.println("Admin account created successfully!");
    }
    // checked
    public static void setSecurityWordForDelivery(String username,String word){
        int id = Garlion.DeliveryNames.indexOf(username);
        Garlion.Deliveries.get(id).securityWord = word;
        System.out.println("Delivery account created successfully!");
    }
    // checked
    public static boolean passwordChecker(String password) {
        boolean b = false;
        if (password.length() < 8)
            System.out.println("Password must contain at least 8 characters!");
        else if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$"))
        {
            if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$"))
                System.out.println("Password must contain at least one number.");
            else if (password.contains("//s"))
                System.out.println("White spaces are not allowed in password!");
            else if (password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,20}$"))
                System.out.println("Password must contain at least one lower case letter!");
            else if (password.matches("^(?=.*[a-z])(?=.*[0-9])(?=\\S+$).{8,20}$"))
                System.out.println("Password must contain at least one upper case letter!");
            b = false;
        }
        else {
            b = true;
        }
        return b;
    }
    // checked
    public static boolean LoginUser(Matcher matcher){
        boolean b = false;
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(!Garlion.UsersNames.contains(username))
            System.out.println("Username doesn't exist!");
        else if(!Garlion.Users.get(Garlion.UsersNames.indexOf(username)).password.equals(password))
            System.out.println("Password is wrong!");
        else {
            User user = Garlion.Users.get(Garlion.UsersNames.indexOf(username));
            b = true;
            Garlion.nowUser = user;
            Garlion.nowAdmin = null;
            Garlion.nowDelivery = null;
        }
        return b;
    }
    // checked
    public static boolean LoginAdmin(Matcher matcher){
        boolean b = false;
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(!Garlion.AdminsNames.contains(username))
            System.out.println("Username doesn't exist!");
        else if(!Garlion.Admins.get(Garlion.AdminsNames.indexOf(username)).password.equals(password))
            System.out.println("Password is wrong!");
        else {
            Admin admin = Garlion.Admins.get(Garlion.AdminsNames.indexOf(username));
            b = true;
            Garlion.nowAdmin = admin;
            Garlion.nowUser = null;
            Garlion.nowDelivery = null;
        }
        return b;
    }
    // checked
    public static boolean LoginDelivery(Matcher matcher){
        boolean b = false;
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(!Garlion.DeliveryNames.contains(username))
            System.out.println("Username doesn't exist!");
        else if(!Garlion.Deliveries.get(Garlion.DeliveryNames.indexOf(username)).password.equals(password))
            System.out.println("Password is wrong!");
        else {
            Delivery delivery = Garlion.Deliveries.get(Garlion.DeliveryNames.indexOf(username));
            b = true;
            Garlion.nowDelivery = delivery;
            Garlion.nowUser = null;
            Garlion.nowAdmin = null;
        }
        return b;
    }
    // checked
    public static void Logout(){
        if(Garlion.nowAdmin==null && Garlion.nowUser==null) System.out.println("Invalid command!");
        else if(Garlion.nowAdmin==null){
            Garlion.nowUser.nowUserResturant=null;
            Garlion.nowUser.nowUserFood=null;
            Garlion.nowUser=null;
            System.out.println("Logged out successfully!");
        }
        else{
            Garlion.nowAdmin.nowFood=null;
            Garlion.nowAdmin.nowRestaurant=null;
            Garlion.nowAdmin=null;
            System.out.println("Logged out successfully.");
        }
    }
    // checked
    public static void restorePassForUser(String username,String word){
        int id = Garlion.UsersNames.indexOf(username);
        Garlion.Users.get(id).forgotPassword(word);
    }
    // checked
    public static void restorePassForAdmin(String username,String word){
        int id= Garlion.AdminsNames.indexOf(username);
        Garlion.Admins.get(id).forgotPassword(word);
    }
    // checked
    public static void restorePassForDelivery(String username,String word){
        int id= Garlion.DeliveryNames.indexOf(username);
        Garlion.Deliveries.get(id).forgotPassword(word);
    }
    // checked
    public static long setInputTimeToSecond(int hour,int minute,int second){
        long time = 0;
        time += (hour*3600) + (minute*60) + (second);
        return time;
    }
    // checked
    public static String randomNumbersForLogin(){
        Random random = new Random();
        int a1 = random.nextInt(100);
        int a2 = random.nextInt(100);
        System.out.println("What is the following math operation answer? ");
        System.out.println(a1 + " + " + a2 + " = ?");
        return String.valueOf(a1+a2);
    }

}
