import java.io.*;

/**
 * Created by roberthitchens3 on 2/22/16.
 */
public class DataWriter {
    private String data;
    public DataWriter(String data){
        this.data = data;
        writeDataToFile();
    }
    public void writeDataToFile(){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("output.txt"), "utf-8"));
            writer.write(data);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }
}
