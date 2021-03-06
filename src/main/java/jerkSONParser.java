import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by roberthitchens3 on 2/22/16.
 */
public class JerkSONParser {
    private String jerkSONData;
    private int errorcount;
    private String pattern;
    private Matcher m;
    private ArrayList<String> workingData;
    private String itemPattern = "[:][A-Za-z0-9]+[:@^*%;!]";
    private String pricePattern = "[:][0-9]+\\.[0-9]{2}[:@^*%;!]";
    private String datePattern = "[:](([0]?[0-9])|([1][0-2]))\\/(([0-2][0-9])|([3][0-1]))\\/([0-9]){4}";
    private String errorPattern = "[A-Za-z]+[:][:@^*;!]";
    private ArrayList<Item> itemlist;
    public JerkSONParser(String data){
        jerkSONData = data;
        errorcount = 0;
        workingData = new ArrayList<String>();
        itemlist = new ArrayList<Item>();
        dataInit();
    }
    public void dataInit(){
        assemblePattern();
        compilePattern(pattern, jerkSONData);
        dataMatcher(m);
        compilePattern(errorPattern, jerkSONData);
        errorMatcher(m);
        parseWorkingDataToItems();
    }

    public void dataMatcher(Matcher m){//refactor
        while(!m.hitEnd()){//
            if(m.find()){
                workingData.add(m.group().substring(0, m.group().length()-2));
            }
        }

    }
    public void errorMatcher(Matcher m){
        while(!m.hitEnd()){
            if(m.find()){
                errorcount++;
            }
        }
    }
    public boolean itemCheckAndAdd(String itemName, Item itm){
        for(Item i : itemlist){
            if(i.getName().equals(itemName)){//refactor
                i.addPrice(itm.getPrice());
                i.incrementTimesSeen();
                return true;
            }
        }
        return false;
    }

    public void parseWorkingDataToItems(){
        for(int i = 0; i < workingData.size();i++){
            String current = workingData.get(i);
            Item temp = new Item(modifyString(findItems(current)), findType(current),findDate(current), findPrice(current));
            if(!itemCheckAndAdd(temp.getName(), temp)) itemlist.add(temp);
        }
    }
    public String modifyString(String item){//might need to fix. //this comes after data parsing.
        if(item.contains("0")) item = item.replaceAll("0", "o");
        item = item.toLowerCase();
        item = item.substring(0,1).toUpperCase()+item.substring(1,item.length());
        return item;
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
    public String findType(String data){
        compilePattern(itemPattern, data);
        String result ="";
        if(m.find())
            m.find();
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
    public String findDate(String data){
        compilePattern(datePattern, data);
        String result ="";
        if(m.find())
            result = m.group().substring(1, m.group().length());
        return result;
    }
    public ArrayList<String> getWorkingData(){return workingData;}

    public void assemblePattern(){
        String itemAndType = "([A-Za-z]+[:][A-Za-z0-9]+)";
        String price = "([A-Za-z]+[:]([0-9])+\\.[0-9]{2})";
        String date = "[A-Za-z]+[:](([0]?[0-9])|([1][0-2]))\\/(([0-2][0-9])|([3][0-1]))\\/([0-9]){4}";
        pattern = itemAndType+"[:@^*;!]"+price+"[:@^*;!]"+itemAndType+"[:@^*;!]"+date+"#{2}";
    }

    public ArrayList<Item> getItemList(){return itemlist;}
    public int getErrorCount(){return errorcount;}
}
