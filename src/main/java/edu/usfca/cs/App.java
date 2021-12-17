package edu.usfca.cs;

import java.util.Scanner;

/**
 *This is the class that creates a userinterface for the music app. It calls the appropriate classes and their methods
 * depending on the user's commands.
 * @author Deep Mistry
 *
 */
public class App {
    static SQLite sq = new SQLite();

    /**
     * Upon running the main the program creates a simple textbased userinterface that can be used to traverse through
     * the user's music database. The user has the option to either view ALbums, Songs, Artists in the databse or
     * add them.
     * @param args
     */
    public static void main(String[] args ) {


        boolean more = true;
        while( more == true) {
            System.out.println("Type view to view the Library");
            System.out.println("Type add to add to the library");
            Scanner myObj = new Scanner(System.in);
            String choice = myObj.nextLine();

            if (choice.equalsIgnoreCase("view")) {
                sq.FromSQL();
            } else if (choice.equalsIgnoreCase("add")) {
                sq.ToSQL();
            }else{
                System.out.println("invalid input");
            }

            System.out.println("\n\nwould you like to quit(y/n)?");
            String choice2 = myObj.nextLine();

            if(choice2.equalsIgnoreCase("y")){
                more = false;
            }
        }
    }
}
