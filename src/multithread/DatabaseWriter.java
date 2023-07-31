package multithread;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseWriter implements Runnable {
	
    private final List<String> dataChunk;

    public DatabaseWriter(List<String> dataChunk) {
    	
        this.dataChunk = dataChunk;
    }

    @Override
    public void run() {
       
     
        String DBURL = "jdbc:mysql://localhost:3306/new_db?user=root&password=root";
       String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";

       
       
       
//		Class.forName(DRIVERCLASS);
//		Connection 	con = DriverManager.getConnection(DBURL);
       
       
       

        try (Connection con = DriverManager.getConnection(DBURL)) {
         
            String tableName = "new_table" + Thread.currentThread().getId();
            
            String insertQuery = "INSERT INTO " + tableName + " (column1, column2, column3,column4) VALUES (?, ?, ?,?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            	
                for (String rd : dataChunk) {
                    
                    String[] values = rd.split(",");
                    
                    preparedStatement.setString(1, values[0]);
                    preparedStatement.setString(2, values[1]);
                    preparedStatement.setString(3, values[2]);
                    preparedStatement.setString(4, values[3]);

                   
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
