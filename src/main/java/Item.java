import java.util.HashMap;

/**
 * Created by roberthitchens3 on 2/22/16.
 */
public class Item {
    private String name;
    private String type;
    private String expiration;
    private int timesSeen;
    private HashMap<String, Integer> prices;
    private String price;
    public Item(String name, String type, String expiration, String price){
        this.name = name;
        this.type = type;
        this.expiration = expiration;
        this.price =price;
        timesSeen = 1;
        prices = new HashMap<String, Integer>();
    }
    public String getName(){return name;}
    public String getType(){return type;}
    public String getExpiration(){return expiration;}
    public int getTimesSeen(){return timesSeen;}
    public String getPrice(){return price;}
    public HashMap<String, Integer> getPrices(){return prices;}
    public void incrementTimesSeen(){timesSeen++;}
    public void addPrice(String price){
        if(prices.containsKey(price)){
            prices.put(price, prices.get(price)+1);
        }else{
            prices.put(price, 1);
        }
    }
}
