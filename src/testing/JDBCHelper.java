package testing;

import java.util.Scanner;

/**
 * Author: Joseph Pariseau
 *
 * This is a helper class for the server.
 *
 * By the end it will handle user input and SQL injection protection.
 */

public class JDBCHelper {

    /**
     * Prints out the user's choices depending on the view that they are looking at.
     */
    static void printChoices(String caller) {
        System.out.println("\n");
        switch (caller) {
            case "main":
                System.out.println("Press 1 to see database information.\n" +
                        "Press 2 to add an entry.\n" +
                        "Press 3 to delete an entry.\n" +
                        "Press 4 to edit an entry.\n" +
                        "Press 5 to exit.");
                break;
            case "delete":
                System.out.println("Press 1 to delete by name.\n" +
                        "Press 2 to delete by id.\n" +
                        "Press 3 to delete by graduation year.\n" +
                        "Press 4 to cancel.");
                break;
            case "edit":
                System.out.println("Press 1 to edit by name.\n" +
                        "Press 2 to edit by id.\n" +
                        "Press 3 to edit by graduation year.\n" +
                        "Press 4 to cancel.");
            default:
                System.out.println("Invalid caller.");
                break;
        }//End switch
    }//End printChoices

    /**
     * Gets user input between 1 and 4.
     * Used in conjunction with printChoices.
     */
    static int getChoice(int max) {
        int choice;
        int loops = 0;

        do {
            if (loops > 0) {
                System.out.println("Please enter a value between 1 and " + max +": ");
            }
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            loops++;
        } while (choice < 1 || choice > max);
        return choice;
    }//End getChoice

    /**
     * Gets a string from System.in.
     * A min and max lengths of the string are passed into the function.
     */
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

    /**
     * Similar to getString, but gets an int formatted as a String.
     * A min and max for the int are passed into the function.
     */
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

    /**
     * Similar to getIntStr, but gets a float formatted as a String.
     * A min and max for the float are passed into the function.
     */
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

    static String queryBuilder(String caller,
                               String whereCondition, String whereArgument,
                               String setCondition, String setArgument) {
        String query;
        String operation;
        switch (caller) {
            case "insert":
                operation = "INSERT INTO student (name, GPA, major, grad)";
                break;
            case "delete":
                operation = "DELETE FROM student";
                break;
            case "edit":
                operation = "UPDATE student";
                break;
            default:
                return "";
        }
        query = operation;

        String where = "WHERE " + whereCondition + " = '" + whereArgument + "'";
        query = query.concat(" " + where);

        if (caller.equals("edit")) {
            String set = "SET " + setCondition + " = '" + setArgument + "'";
            query = query.concat(" " + set);
        }

        String orderCondition;
        if (caller.equals("insert")) {
            orderCondition = ";";
        }
        else {
            orderCondition = "ORDER BY added LIMIT 1;";
        }
        query = query.concat(" " + orderCondition);
        return query;
    }//End queryBuilder
}//End JDBCHelper
