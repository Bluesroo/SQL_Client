package testing;
import java.sql.*;
import java.util.Scanner;

/**
 * Author: Joseph Pariseau
 *
 * This is a simple SQL server written in Java.
 *
 * By the end it will be able to add entries, delete entries, and look up
 * entries.
 */

public class JDBCManipulate {
    //Database connection variables
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/STUDENTS";
    static final String USER = "USER";
    static final String PASS = "PASS";

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
            while (choice != 4) {
                printChoices(caller);
                System.out.println("What would you like to do? ");
                choice = getChoice();
                System.out.println("\n");
                switch (choice) {
                    case 1:
                        getInfo(sqlStatment);
                        break;
                    case 2:
                        addEntry(sqlStatment);
                        System.out.println("Data added.\n");
                        break;
                    case 3:
                        deleteEntry(sqlStatment);
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

    static void printChoices(String caller) {
        switch (caller) {
            case "main":
                System.out.println("Press 1 to see database information.\n" +
                        "Press 2 to add an entry.\n" +
                        "Press 3 to delete an entry.\n" +
                        "Press 4 to exit.");
                break;
            default:
                System.out.println("Invalid caller.");
                break;
        }//End switch
    }//End printChoices

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
    }//End getChoice

    static String getString(String warning, int minLength, int maxLength) {
        String input;
        Scanner in = new Scanner(System.in);
        int loops = 0;

        do {
            if (loops > 1) {
                System.out.println(warning);
            }
            input = in.next();
            loops++;
        } while (input.length() > maxLength || input.length() < minLength);
        return input;
    }//End getString

    static String getIntStr(String warning, int min, int max) {
        String input;
        Scanner in = new Scanner(System.in);
        int loops = 0;

        do {
            if (loops > 1) {
                System.out.println(warning);
            }
            input = in.next();
            loops++;
        } while (Integer.parseInt(input) < min || Integer.parseInt(input) > max);
        return input;
    }//End getIntStr

    static String getFloatStr(String warning, Float min, Float max) {
        String input;
        Scanner in = new Scanner(System.in);
        int loops = 0;

        do {
            if (loops > 1) {
                System.out.println(warning);
            }
            input = in.next();
            loops++;
        } while (Float.parseFloat(input) < min || Float.parseFloat(input) > max);
        return input;
    }//End getFloatStr

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
                }//End for
            }//End while
        } catch (SQLException se) {
            se.printStackTrace();
        }//End try
        System.out.println("\n\n");
    }//End getInfo

    static void addEntry(Statement sqlStatement) {
        String name, GPA, major, grad;

        System.out.println("Enter a first name: ");
        name = getString("Please enter between 1 and 30 characters: ", 1, 30);
        System.out.println("Enter a GPA: ");
        GPA = getFloatStr("Please enter a number between 0.0 and 4.0 characters: ", 0.0f, 4.0f);
        System.out.println("Enter a major: ");
        major = getString("Please enter between 1 and 30 characters: ", 1, 30);
        System.out.println("Enter a year of graduation: ");
        grad = getIntStr("Please enter a year between 1912 and 2020: ", 1900, 2018);

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

    static void deleteEntry(Statement sqlStatement) {
        System.out.println("Please enter the name of the entry you'd like to delete: ");
        String name = getString("Please enter between 1 and 30 characters: ", 1, 30);

        //Construct the sqlQuest and execute it
        String sqlQuery = "DELETE FROM student WHERE name = '" + name + "'\n" +
                "ORDER BY added LIMIT 1;";
        try {
            sqlStatement.executeUpdate(sqlQuery);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }//End deleteEntry
}//End JDBCManipulate