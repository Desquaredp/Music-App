package edu.usfca.cs;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;
//
//import static java.lang.Integer.parseInt;

/**
 * this class contains all the properties of a Song
 * it extends from the Entity class
 * @author Deep Mistry
 */
public class Song extends Entity {

    protected Album album;
    protected Artist performer;
    protected SongInterval duration;
    protected String genre;
    protected Boolean liked;

    /**
     * sConstructor
     * @param name takes in name of the Song object
     * @param length takes in the length of the Song
     * @param album takes in the name of the Album object
     * @param performer takes in the name of the Artist object
     * @param liked takes in the boolean vlaue if the song is liked or not
     *
     */
    public Song(String name, int length, String album, String performer, boolean liked) {
        super(name);
        this.duration = new SongInterval(length);
        this.performer = new Artist(performer);
        this.album = new Album(album, performer);
        this.liked = liked;
        genre = "";
    }

    /**
     * Constructor
     * @param name Takes in the mname of the Song object
     * @param album Takes in the name of the Album object
     * @param performer Takes in the name of the Artist object
     */
    public Song(String name, String album, String performer) {
        super(name);
        this.performer = new Artist(performer);
        this.album = new Album(album, performer);
        this.genre = "";
        this.mood = "";
    }

    /**
     * constuctor
     * @param name Takes in the name of the Song object
     * @param length Takes in the length of the Song
     *
     * @param album Takes n the name of the ALbum object
     * @param performer TAkes in the name of the Artist object
     * @param liked Takes in the boolean value if the song is liked or not
     * @param genre Takes in the genre of the Song object
     */
    public Song(String name, int length, String album, String performer, boolean liked, String genre) {
        super(name);
        this.duration = new SongInterval(length);
        this.performer = new Artist(performer);
        this.album = new Album(album, performer);
        this.liked = liked;
        this.genre = genre;
    }

    /**
     * Empty constructor
     */
    public Song() {
    }

    /**
     * checks if a Song is already present
     * @param otherSong the song to be checked
     * @return returns a boolean value
     */
    public boolean equals(Song otherSong) {
        if ((this.album.name.equals(otherSong.getAlbum().name) &&
                this.name.equals(getName()) &&
                this.performer.name.equals(otherSong.getPerformer().name))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getter for liked
     * @return returns a boolean if the song is liked ot not
     */
    public Boolean getLiked() {
        return liked;
    }

    /**
     * setter for liked
     * @param liked takes in a boolean value
     */
    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    /**
     * getter for genre
     * @return returns the of the genre of the SOng object
     */
    public String getGenre() {
        return genre;
    }

    /**
     * setter for genre
     * @param genre takes in the value of the genre
     */

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * setter for setlength
     * @param length tkaes in the value of the length of the Song object
     */
    public void setLength(int length) {
        duration = new SongInterval(length);
    }

    /**
     * Shows the duration of the song
     * @return duration of the song as string
     */
    public String showLength() {
        return duration.toString();
    }

    /**
     * getter for Album
     * @return returns an Album object
     */
    protected Album getAlbum() {
        return album;
    }

    /**
     * setter for Album
     * @param album takes iin an Album object
     *
     */
    protected void setAlbum(Album album) {
        this.album = album;
    }

    /**
     * getter for Artist
     * @return returns an Artist oject
     */
    public Artist getPerformer() {
        return performer;
    }

    /**
     * getter for Artist
     * @param performer takes in an Artist object
     */
    public void setPerformer(Artist performer) {
        this.performer = performer;
    }


    /**
     * Generates an XML script
     * @return returns a XML script with the name and the id of the Song, Artist and the Album objects respectively
     */
    public String XML() {
        return "\t<song id= \"" + this.entityID + "\">\n" +
                "\t\t<title>" + this.name + "</title>\n" +
                "\t\t<artist id=\"" + this.performer.entityID + "\">" + this.performer.name + "</artist>\n"
                + "\t\t<album id=\"" + this.album.entityID + "\">" + this.album.name + "</album> </song>";
    }

    /**
     *Creates an SQL command
     * @return returns an sql command that inserts Song name, Sonf Id, Album ID, Artist Id, genre and mood.
     */
    public String toSQL(){

        return "insert into songs values(" + this.entityID + ", '" + stripPunct(this.name) + "'," + this.performer.entityID + "," + this.album.entityID + ", '" + this.genre+ "', '" + this.mood + "');";
    }
    /**
     * assigns the result set, used to search through the databse, to Song properties
     * @param rs Result set that retrives information from the databse
     * @throws SQLException
     */
    public void fromSQL(ResultSet rs) throws SQLException {
        this.entityID = rs.getInt("id");
        this.name = rs.getString("name");
        this.genre = rs.getString("genre");
        this.mood= rs.getString("mood");
    }

}


