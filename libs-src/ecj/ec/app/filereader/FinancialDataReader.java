package ec.app.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class FinancialDataReader {

    String path;
    
    // Use this class to return ArrayLists given only the path to a data .txt file
    // This helps cleanup the main class.
    public FinancialDataReader() {
        path = System.getProperty("user.dir");
    }
    
    public ArrayList<Double> read(InputFileEnum in) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path + in.v()));
        ArrayList<Double> data = new ArrayList<>();
        
        String next;
        while((next = reader.readLine()) != null) {
            data.add(Double.valueOf(next));
        }
        
        return data;
        
    }
}
