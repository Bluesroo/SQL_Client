package main.java;

import java.util.Scanner;

/**
 * Author: Joseph Pariseau
 *
 * This is a helper class for the server. It contains lots of little functions
 * that are needed for other classes to work. e.g. getting inputs and
 * converting them to ints or floats.
 */

public class Helpers {

    /**
     * Prints out the user's choices depending on the view that they are looking at.
     */
    static void printChoices(String caller) {
        System.out.println("\n");
        switch (caller) {
            case "main":
                System.out.println("Press 1 to see a row.\n" +
                        "Press 2 to see a column.\n" +
                        "Press 3 to see the whole table.\n" +
                        "Press 4 to see the column names.\n" +
                        "Press 5 to add an entry.\n" +
                        "Press 6 to delete an entry.\n" +
                        "Press 7 to edit an entry.\n" +
                        "Press 8 to exit.");
                break;
            case "delete":
                System.out.println("Press 1 to delete by name.\n" +
                        "Press 2 to delete by id.\n" +
                        "Press 3 to delete by graduation year.\n" +
                        "Press 4 to cancel.");
                break;
            case "edit1":
                System.out.println("Press 1 to edit by name.\n" +
                        "Press 2 to edit by graduation year.\n" +
                        "Press 3 to edit by id.\n" +
                        "Press 4 to cancel.");
                break;
            case "edit2":
                System.out.println("Press 1 to edit the name.\n" +
                        "Press 2 to edit the GPA.\n" +
                        "Press 3 to edit the major.\n" +
                        "Press 4 to edit the graduation year.\n" +
                        "Press 5 to cancel.");
                break;
            default:
                System.out.println("Invalid caller.");
                break;
        } //End switch
    } //End printChoices

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
    } //End getChoice

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
    } //End getString

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
    } //End getIntStr

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
    } //End getFloatStr
} //End Helpers
