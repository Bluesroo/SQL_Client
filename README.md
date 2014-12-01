#SQL_Client

This is an SQL client that I've written on my own to learn Java and SQL (MySQL specifically).

##Structure
###[Client.java](https://github.com/Bluesroo/SQL_Client/blob/master/src/main/java/Client.java)
This file contains the main entry point of the client. It takes the action that the use wants to do and send him to the
appropriate function.
###[QueryBuilder.java](https://github.com/Bluesroo/SQL_Client/blob/master/src/main/java/QueryBuilder.java)
This file contains the functions used to build queries to be used by DatabaseReader and DatabaseManipulator.
###[DatabaseManipulator.java](https://github.com/Bluesroo/SQL_Client/blob/master/src/main/java/DatabaseManipulator.java)
This file contains the functions that are used to make changes to the database. e.g. Adding, deleting, and editing
entries.
###[DatabaseReader.java](https://github.com/Bluesroo/SQL_Client/blob/master/src/main/java/DatabaseReader.java)
This file contains the functions that allow you to see what is in the database.
###[Helpers.java](https://github.com/Bluesroo/SQL_Client/blob/master/src/main/java/Helpers.java)
This file contains the "utility" functions. e.g. Printing the options for the users, and converting user inputs into
usable data.

##Future Goals
I might use this in the future to help learn HTML, CSS, and JS by making a web front end for it. For now, development is
done.