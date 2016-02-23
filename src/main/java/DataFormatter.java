import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by roberthitchens3 on 2/22/16.
 */
public class DataFormatter {
    private ArrayList<Item> parsedData;
    private int errorCount;
    //private ArrayList<Item> items;
    private HashMap<String , HashMap<String, Integer>> data;
    private String outputData;
    private StringBuilder sb;
    public DataFormatter(ArrayList<Item> parsedData, int errorCount){
        this.parsedData = parsedData;
        this.errorCount = errorCount;
        sb = new StringBuilder();
        outputData = "";
    }

    public void dataFormat(){
        for(Item i : parsedData){
            appendToBuilder(i);
            sb.append("\n");
        }
        sb.append(String.format("%1$-13s %2$17s", "Error", "seen: "+errorCount+" times"));
        outputData = sb.toString();
    }
    public String getOutputData(){
        dataFormat();
        return outputData;
    }
    public void appendToBuilder(Item i){
        sb.append(String.format("name: %1$-13s", i.getName()));
        sb.append(String.format("%1$13s", "seen: "+i.getTimesSeen()+" times"+"\n"));
        sb.append("============= \t \t =============\n");
        appendPrices(i);
    }
    public void appendPrices(Item i){
        for(String s : i.getPrices().keySet()) {
            sb.append(String.format("price: %1$-13s", s));
            sb.append(String.format("%1$13s", "seen: "+i.getPrices().get(s)+" times"+"\n"));
            sb.append("-------------\t\t -------------\n");
        }
    }
}
