import java.util.ArrayList;
import java.util.Comparator;


public class Food {
    String name;
    boolean isActive;
    int price;
    Rating rating;
    //ArrayList<Integer> ratings;
    ArrayList<Comment> comments;
    ArrayList<Integer> commentsIDs;
    int discount;
    Timed<Boolean> hasDiscount;
    int ID;
    Restaurant resturant;

    Food(String name, int price, Restaurant resturant){
        this.name = name;
        this.price = price;
        this.resturant = resturant;
        int id = Garlion.AllFoods.size()+1;
        this.ID= id;
        this.discount = 0;
        this.isActive = true;
        // this.ratings=new ArrayList<>();
        this.comments = new ArrayList<>();
        this.commentsIDs = new ArrayList<>();
        this.rating = new Rating();
    }
    void editFoodName(String newName){this.name=newName;}
    void editFoodPrice(int newPrice){this.price=newPrice;}
    void deactiveFood(){this.isActive=false;}
    void activeFood(){this.isActive=true;}

    // checked
    @Override
    public String toString(){
        String s1 ="";
        if(this.isActive){
            s1 ="Active";
        }
        else{
            s1="Deactive";
        }
        String s = "ID: " + this.ID + "    name: " + this.name+"    price: " + this.price+"    discount: " + this.discount + "%    " + s1;
        return s;
    }

    public String toStringForUser(){
        String s= "ID: "+this.ID+"    name: "+this.name+"    price: "+this.price+"    discount: "+this.discount+"%";
        return s;
    }

    // checked
    public static Comparator<Food> foodComparator = new Comparator<Food>() {
        public int compare(Food f1, Food f2) {
            return f1.ID-f2.ID;
        }
    };
}
