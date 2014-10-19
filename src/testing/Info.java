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

public class Info {
    static void printResults(ResultSet results) {
        try {
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
        }
    }

    static void printRow(Statement sqlStatement) {
        String sqlQuery, fromArg, whereCondition, whereArg;
        String caller = "row";
        String selectArg = "*";
        fromArg = "student";

        System.out.println("Please enter a row name: ");
        whereCondition = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        System.out.println("Please enter a value from the row: ");
        whereArg = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        sqlQuery = Helper.infoQueryBuilder(caller, selectArg, fromArg, whereCondition, whereArg);

        try {
            printResults((sqlStatement.executeQuery(sqlQuery)));
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Prints the columns at the table has.
     */
    static void printColumn(Statement sqlStatement) {
        String sqlQuery, caller, selectArg, fromArg, whereCondition, whereArg;
        try {
            sqlQuery = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'student';";
            printResults(sqlStatement.executeQuery(sqlQuery));
        } catch (SQLException se) {
            se.printStackTrace();
        }
    } //End printColumns

    static void printAll(Statement sqlStatement) {

    }

    static void printColumnNames(Statement sqlStatement) {

    }
} //End Info
