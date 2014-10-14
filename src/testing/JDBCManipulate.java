package testing;
import java.sql.*;
import java.util.Scanner;

/**
 * Author: Joseph Pariseau
 * This is a simple SQL server written in Java.
 */

public class JDBCManipulate {
    //Driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/STUDENTS";

    //Database credentials
    static final String USER = "USER";
    static final String PASS = "PASS";

    public static void main(String[] args) {
        //Initializing variables
        Connection conn = null;
        Statement stmt = null;
        Integer choice = 0;

        try {
            //Open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //Defining statement
            stmt = conn.createStatement();
            //Register driver
            Class.forName(JDBC_DRIVER);

            //Gets the user's choice and directs to the proper function
            while (choice != 4) {
                printChoices();
                System.out.println("What would you like to do? ");
                choice = getChoice();
                System.out.println("\n");
                switch (choice) {
                    case 1:
                        getInfo(stmt);
                        break;
                    case 2:
                        addEntry(stmt);
                        System.out.println("Data added.\n");
                        break;
                    case 3:
                        deleteEntry();
                        System.out.println("Data deleted.\n");
                        break;
                    case 4:
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
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
    }//end main

    static void printChoices() {
        System.out.println("Press 1 to see database information.\n" +
                "Press 2 to add an entry.\n" +
                "Press 3 to delete an entry.\n" +
                "Press 4 to exit.");
    }

    static int getChoice() {
        int choice;
        int loops = 0;
        do {
            if (loops > 0) {
                System.out.println("Please enter a valid value: ");
            }
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            loops++;
        } while (choice < 1 || choice > 4);
        return choice;
    }

    static void getInfo(Statement sqlStatement) {
        try {
            String sqlQuery = "SHOW COLUMNS FROM student;";
            sqlStatement.executeQuery(sqlQuery);
            ResultSet results = sqlStatement.executeQuery(sqlQuery);
            ResultSetMetaData resultsMetaData = results.getMetaData();

            int columnsNumber = resultsMetaData.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print("\t");
                    }
                    String columnValue = results.getString(i);
                    System.out.print(columnValue + " " + resultsMetaData.getColumnName(i) + "\n");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("\n\n");
    }

    static void addEntry(Statement sqlStatement) {
        String name, GPA, major, grad;
        Scanner in = new Scanner(System.in);
        int loops = 0;

        //Get name
        System.out.println("Enter a first name: ");
        do {
            if (loops > 1) {
                System.out.println("Please enter between 1 and 30 characters: ");
            }
            name = in.next();
            loops++;
        } while (name.length() > 30 || name.length() < 1);
        loops = 0;

        //Get GPA
        System.out.println("Enter a GPA: ");
        do {
            if (loops > 1) {
                System.out.println("Please enter a number between 0.0 and 4.0 characters: ");
            }
            GPA = in.next();
            loops++;
        } while (Float.parseFloat(GPA) > 4.0 || Float.parseFloat(GPA) < 0.0);
        loops = 0;

        //Get major
        System.out.println("Enter a major: ");
        do {
            if (loops > 1) {
                System.out.println("Please enter between 1 and 30 characters: ");
            }
            major = in.next();
            loops++;
        } while (major.length() > 30 || major.length() < 1);
        loops = 0;

        //Get year of graduation
        System.out.println("Enter a year of graduation: ");
        do {
            if (loops > 1) {
                System.out.println("Please enter a number between 1912 and 2020 characters: ");
            }
            grad = in.next();
            loops++;
        } while (Integer.parseInt(grad) > 2018 || Integer.parseInt(grad) < 1900);

        //Construct the sqlQuery and execute it
        String sqlQuery = "INSERT INTO student (name, GPA, major, grad)\n" +
                "VALUES ('" + name + "', '" + GPA + "', '" + major + "', '" + grad + "' );";
        System.out.println(sqlQuery);
        try {
            sqlStatement.executeUpdate(sqlQuery);
        } catch (SQLException se) {
            System.out.println(grad);
            se.printStackTrace();
        }
    }

    static void deleteEntry() {
        System.out.println("Delete");
    }
}//end JDBCManipulate