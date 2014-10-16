package testing;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Author: Joseph Pariseau
 *
 * This class is responsible for retrieving information from the db to be
 * displayed to the user.
 *
 * By the end it will be able to handle complex SELECT requests.
 */

public class JDBCInfo {

    /**
     * Prints the columns at the table has.
     */
    static void printColumns(Statement sqlStatement) {
        try {
            String sqlQuery = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'student';";
            ResultSet results = sqlStatement.executeQuery(sqlQuery);
            ResultSetMetaData resultsMetaData = results.getMetaData();

            int columnsNumber = resultsMetaData.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = results.getString(i);
                    System.out.println(columnValue);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }//End try
    }//End printColumns
}//End JDBCInfo
