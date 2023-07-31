package multithread;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
    	
        String csvFilePath = "‪C:\\Users\\ELCOT-Lenovo\\Downloads\\orders.csv";
        
        CSVFileReader csvReader = new CSVFileReader();
        
        try {
            List<List<String>> dataChunks = csvReader.readCSV(csvFilePath);
            
            ExecutorService executor = Executors.newFixedThreadPool(10);
            
            for (List<String> chunk : dataChunks) {
            	
                executor.execute(new DatabaseWriter(chunk));
            }
            
            executor.shutdown();
            
            
        }
        
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
