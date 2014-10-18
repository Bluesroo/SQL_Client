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
        String sqlQuery = JDBCHelper.manipulateQueryBuilder("insert", "", "", new String[] {name, GPA, major, grad}, "", "");
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
        String sqlQuery;
        int choice;

        //Constructs sqlQuery
        JDBCHelper.printChoices(caller);
        choice = JDBCHelper.getChoice(4);
        System.out.println("\n");
        switch (choice) {
            case 1:
                System.out.println("Enter a first name: ");
                String name = JDBCHelper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                sqlQuery = JDBCHelper.manipulateQueryBuilder("delete", "name", name, null, "", "");
                break;
            case 2:
                System.out.println("Enter an id: ");
                String id = JDBCHelper.getIntStr("Please enter a valid id: ", 1, 100);
                sqlQuery = JDBCHelper.manipulateQueryBuilder("delete", "id", id, null, "", "");
                break;
            case 3:
                System.out.println("Enter a year of graduation: ");
                String grad = JDBCHelper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);
                sqlQuery = JDBCHelper.manipulateQueryBuilder("delete", "grad", grad, null, "", "");
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid input.");
                return;
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
     * Edits a database entry
     */
    static void editEntry(Statement sqlStatement) {
        String caller1 = "edit1";
        String caller2 = "edit2";
        String sqlQuery;
        String whereCondition, whereArgument, setCondition, setArgument;
        int choice;

        //Constructs sqlQuery
        JDBCHelper.printChoices(caller1);
        choice = JDBCHelper.getChoice(4);
        System.out.println("\n");
        switch (choice) {
            case 1:
                whereCondition = "name";
                System.out.println("Enter a first name: ");
                whereArgument = JDBCHelper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                break;
            case 2:
                whereCondition = "grad";
                System.out.println("Enter a year of graduation: ");
                whereArgument = JDBCHelper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);
                break;
            case 3:
                whereCondition = "id";
                System.out.println("Enter an id: ");
                whereArgument = JDBCHelper.getIntStr("Please enter a valid id: ", 1, 100);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid input.");
                return;
        }//End switch
        JDBCHelper.printChoices(caller2);
        choice = JDBCHelper.getChoice(5);
        System.out.println("\n");
        switch (choice) {
            case 1:
                setCondition = "name";
                System.out.println("Enter a first name: ");
                setArgument = JDBCHelper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                break;
            case 2:
                setCondition = "GPA";
                System.out.println("Enter a GPA: ");
                setArgument = JDBCHelper.getFloatStr("Please enter a number between 0.0 and 4.0 characters: ", 0.0f, 4.0f);
                break;
            case 3:
                setCondition = "major";
                System.out.println("Enter a major: ");
                setArgument = JDBCHelper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                break;
            case 4:
                setCondition = "grad";
                System.out.println("Enter a year of graduation: ");
                setArgument = JDBCHelper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid input.");
                return;
        }//End switch
        sqlQuery = JDBCHelper.manipulateQueryBuilder("edit", whereCondition, whereArgument, null, setCondition, setArgument);

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