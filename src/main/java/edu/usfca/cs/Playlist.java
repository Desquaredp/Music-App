package edu.usfca.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * this class contains all the properties of Playlist and can be used to create a playlist
 * it extends from Entity
 * @author Deep Mistry
 **/
public class Playlist extends Entity {


    private static ArrayList<Song> songs;
    public static ArrayList<Album> albums;
    public static ArrayList<Artist> artist;

    /**
     * Constructor
     * @param name takes in the name of the Playlist
     */
    public Playlist(String name) {
        super(name);
        songs = new ArrayList<Song>();
        albums = new ArrayList<>();
        artist = new ArrayList<>();

    }

    /**
     * add albums to the Playlist
     * @param a takes in an album object and checks if it already exists. In case it doesn't, it's added to the
     *          arraylist albums
     */
    public static void addAlbums(Album a) {
        Boolean check =
                albums.stream()
                        .anyMatch(alb -> a.equals(alb));
        if (!check) {
            albums.add(a);
        }
    }
    /**
     * add artist to the Playlist
     * @param ar takes in an artist object and checks if it already exists. In case it doesn't, it's added to the
     *          arraylist artist
     */
    public static void addArtist(Artist ar) {
        Boolean check =
                artist.stream()
                        .anyMatch(art -> ar.equals(art));
        if (!check) {
            artist.add(ar);
        }
    }

    /**
     * add songs to the Playlist
     * @param s takes in a Song object and checks if it already exists. In case it doesn't, it's added to the
     *          arraylist songs. It calls the album and artist methods.
     */

    public static void addSongs(Song s) {
        if (!songs.contains(s)) {
            songs.add(s);
            addAlbums(s.getAlbum());
            addArtist(s.getPerformer());
        }
    }

    /**
     * delete a song
     * @param s takes in the song that needs to be deleted
     * @return returns the arraylist songs that has been edited
     */
    public boolean deleteSong(Song s) {
        return songs.remove(s);
    }

    /**
     * checks if a song is in the arraylist and returns a boolean vlaue accordingly
     * @param s takes in the song object that needs to be checked against the database
     * @return returns boolean value
     */
    public boolean findSong(Song s) {
        return songs.contains(s);
    }

    /**
     * sorts the songs arraylist according a given constraint
     * @return returns the sorted list
     */
    public List<Song> sortList() {
        List<Song> sort = songs.stream()
                .sorted(Comparator
                        .comparing(Song::getLiked))
                .collect(Collectors
                        .toList());
        return sort;
    }

    /**
     * getter for songs
     * @return returns a list of all the song objects in the arraylist songs
     */
    public ArrayList<Song> getSongs() {
        ArrayList<Song> list = new ArrayList<Song>();
        list.addAll(songs);
        return list;
    }

    /**
     * mereges two playlists of songs
     * @param secondList takes in another list of songs that is to be merged with the songs arraylist
     * @return returns the songs arrraylist with song objects from the other list added to it
     */
    public List<Song> mergeLists(List<Song> secondList) {

        List<Song> merger = new ArrayList<>(songs);
        merger.removeAll(secondList);
        songs.addAll(merger);
        return songs;

    }

    /**
     * shuffles the lsit of songs
     * @return returns a shuffled list of songs
     */
    public List<Song> shuffleList() {
        Collections.shuffle(songs);
        return songs;
    }

    /**
     * creates a random playlist of songs
     * @param g takes in a string value of the genre
     * @return returns a playlist of songs of the specified genre
     */
    public List<Song> randomList(String g) {
        List<Song> randList =
                songs.stream()
                        .filter(s -> s.genre.equals(g))
                        .collect(Collectors.toList());
        return randList;
    }

    /**
     * creates a file XMLCreate.xml and adds the playlist to it as an XML
     */
    public void XML() {

        try {
            FileWriter myWriter = new FileWriter("XMLCreate.xml");
            myWriter.write("<Playlist>\n");
            myWriter.write("<songs>\n");
            for (Song s : songs) {
                myWriter.write(s.XML() + "\n");
            }
            myWriter.write("</songs>\n");

            myWriter.write("<artists>\n");
            for (Artist art : artist) {
                myWriter.write(art.XML() + "\n");
            }
            myWriter.write("</artists>\n");

            myWriter.write("<albums>\n");
            for (Album a : albums) {
                myWriter.write(a.XML() + "\n");
            }
            myWriter.write("</albums>\n");

            myWriter.write("</Playlist>");

            myWriter.close();
            System.out.println("Successfully written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


}
