import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Main {

    public String readRawDataToString() throws Exception{
        Path path = Paths.get(ClassLoader.getSystemResource("RawData.txt").toURI());
        return new String(readAllBytes(get(path.toUri())));
    }

    public static void main(String[] args) throws Exception{
        Main maine= new Main();
        JerkSONParser jerkSONParser = new JerkSONParser(maine.readRawDataToString());
        DataFormatter dataFormatter = new DataFormatter(jerkSONParser.getItemList(), jerkSONParser.getErrorCount());
        DataWriter dataWriter = new DataWriter(dataFormatter.getOutputData());
    }
}
