import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetPrinter {

    public static void printResultSet(ResultSet resultSet) {
        try {
            // Get metadata from the result set
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Get the number of columns
            int columnCount = metaData.getColumnCount();

            // Print column headings
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", metaData.getColumnName(i));
            }
            System.out.println();

            // Print rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-20s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        // Assuming you have a Connection object and a Statement object named 'statement'
        // Execute a query and get the ResultSet
        // ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table");

        // Call the printResultSet method
        // printResultSet(resultSet);
    }
}
