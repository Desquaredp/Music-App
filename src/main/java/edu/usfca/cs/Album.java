package edu.usfca.cs;

import java.util.ArrayList;

/**
 * Deep Mistry
 **/
public class Album extends Entity {
    protected ArrayList<Song> songs;
    protected Artist artist;

    public String getName() {
        return name;
    }

    public Album(String name, String artist) {
        super(name);
        songs = new ArrayList<Song>();
        this.artist = new Artist(artist);
    }

    public Album(String name) {
        super(name);
        songs = new ArrayList<Song>();
    }

    public boolean equals(Album otherAlbum) {
        if ((this.artist.name.equals(otherAlbum.getArtist().name) &&
                this.name.equals(getName()))) {
            return true;

        } else {
            return false;
        }
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    protected void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public Artist getArtist() {
        return artist;
    }

    protected void setArtist(Artist artist) {
        this.artist = artist;
    }


    public String toHTML() {
        return super.toHTML() + " <br> " + this.artist + " </br>";
    }

    public String XML() {
        return "\t<album id= \"" + this.entityID + "\">\n" +
                "\t\t<title>" + this.name + "</title>\n" +
                "\t</album>";
    }

    public String JSON() {
        return "{" +
                "\t\"id\": \"" + this.entityID + "\",\n" +
                "\t\"name\": \"" + this.name + "\"\n" +
                "}\n";
    }
}
