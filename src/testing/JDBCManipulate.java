package testing;
import java.sql.*;

/**
 * Author: Joseph Pariseau
 *
 * This class is responsible for manipulating the db based on user input.
 *
 * By the end it will be able to add, delete, and edit entries.
 *
 * (It might also be able to edit the table itself, but that's to be decided
 * later).
 */

public class JDBCManipulate {

    /**
     * Simply adds an entry to the db.
     */
    static void addEntry(Statement sqlStatement) {
        String name, GPA, major, grad;

        System.out.println("Enter a first name: ");
        name = JDBCHelper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        System.out.println("Enter a GPA: ");
        GPA = JDBCHelper.getFloatStr("Please enter a number between 0.0 and 4.0 characters: ", 0.0f, 4.0f);
        System.out.println("Enter a major: ");
        major = JDBCHelper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        System.out.println("Enter a year of graduation: ");
        grad = JDBCHelper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);

        //Construct the sqlQuery and execute it
        String sqlQuery = "INSERT INTO student (name, GPA, major, grad)\n" +
                "VALUES ('" + name + "', '" + GPA + "', '" + major + "', '" + grad + "' );";
        try {
            sqlStatement.executeUpdate(sqlQuery);
        } catch (SQLException se) {
            System.out.println(grad);
            se.printStackTrace();
        }
    }//End addEntry

    /**
     * Simply deletes an entry from the db
     */
    static void deleteEntry(Statement sqlStatement) {
        String caller = "delete";
        String sqlQuery = null;
        int choice;

        //Constructs sqlQuery
        JDBCHelper.printChoices(caller);
        choice = JDBCHelper.getChoice(4);
        System.out.println("\n");
        switch (choice) {
            case 1:
                System.out.println("Enter a first name: ");
                String name = JDBCHelper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                sqlQuery = "DELETE FROM student WHERE name = '" + name + "'\n" +
                        "ORDER BY added LIMIT 1;";
                break;
            case 2:
                System.out.println("Enter an id: ");
                String id = JDBCHelper.getIntStr("Please enter a valid id: ", 1, 100);
                sqlQuery = "DELETE FROM student WHERE id = '" + id + "'\n" +
                        "ORDER BY added LIMIT 1;";
                break;
            case 3:
                System.out.println("Enter a year of graduation: ");
                String grad = JDBCHelper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);
                sqlQuery = "DELETE FROM student WHERE grad = '" + grad + "'\n" +
                        "ORDER BY added LIMIT 1;";
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid input.");
                break;
        }

        //Execute the sqlQuery
        try {
            if (sqlQuery != null) {
                sqlStatement.executeUpdate(sqlQuery);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }//End deleteEntry

    /**
     * STUB
     * Edits an entry to the table.
     */
    static void editEntry(Statement sqlStatement) {
        String caller = "edit";
        String sqlQuery = null;
        int choice;

        JDBCHelper.printChoices(caller);
        choice = JDBCHelper.getChoice(4);
        System.out.println("\n");
        switch (choice) {
            case 1:
                System.out.println("Enter a first name: ");
                String name = JDBCHelper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                sqlQuery = "DELETE FROM student WHERE name = '" + name + "'\n" +
                        "ORDER BY added LIMIT 1;";
                break;
            case 2:
                System.out.println("Enter an id: ");
                String id = JDBCHelper.getIntStr("Please enter a valid id: ", 1, 100);
                sqlQuery = "DELETE FROM student WHERE id = '" + id + "'\n" +
                        "ORDER BY added LIMIT 1;";
                break;
            case 3:
                System.out.println("Enter a year of graduation: ");
                String grad = JDBCHelper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);
                sqlQuery = "DELETE FROM student WHERE grad = '" + grad + "'\n" +
                        "ORDER BY added LIMIT 1;";
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid input.");
                break;
        }

        //Execute the sqlQuery
        try {
            if (sqlQuery != null) {
                sqlStatement.executeUpdate(sqlQuery);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }//End editEntry
}//End JDBCManipulate