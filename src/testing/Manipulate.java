package testing;
import java.sql.*;

/**
 * Author: Joseph Pariseau
 *
 * This class is responsible for manipulating the db based on user input.
 *
 * By the end it will be able to add, delete, and edit entries.
 */

public class Manipulate {

    /**
     * Simply adds an entry to the db.
     */
    static void addEntry(Statement sqlStatement) {
        String name, GPA, major, grad;

        System.out.println("Enter a first name: ");
        name = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        System.out.println("Enter a GPA: ");
        GPA = Helper.getFloatStr("Please enter a number between 0.0 and 4.0 characters: ", 0.0f, 4.0f);
        System.out.println("Enter a major: ");
        major = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
        System.out.println("Enter a year of graduation: ");
        grad = Helper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);

        //Construct the sqlQuery and execute it
        String sqlQuery = Helper.manipulateQueryBuilder("insert", "", "", new String[]{name, GPA, major, grad}, "", "");
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
        Helper.printChoices(caller);
        choice = Helper.getChoice(4);
        System.out.println("\n");
        switch (choice) {
            case 1:
                System.out.println("Enter a first name: ");
                String name = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                sqlQuery = Helper.manipulateQueryBuilder("delete", "name", name, null, "", "");
                break;
            case 2:
                System.out.println("Enter an id: ");
                String id = Helper.getIntStr("Please enter a valid id: ", 1, 100);
                sqlQuery = Helper.manipulateQueryBuilder("delete", "id", id, null, "", "");
                break;
            case 3:
                System.out.println("Enter a year of graduation: ");
                String grad = Helper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);
                sqlQuery = Helper.manipulateQueryBuilder("delete", "grad", grad, null, "", "");
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
        Helper.printChoices(caller1);
        choice = Helper.getChoice(4);
        System.out.println("\n");
        switch (choice) {
            case 1:
                whereCondition = "name";
                System.out.println("Enter a first name: ");
                whereArgument = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                break;
            case 2:
                whereCondition = "grad";
                System.out.println("Enter a year of graduation: ");
                whereArgument = Helper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);
                break;
            case 3:
                whereCondition = "id";
                System.out.println("Enter an id: ");
                whereArgument = Helper.getIntStr("Please enter a valid id: ", 1, 100);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid input.");
                return;
        }//End switch
        Helper.printChoices(caller2);
        choice = Helper.getChoice(5);
        System.out.println("\n");
        switch (choice) {
            case 1:
                setCondition = "name";
                System.out.println("Enter a first name: ");
                setArgument = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                break;
            case 2:
                setCondition = "GPA";
                System.out.println("Enter a GPA: ");
                setArgument = Helper.getFloatStr("Please enter a number between 0.0 and 4.0 characters: ", 0.0f, 4.0f);
                break;
            case 3:
                setCondition = "major";
                System.out.println("Enter a major: ");
                setArgument = Helper.getString("Please enter between 1 and 30 characters: ", 1, 30);
                break;
            case 4:
                setCondition = "grad";
                System.out.println("Enter a year of graduation: ");
                setArgument = Helper.getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid input.");
                return;
        }//End switch
        sqlQuery = Helper.manipulateQueryBuilder("edit", whereCondition, whereArgument, null, setCondition, setArgument);

        //Execute the sqlQuery
        try {
            if (sqlQuery != null) {
                sqlStatement.executeUpdate(sqlQuery);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }//End editEntry
}//End Manipulate