package edu.usfca.cs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * this class contains all the properties of an artist
 * it extends from entity
 *@author Deep Mistry
 **/
public class Artist extends Entity {

    protected ArrayList<Song> songs;
    protected ArrayList<Album> albums;

    /**
     * this method checks if an Artist already exists. it returns a boolean value
     * @param otherArtist the artist to be checked
     * @return returns a boolean value
     */
    public boolean equals(Artist otherArtist) {
        if (this.name.equals(otherArtist.name)) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * Empty artist constructor
     */
    public Artist(){
    }

    /**
     * Artist Constructor
     * @param name takes in the name of the artist
     */
    public Artist(String name) {
        super(name);
    }

    /**
     * getter that returns an arraylist of song objects
     * @return returns an arraylist of song objects
     */
    protected ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * setter for songs
     * @param songs takes in an arraylist of song objects
     */
    protected void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    /**
     * getter for albums
     *
     * @return returns an arraylist of artists
     */
    protected ArrayList<Album> getAlbums() {
        return albums;
    }

    /**
     * setter for albums
     * @param albums takes in an arraylist of album objects
     */
    protected void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    /**
     * adds a song object to the songs arraylist
     * @param s takes in a song object that is to be added to the songs arraylist
     */
    public void addSong(Song s) {
        songs.add(s);
    }

    /**
     * Generates an XML script
     * @return  returns an XML script with the name and the id of the Artist object
     */
    public String XML() {
        return "\t<artist id= \"" + this.entityID + "\">\n" +
                "\t\t<name>" + this.name + "</name>\n" +
                "\t</artist>";
    }

    /**
     *Creates an SQL command
     * @return returns an SQLite command that inserts Artist name, Artist Id and mood.
     */
    public String toSQL(){
        return "insert into artists values(" + this.entityID + ", '" + stripPunct(this.name)+ "', '"  + this.mood + "');";
    }

    /**
     * assigns the result set, used to search through the databse, to Artist properties
     * @param rs Result set that retrives information from the databse
     * @throws SQLException
     */
    public void fromSQL(ResultSet rs) throws SQLException {
        this.entityID = rs.getInt("id");
        this.name = rs.getString("name");
        this.mood = rs.getString("mood");
    }

    /**
     * This method fetches all the properties of an Artist from AudioDB and assigns them to appropriate facets
     * of the Artist object
     */
    public void artistAudioDB() {
        String requestURL = "https://www.theaudiodb.com/api/v1/json/2/search.php?s=";
        String artist = this.name;
        StringBuilder response = new StringBuilder();
        URL u;
        try {
            u = new URL(requestURL + artist);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
            return;
        }
        try {
            URLConnection connection = u.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int code = httpConnection.getResponseCode();

            String message = httpConnection.getResponseMessage();
            System.out.println(code + " " + message);
            if (code != HttpURLConnection.HTTP_OK) {
                return;
            }
            InputStream instream = connection.getInputStream();
            Scanner in = new Scanner(instream);
            while (in.hasNextLine()) {
                response.append(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading response");
            return;
        }
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response.toString());
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray artists = (JSONArray)jsonObject.get("artists"); // get the list of all artists returned.
            JSONObject beatles =(JSONObject) artists.get(0);  // I happen to know that the beatles is the first entry. Otherwise I'd iterate.
            this.name = (String)beatles.get("strArtist");
            this.entityID = parseInt((String) beatles.get("idArtist"));
            String mood = (String)beatles.get("strMood");
            System.out.println("Mood: " + mood);
            this.mood = (String)beatles.get("strMood");

        } catch(ParseException e) {
            System.out.println("Error parsing JSON");
            return;
        }

    }



}
