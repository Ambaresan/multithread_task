package multithread;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {
	
    public List<List<String>> readCSV(String filePath) throws IOException {
    	
        List<List<String>> dataChunks = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        	
            String line;
            
            List<String> chunk = new ArrayList<>();
            
            int count = 0;
            
            while ((line = br.readLine()) != null) {
            	
                chunk.add(line);
                count++;
                
                if (count == 10000) {
                    dataChunks.add(chunk);
                    chunk = new ArrayList<>();
                    count = 0;
                }
            }
            if (!chunk.isEmpty()) {
                dataChunks.add(chunk);
            }
        }
        return dataChunks;
    }
}
