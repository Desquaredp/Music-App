package edu.usfca.cs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.usfca.cs.rest.artistAudioDB;
import static java.lang.Integer.parseInt;

/**
 * this class deals with adding the songs, artists, albums in the user's SQLite database.
 * it also fetches the information from the said database.
 * @author Deep Mistry
 **/
public class SQLite {

    public static Song s;
    public static Artist artAdd;
    public static Album albAdd;

    /**
     * Empty Constructor
     */
    public SQLite() {

    }

    /**
     * This method is used to input the data given by the user to their DAtabse. It is compartmentalised for 3 different
     * choices. It checks for primary keys when adding albums and artists. It throws exceptions when necessary.
     */

    public void ToSQL() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter your choice. \n" +
                    "type 1 for Song" +
                    "\ntype 2 for Artist" +
                    "\ntype 3 fpr Album");

            int choice = myObj.nextInt();

            if (choice == 1) {
                Scanner sngScan = new Scanner(System.in);
                System.out.println("Enter the song you'd like to add \n");
                String sngName = sngScan.nextLine();

                System.out.println("Enter the album the song is in \n");
                String albName = sngScan.nextLine();

                System.out.println("\n Enter the album's artist name\n");
                String artName = sngScan.nextLine();

                s = new Song(sngName, albName, artName);

                int instanceCount = 0;
                ResultSet art = statement.executeQuery("select * from artists");
                while (art.next()) {

                    if (artName.equalsIgnoreCase(art.getString("name"))) {
                        instanceCount++;
                        s.performer.entityID = art.getInt("id");
                    }
                }

                if (instanceCount == 0) {
                    artAdd = new Artist(artName);
                    artAdd.artistAudioDB();
                    statement.executeUpdate(artAdd.toSQL());
                    s.performer.entityID = artAdd.entityID;
                }

                instanceCount = 0;
                ResultSet alb = statement.executeQuery("select * from albums");
                while (alb.next()) {

                    if (albName.equalsIgnoreCase(alb.getString("name"))) {
                        instanceCount++;
                        s.album.entityID = alb.getInt("id");
                    }
                }

                if (instanceCount == 0) {
                    albAdd = new Album(albName);
                    albAdd.albumAudioDB(String.valueOf(s.performer.entityID));
                    statement.executeUpdate(albAdd.toSQL());
                    s.album.entityID = albAdd.entityID;
                }

                for (Song sng : s.album.addALbumSongs()) {
                    if (sng.name.equalsIgnoreCase(sngName))
                        statement.executeUpdate(sng.toSQL());
                }

                ResultSet rs = statement.executeQuery("select * from songs");
                while (rs.next()) {
                    if (rs.getString("name").equalsIgnoreCase(s.name)) {

                        System.out.println("name = " + rs.getString("name")
                                + "\t" + "id = " + rs.getInt("id")
                                + "\t" + "Genre = " + rs.getString("genre")
                                + "\t" + "Mood = " + rs.getString("mood") +
                                "\tAdded successfully!");
                    }
                }
            } else if (choice == 2) {
                Scanner albScan = new Scanner(System.in);
                System.out.println("Enter your artist. \n");
                String artName = albScan.nextLine();


                artAdd = new Artist(artName);
                artAdd.artistAudioDB();
                int counter = 0;

                ResultSet art = statement.executeQuery("select * from artists");
                while (art.next()) {
                    if (parseInt(art.getString("id")) == (artAdd.entityID)) {
                        counter++;
                        break;
                    }
                }

                if (counter == 0) {
                    statement.executeUpdate(artAdd.toSQL());
                    ResultSet artc = statement.executeQuery("select * from artists");
                    while (artc.next()) {
                        if (artc.getString("name").equalsIgnoreCase(artAdd.name)) {
                            System.out.println("name = " + artc.getString("name")
                                    + "\t" + "id = " + artc.getInt("id")
                                    + "\tMood = " + artc.getString("mood") +
                                    "\tAdded successfully!");
                        }
                    }
                } else {
                    System.out.println("The artist already exists in the Database");
                }

            } else if (choice == 3) {

                Scanner albScan = new Scanner(System.in);
                System.out.println("Enter the album you'd like to add. \n");
                String albName = albScan.nextLine();

                System.out.println("\n Enter the album's artist name\n");
                String artName = albScan.nextLine();

                albAdd = new Album(albName, artName);

                int instanceCount = 0;
                ResultSet art = statement.executeQuery("select * from artists");
                while (art.next()) {

                    if (artName.equalsIgnoreCase(art.getString("name"))) {
                        instanceCount++;
                        albAdd.artist.entityID = art.getInt("id");
                    }
                }

                if (instanceCount == 0) {
                    artAdd = new Artist(artName);
                    artAdd.artistAudioDB();
                    statement.executeUpdate(artAdd.toSQL());
                    albAdd.artist.entityID = artAdd.entityID;
                }

                albAdd.albumAudioDB(albAdd.artistAudioDB());


                int counter = 0;

                ResultSet alb = statement.executeQuery("select * from albums");

                while (alb.next()) {
                    if (parseInt(alb.getString("id")) == (albAdd.entityID)) {
                        counter++;
                        break;
                    }
                }
                if (counter == 0) {
                    statement.executeUpdate(albAdd.toSQL());

                    for (Song sng : albAdd.addALbumSongs()) {
                        statement.executeUpdate(sng.toSQL());
                    }
                    ResultSet alb1 = statement.executeQuery("select * from albums");

                    while (alb1.next()) {
                        if (alb1.getString("name").equalsIgnoreCase(albAdd.name)) {
                            System.out.println("name = " + alb1.getString("name")
                                    + "\t" + "id = " + alb1.getInt("id")
                                    + "\tMood = " + alb1.getString("mood") +
                                    "\tGenre = " + alb1.getString("genre") +
                                    "\tAdded successfully!");
                            System.out.println("Songs added: \n");
                            ResultSet rs = statement.executeQuery("select * from songs");
                            while (rs.next()) {
                                if (parseInt(rs.getString("album")) == (albAdd.entityID)) {

                                    System.out.println(rs.getString("name"));
                                }
                            }
                        }
                    }

                } else {
                    System.out.println("The album already exists in the Database");
                }
            }else{
                System.out.println("Invalid Input");
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());

            }
        }

    }

    /**
     * this method fetches data from the Database and displays it to the user. It is compartmentalised for 3 different
     *      choices. It checks for primary keys when adding albums and artists. It throws exceptions when necessary.
     */

    public static void FromSQL() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter your choice. \n" +
                    "type 1 for Song" +
                    "\ntype 2 for Artist" +
                    "\ntype 3 fpr Album");

            int choice = myObj.nextInt();  // Read user input

            if (choice == 1) {
                {
                    ResultSet rs = statement.executeQuery("select * from songs");

                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }


                    ArrayList<Song> list = new ArrayList<>();

                    for (int i = 0; i < count; i++) {
                        list.add(i, new Song());
                    }



                    ResultSet rv = statement.executeQuery("select * from songs");


                    for (Song s : list) {

                        if (rv.next()) {
                            s.fromSQL(rs);
                        }
                    }

                    for (Song s : list
                    ) {
                        System.out.println("Song:  " + s.name);

                    }


                }
            } else if (choice == 2) {
                ResultSet rs = statement.executeQuery("select * from artists");

                int count = 0;
                while (rs.next()) {
                    count++;
                }


                ArrayList<Artist> list = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    list.add(i, new Artist());
                }

                ResultSet rv = statement.executeQuery("select * from artists");


                for (Artist s : list) {

                    if (rv.next()) {
                        s.fromSQL(rs);
                    }
                }

                for (Artist s : list
                ) {
                    System.out.println("Artist: " + s.name);
                    System.out.println("mood: " + s.mood);
                }

            } else if (choice == 3) {
                ResultSet rs = statement.executeQuery("select * from albums");

                int count = 0;
                while (rs.next()) {
                    count++;
                }


                ArrayList<Album> list = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    list.add(i, new Album());
                }

                ResultSet rv = statement.executeQuery("select * from albums");


                for (Album s : list) {

                    if (rv.next()) {
                        s.fromSQL(rs);
                    }
                }

                for (Album s : list
                ) {
                    System.out.println("Album: " + s.name);

                }

            } else {
                System.out.println("Invalid input");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
