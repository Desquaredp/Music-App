package edu.usfca.cs;

import java.util.ArrayList;
/**
 * Deep Mistry
 **/
public class Artist extends Entity {

    protected ArrayList<Song> songs;
    protected ArrayList<Album> albums;

    public boolean equals(Artist otherArtist) {
        if (this.name.equals(otherArtist.name)) {
            return true;

        } else {
            return false;
        }
    }

    public Artist(String name) {
        super(name);
    }

    protected ArrayList<Song> getSongs() {
        return songs;
    }

    protected void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    protected ArrayList<Album> getAlbums() {
        return albums;
    }

    protected void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void addSong(Song s) {
        songs.add(s);
    }

    public String XML() {
        return "\t<artist id= \"" + this.entityID + "\">\n" +
                "\t\t<name>" + this.name + "</name>\n" +
                "\t</artist>";
    }

    public String JSON() {
        return "{" +
                "\t\"id\": \"" + this.entityID + "\",\n" +
                "\t\"name\": \"" + this.name + "\"\n" +
                "}\n";
    }


}
