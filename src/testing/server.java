package testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Author: Joseph Pariseau
 *
 * This is a simple SQL server written in Java.
 *
 * By the end it will be able to add entries, delete entries, edit entries,
 * and look up entries with lots of parameters.
 */

public class server {
    //Database connection variables
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/STUDENTS";
    static final String USER = User.USER;
    static final String PASS = User.PASS;

    /**
     * Main entry point for the server.
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement sqlStatment = null;
        Integer choice = 0;
        String caller = "main";

        try {
            //Connects to the database and prepares to issue a statement
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            sqlStatment = conn.createStatement();
            Class.forName(JDBC_DRIVER);

            //Gets the user's choice and directs them to the proper function
            while (choice != 5) {
                Helper.printChoices(caller);
                System.out.println("What would you like to do? ");
                choice = Helper.getChoice(5);
                System.out.println("\n");
                switch (choice) {
                    case 1:
                        Info.printColumns(sqlStatment);
                        break;
                    case 2:
                        Manipulate.addEntry(sqlStatment);
                        System.out.println("Data added.\n");
                        break;
                    case 3:
                        Manipulate.deleteEntry(sqlStatment);
                        System.out.println("Data deleted.\n");
                        break;
                    case 4:
                        Manipulate.editEntry(sqlStatment);
                        System.out.println("Data edited.\n");
                        break;
                    case 5:
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
                if (sqlStatment != null) {
                    sqlStatment.close();
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
    }//End main
}//End server
