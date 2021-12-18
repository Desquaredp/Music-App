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
 *
 *
 * This class is contains all the properties of an album.
 * This class extends Entity
 * @author Deep Mistry
 *
 **/
public class Album extends Entity {
    protected ArrayList<Song> songs;
    protected Artist artist;

    public String getName() {
        return name;
    }

    /**
     *Album Constructor
     * @param name Name of the Album
     * @param artist Name of the Artist the Album belongs to
     */
    public Album(String name, String artist) {
        super(name);
        songs = new ArrayList<Song>();
        this.artist = new Artist(artist);
    }

    /**
     * Album constructor
     * @param name Name of the Album
     */

    public Album(String name) {
        super(name);
        songs = new ArrayList<Song>();
        this.artist = new Artist();

    }

    /**
     * Empty Album Constructor
     */
    public Album(){

    }

    /**
     * This method checks if an Album is already exists. It returns a boolean value
     * @param otherAlbum The Album to be checked
     * @return returns a boolean value
     */
    public boolean equals(Album otherAlbum) {
        if ((this.artist.name.equalsIgnoreCase(otherAlbum.getArtist().name) &&
                this.name.equals(getName()))) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * Getter for songs
     * @return returns arraylist of songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Setter for songs
     * @param songs takes in an arraylist of songs
     */
    protected void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    /**
     * Getter for Artist
     * @return returns the artist object
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * setter for Artist
     * @param artist takes in an artist object as an argument
     */
    protected void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Generates an XML script
     * @return returns a XML script with the name and the id of the Album object
     */
    public String XML() {
        return "\t<album id= \"" + this.entityID + "\">\n" +
                "\t\t<title>" + this.name + "</title>\n" +
                "\t</album>";
    }


    /**
     *Creates an SQL command
     * @return returns an sql command that inserts Album name, Album Id, Artist Id, genre and mood.
     */
    public String toSQL(){
        return "insert into albums(id, name, artist, genre, mood)  values(" + this.entityID + ", '" + stripPunct(this.name) + "','" + this.artist.entityID + "','"+ this.genre+ "','"+ this.mood +"');";
    }

    /**
     * assigns the result set, used to search through the databse, to Album properties
     * @param rs Result set that retrives information from the databse
     * @throws SQLException
     */
    public void fromSQL(ResultSet rs) throws SQLException {
        this.entityID = rs.getInt("id");
        this.name = rs.getString("name");
        this.genre = rs.getString("genre");
        this.mood = rs.getString("mood");
    }

    /**
     * This method retrives the artist's audioDB Id that can be used to obtain the albums belonging to the Artist
     * @return returns a string containing the artist audioDB Id
     */
    public String artistAudioDB() {
        String requestURL = "https://www.theaudiodb.com/api/v1/json/2/search.php?s=";
        String artist = this.artist.name;
        StringBuilder response = new StringBuilder();
        URL u;
        try {
            u = new URL(requestURL + artist);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
            return null;
        }
        try {
            URLConnection connection = u.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int code = httpConnection.getResponseCode();

            String message = httpConnection.getResponseMessage();

            if (code != HttpURLConnection.HTTP_OK) {
                return null;
            }
            InputStream instream = connection.getInputStream();
            Scanner in = new Scanner(instream);
            while (in.hasNextLine()) {
                response.append(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading response");
            return null;
        }
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response.toString());
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray artists = (JSONArray)jsonObject.get("artists"); // get the list of all artists returned.
            JSONObject beatles =(JSONObject) artists.get(0);  // I happen to know that the beatles is the first entry. Otherwise I'd iterate.
            String id = (String)beatles.get("idArtist");

            return id;



        } catch(ParseException e) {
            System.out.println("Error parsing JSON");
            return null;
        }}

    /**
     *       This method fetches all the properties of an Album from AudioDB and assigns them to appropriate facets
     *       of the Artist object
     *
     * @param ID takes in a string of audioDB ArtistID
     */
        public void albumAudioDB(String ID) {
//            String requestURL = "https://www.theaudiodb.com/api/v1/json/{APIKEY}/album.php?i=" + ID;
//            String artist = this.artist.name;
            StringBuilder response = new StringBuilder();
            URL u;
            try {
                u = new URL("https://www.theaudiodb.com/api/v1/json/2/album.php?i=" + ID);
            } catch (MalformedURLException e) {
                System.out.println("Malformed URL");
                return;
            }
            try {
                URLConnection connection = u.openConnection();
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                int code = httpConnection.getResponseCode();

                String message = httpConnection.getResponseMessage();

                if (code != HttpURLConnection.HTTP_OK) {
                    return ;
                }
                InputStream instream = connection.getInputStream();
                Scanner in = new Scanner(instream);
                while (in.hasNextLine()) {
                    response.append(in.nextLine());
                }
            } catch (IOException e) {
                System.out.println("Error reading response");
                return ;
            }
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response.toString());
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray album = (JSONArray) jsonObject.get("album"); // get the list of all artists returned.

                for (int i = 0; i < album.size(); i++) {
                    JSONObject beatles =(JSONObject) album.get(i);  // I happen to know that the beatles is the first entry. Otherwise I'd iterate.
                    String albName = (String)beatles.get("strAlbum");
                    if(albName.equalsIgnoreCase(this.name)){
//®;
                        this.name = (String)beatles.get("strAlbum");
                        this.mood = (String)beatles.get("strMood");
                        this.genre = (String)beatles.get("strGenre");
                        this.entityID = parseInt((String)beatles.get("idAlbum"));
                        this.artist.entityID = Integer.parseInt(ID);

                        break;
                    }
                 }



            } catch (ParseException e) {
                System.out.println("Error parsing JSON");
                return;
            }

        }

    /**
     * fetches all the songs from an ALbum from audioDB.
     * @return returns an arraylist of Song objects that belong to the Album
     */
    public ArrayList<Song> addALbumSongs() {

            ArrayList<Song> sng = new ArrayList<Song>();
                    StringBuilder response = new StringBuilder();
            URL u;
            try {
                u = new URL("https://www.theaudiodb.com/api/v1/json/2/track.php?m=" + this.entityID);
            } catch (MalformedURLException e) {
                System.out.println("Malformed URL");
                return null;
            }
            try {
                URLConnection connection = u.openConnection();
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                int code = httpConnection.getResponseCode();

                String message = httpConnection.getResponseMessage();

                if (code != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                InputStream instream = connection.getInputStream();
                Scanner in = new Scanner(instream);
                while (in.hasNextLine()) {
                    response.append(in.nextLine());
                }
            } catch (IOException e) {
                System.out.println("Error reading response");
                return null;
            }
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response.toString());
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray track = (JSONArray) jsonObject.get("track"); // get the list of all artists returned.

                for (int i = 0; i < track.size(); i++) {
                    JSONObject beatles = (JSONObject) track.get(i);  // I happen to know that the beatles is the first entry. Otherwise I'd iterate.
                    String trkName = (String) beatles.get("strTrack");
                    String albName = (String) beatles.get("strAlbum");
                    String artName = (String) beatles.get("strArtist");


                    sng.add(i, new Song(trkName, albName, artName));
                    sng.get(i).entityID = parseInt((String) beatles.get("idTrack"));
                    sng.get(i).performer.entityID = parseInt((String) beatles.get("idArtist"));
                    sng.get(i).album.entityID = this.entityID;

                    if(i == 0 ){
                        sng.get(0).mood = (String)beatles.get("strMood");
                        sng.get(0).genre = (String)beatles.get("strGenre");
                    }else{
                        sng.get(i).mood = sng.get(0).mood;
                        sng.get(i).genre = sng.get(0).genre;
                    }

                }

                return sng;


            } catch (ParseException e) {
                System.out.println("Error parsing JSON");
                return null;
            }


        }



}
