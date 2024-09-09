import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InputCommands {
    // LoginPanel Commands
    ADD_ADMIN("\\s*(ADD)\\s+(ADMIN)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    ADD_USER("\\s*(ADD)\\s+(USER)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s+(?<location>\\S+)\\s*"),
    ADD_DELIVERY("\\s*(ADD)\\s+(DELIVERY)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s+(?<location>\\S+)\\s*"),
    LOGIN_ADMIN("\\s*(LOGIN)\\s+(ADMIN)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    LOGIN_USER("\\s*(LOGIN)\\s+(USER)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    LOGIN_DELIVERY("\\s*(LOGIN)\\s+(USER)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    FORGET_PASSWORD_USER("\\s*(FORGOT)\\s+(USER)\\s+(PASSWORD)\\s+(?<username>\\S+)\\s*"),
    FORGET_PASSWORD_ADMIN("\\s*(FORGOT)\\s+(ADMIN)\\s+(PASSWORD)\\s+(?<username>\\S+)\\s*"),
    FORGET_PASSWORD_DELIVERY("\\s*(FORGOT)\\s+(ADMIN)\\s+(PASSWORD)\\s+(?<username>\\S+)\\s*"),

    // AdminPanel Commands
    SELECT_RESTAURANT("\\s*(SELECT)\\s+(?<ID>\\d+)\\s*"),
    ADD_RESTAURANT("\\s*(ADD)\\s+(RESTAURANT)\\s+(?<name>\\S+)\\s+(?<location>\\S+)\\s+(?<types>[a-zA-Z][a-zA-Z ]+[a-zA-Z]$)\\s*"),

    // RestaurantPanel Commands
    EDIT_RESTAURANT_TYPE("\\s*(EDIT)\\s+(FOODTYPE)\\s+(?<types>[a-zA-Z][a-zA-Z ]+[a-zA-Z]$)\\s*"),
    EDIT_FOOD_NAME("\\s*(EDIT)\\s+(FOOD)\\s+(?<ID>\\d+)\\s+(NAME)\\s+(?<newName>\\S+)\\s*"),
    EDIT_FOOD_PRICE("\\s*(EDIT)\\s+(FOOD)\\s+(?<ID>\\d+)\\s+(PRICE)\\s+(?<newPrice>\\d+)\\s*"),
    ADD_FOOD("\\s*(ADD)\\s+(FOOD)\\s+(?<name>\\S+)\\s+(?<price>\\d+)\\s*"),
    DELETE_FOOD("\\s*(DELETE)\\s+(FOOD)\\s+(?<ID>\\d+)\\s*"),
    DEACTIVE_FOOD("\\s*(DEACTIVE)\\s+(FOOD)\\s+(?<ID>\\d+)\\s*"),
    ACTIVE_FOOD("\\s*(ACTIVE)\\s+(FOOD)\\s+(?<ID>\\d+)\\s*"),
    SELECT_FOOD("\\s*(SELECT)\\s+(FOOD)\\s+(?<ID>\\d+)\\s*"),
    DISCOUNT_FOOD("\\s*(DISCOUNT)\\s+(FOOD)\\s+(?<ID>\\d+)\\s+(?<percent>\\d+)\\s+(?<hour>\\d+):(?<minute>\\d+):(?<second>\\d+)\\s*"),
    EDIT_LOCATION("\\s*(EDIT)\\s+(LOCATION)\\s+(?<location>\\d+)\\s*"),

    FIND_PATH("\\s*(FIND)\\s+(BEST)\\s+(PATH)\\s+(?<ID>\\d+)\\s*"),


    SEARCH_RESTAURANT_USER("\\s*(SEARCH)\\s+(RESTAURANT)\\s+(?<name>\\S+)\\s*"),
    CHARGE_ACCOUNT("\\s*(CHARGE)\\s+(ACCOUNT)\\s+(?<amount>\\d+)\\s*"),

    SEARCH_FOOD_USER("\\s*(SEARCH)\\s+(FOOD)\\s+(?<name>\\S+)\\s*"),

    EDIT_COMMENT_USER("\\s*(EDIT)\\s+(COMMENT)\\s+(?<ID>\\d+)\\s*"),

    SELECT_ORDER("\\s*(SELECT)\\s+(ORDER)\\s+(?<ID>\\d+)\\s*"),

    SUBMIT_RATING("\\s*(SUBMIT)\\s+(RATING)\\s+(?<amount>[0-9]{1,13}(\\.[0-9]*)?)\\s*"),
    EDIT_RATING("\\s*(EDIT)\\s+(RATING)\\s+(?<amount>[0-9]{1,13}(\\.[0-9]*)?)\\s*"),
    EDIT_ORDER("\\s*(EDIT)\\s+(ORDER)\\s+(?<ID>\\d+)\\s+(STATUS)\\s+(SENT)\\s*"),
    ADD_RESPONSE("\\s*(ADD)\\s+(NEW)\\s+(RESPONSE)\\s+(?<ID>\\d+)\\s+(?<MESSAGE>\\d+)\\s*"),
    EDIT_RESPONSE("\\s*(EDIT)\\s+(RESPONSE)\\s+(?<ID>\\d+)\\s+(?<MESSAGE>\\d+)\\s*"),

    //REMOVE_ADMIN("\\s*REMOVE\\s+ADMIN\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    //REMOVE_USER("\\s*REMOVE\\s+USER\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    ;
    public String regex;

    InputCommands(String regex){
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , InputCommands command){
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
