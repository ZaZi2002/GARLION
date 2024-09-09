import java.util.ArrayList;

public class Rating {
    double nowRate;
    ArrayList<User> usersRated;
    ArrayList<String> usersRatedNames;
    ArrayList<Double> usersRatingAmount;
    Rating(){
        this.nowRate=0;
        this.usersRated=new ArrayList<>();
        this.usersRatedNames=new ArrayList<>();
        this.usersRatingAmount=new ArrayList<>();
    }
    // checked
    void newRating(User user, double rate){
        this.nowRate=this.updateNowRate(rate);
        this.usersRated.add(user);
        this.usersRatedNames.add(user.username);
        this.usersRatingAmount.add(rate);
    }
    // checked
    void editRating(User user, double rate){
        int index=this.usersRatedNames.indexOf(user.username);
        double preRate=this.usersRatingAmount.get(index);
        double x=this.nowRate*(this.usersRated.size());
        x+=(rate-preRate);
        x/=this.usersRated.size();
        this.nowRate=x;
    }
    // checked
    double updateNowRate(double n){
        double x=this.nowRate*this.usersRated.size();
        x+=n;
        x/=(double) (this.usersRated.size()+1);
        return x;

    }
    // checked
    void showAllRatingsForAdmin(){
        String s="";
        for (int i = 0; i < this.usersRated.size(); i++) {
            s="username: "+this.usersRatedNames.get(i)+" -> rate: "+this.usersRatingAmount.get(i);
            System.out.println(s);
        }
    }
    // checked
    @Override
    public String toString(){
        String s="";
        String roundedNowRate = String.format("%.1f", this.nowRate);
        s+="rating: "+roundedNowRate+"/ 5 "+"-> "+this.usersRated.size()+" users rated";
        return s;
    }
}
