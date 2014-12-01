package main.java;

/**
 * Author: Joseph Pariseau
 *
 * This class is responsible for building queries to be used by DatabaseManipulator and DatabaseReader.
 */

public class QueryBuilder {
    /**
     * Construct an informative SQL query (SELECT).
     * Returns the query as a String.
     */
    static String infoQueryBuilder(String caller, String selectArg,
                                   String fromArg, String whereCondition,
                                   String whereArg) {
        String query = "SELECT " + selectArg + "\nFROM " + fromArg;

        if (caller.equals("row") || caller.equals("columnNames")) {
            query = query.concat("\nWHERE " + whereCondition + " = '" + whereArg + "';");
        }

        return query;
    }

    /**
     * Constructs a manipulative SQL query (INSERT, UPDATE, DELETE).
     * Returns the query as a String.
     */
    static String manipulateQueryBuilder(String caller, String whereCondition,
                                         String whereArgument, String[] valueArguments,
                                         String setCondition, String setArgument) {
        String query;

        //Sets the statement of the query
        String statement;
        switch (caller) {
            case "insert":
                statement = "INSERT INTO student (name, GPA, major, grad)";
                break;
            case "delete":
                statement = "DELETE FROM student";
                break;
            case "edit":
                statement = "UPDATE student";
                break;
            default:
                return "";
        }
        query = statement;

        //If insert called this function, set the VALUES for INSERT INTO
        if (caller.equals("insert")) {
            String values = "VALUES ('" + valueArguments[0] + "', '" + valueArguments[1] + "'," +
                    " '" + valueArguments[2] + "', '" + valueArguments[3] + "')";
            query = query.concat("\n" + values);
        }

        //If edit called this function, set the SET for UPDATE
        if (caller.equals("edit")) {
            String set = "SET " + setCondition + " = '" + setArgument + "'";
            query = query.concat("\n" + set);
        }

        //If insert was not the caller, set the WHERE value and ORDER BY limit
        String orderCondition;
        if (!caller.equals("insert")) {
            String where = "WHERE " + whereCondition + " = '" + whereArgument + "'";
            orderCondition = "\nORDER BY added LIMIT 1;";
            query = query.concat("\n" + where);
        } else {
            orderCondition = ";";
        }
        query = query.concat(orderCondition);

        return query;
    } //End manipulateQueryBuilder
}
