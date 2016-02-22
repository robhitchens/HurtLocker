import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by roberthitchens3 on 2/22/16.
 */
public class DataFormatter {
    private ArrayList<String> parsedData;
    private int errorCount;
    private ArrayList<Item> items;
    private String itemPattern = "[:][A-Za-z0-9]+[:@^*%;!]";
    private String pricePattern = "[:][0-9]+\\.[0-9]{2}[:@^*%;!]";

    private Matcher m;
    private String outputData;
    public DataFormatter(ArrayList<String> parsedData, int errorCount){
        this.parsedData = parsedData;
        this.errorCount = errorCount;
        items = new ArrayList<Item>();
        outputData = "";
    }
    public void compilePattern(String pattern, String compareTo){
        Pattern p = Pattern.compile(pattern);
        m = p.matcher(compareTo);
    }
    public String findItems(String data){
        compilePattern(itemPattern, data);
        String result ="";
        if(m.find())
            result = m.group().substring(1, m.group().length()-1);
        return result;
    }
    public String findPrice(String data){
        compilePattern(pricePattern, data);
        String result ="";
        if(m.find())
            result = m.group().substring(1, m.group().length()-1);
        return result;
    }
    public void assembleDataLists(){
        for(int i = 0; i<parsedData.size(); i++){
            String tempName = modifyString(findItems(parsedData.get(i)));
            if(itemListContains(tempName))
                wasSeen(tempName, findPrice(parsedData.get(i)));
            else {
                Item tempit = new Item(tempName);
                tempit.addPrice(findPrice(parsedData.get(i)));
                tempit.incrementTimesSeen();
                items.add(tempit);
            }
        }
    }
    /*public String modifyString(String item){//might need to fix. //this comes after data parsing.
        if(item.contains("0")) item = item.replaceAll("0", "o");
        item = item.toLowerCase();
        item = item.substring(0,1).toUpperCase()+item.substring(1,item.length());
        return item;
    }
    public boolean itemListContains(String itm){
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getName().equals(itm)){
                return true;
            }
        }
        return false;
    }*/
    public void wasSeen(String itm, String price){
        for(Item i : items){
            if(i.getName().equals(itm))
                i.incrementTimesSeen();
            // i.addPrice(price);
            break;
        }
    }
    /*public void includePrice(String item, String price){
        if(!associatedPrices.get(item).contains(price)) associatedPrices.get(item).add(price);
    }*/

    public void outputPrep(){
        assembleDataLists();
        StringBuilder builder = new StringBuilder();
        for(Item i : items){
            String line = String.format("name:%1$10s seen: %2d times",i.getName(), i.getTimesSeen());
            builder.append(line +"\n");
            line = String.format("%1$-10s %1$10s", "=============");
            builder.append(line+"\n");
            for(String s : i.getPrices().keySet()){
                line = String.format("price:%1$-10s seen: %2$10d times",s, i.getPrices().get(s));
                builder.append(line+"\n");
                line = String.format("%1$-10s %1$10s", "-------------");
                builder.append(line+"\n");
            }

        }
        builder.append(String.format("%1$-10s seen: %2$10d times", "Errors",errorCount));
        outputData = builder.toString();
    }
    public String getOutputData(){
        outputPrep();
        return outputData;
    }
}
