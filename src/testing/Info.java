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
 */

public class Info {

    /**
     * A helper function that prints out the results returned from queries.
     */
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

    /**
     * Prints out a row that meets the parameters input by the user.
     */
    static void printRow(Statement sqlStatement) {
        String sqlQuery, fromArg, whereCondition, whereArg;
        String caller = "row";
        String selectArg = "*";
        fromArg = "student";

        System.out.println("Please enter a column name: ");
        whereCondition = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        System.out.println("Please enter a value from the column: ");
        whereArg = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        sqlQuery = Helper.infoQueryBuilder(caller, selectArg, fromArg, whereCondition, whereArg);

        try {
            printResults((sqlStatement.executeQuery(sqlQuery)));
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Prints all the columns that the table has.
     */
    static void printColumn(Statement sqlStatement) {
        String sqlQuery, selectArg;
        String caller = "column";
        String fromArg = "student";

        System.out.println("Please enter a column name: ");
        selectArg = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        sqlQuery = Helper.infoQueryBuilder(caller, selectArg, fromArg, null, null);

        try {
            printResults(sqlStatement.executeQuery(sqlQuery));
        } catch (SQLException se) {
            se.printStackTrace();
        }
    } //End printColumns

    /**
     * Prints out every entry in the table.
     */
    static void printAll(Statement sqlStatement) {
        String sqlQuery;
        String caller = "all";
        String selectArg = "*";
        String fromArg = "student";

        sqlQuery = Helper.infoQueryBuilder(caller, selectArg, fromArg, null, null);

        try {
            printResults(sqlStatement.executeQuery(sqlQuery));
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Prints out all the column names in the table.
     */
    static void printColumnNames(Statement sqlStatement) {
        String sqlQuery;
        String caller = "columnNames";
        String selectArg = "COLUMN_NAME";
        String fromArg = "INFORMATION_SCHEMA.COLUMNS";
        String whereCondition = "table_name";
        String whereArg = "student";

        sqlQuery = Helper.infoQueryBuilder(caller, selectArg, fromArg, whereCondition, whereArg);

        try {
            printResults(sqlStatement.executeQuery(sqlQuery));
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
} //End Info
