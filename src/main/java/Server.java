package main.java ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Author: Joseph Pariseau
 *
 * This is a simple SQL Server written in Java.
 */

public class Server {
    //Database connection variables
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/STUDENTS";
    static final String USER = User.USER;
    static final String PASS = User.PASS;
    static final int MAIN_CHOICE_AMOUNT = 8;

    /**
     * Main entry point for the server.
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement sqlStatement = null;
        Integer choice = 0;
        String caller = "main";

        try {
            //Connects to the database and prepares to issue a statement
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            sqlStatement = conn.createStatement();
            Class.forName(JDBC_DRIVER);

            //Gets the user's choice and directs them to the proper function
            while (choice != MAIN_CHOICE_AMOUNT) {
                Helper.printChoices(caller);
                System.out.println("What would you like to do? ");
                choice = Helper.getChoice(MAIN_CHOICE_AMOUNT);
                System.out.println("\n");
                switch (choice) {
                    case 1:
                        Info.printRow(sqlStatement);
                        break;
                    case 2:
                        Info.printColumn(sqlStatement);
                        break;
                    case 3:
                        Info.printAll(sqlStatement);
                        break;
                    case 4:
                        Info.printColumnNames(sqlStatement);
                        break;
                    case 5:
                        Manipulate.addEntry(sqlStatement);
                        System.out.println("Data added.\n");
                        break;
                    case 6:
                        Manipulate.deleteEntry(sqlStatement);
                        System.out.println("Data deleted.\n");
                        break;
                    case 7:
                        Manipulate.editEntry(sqlStatement);
                        System.out.println("Data edited.\n");
                        break;
                    case MAIN_CHOICE_AMOUNT:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //Finally block used to close resources
            try {
                if (sqlStatement != null) {
                    sqlStatement.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } //End finally try
        } //End try
    } //End main
} //End server
