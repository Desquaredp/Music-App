package edu.usfca.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deep Mistry
 **/

public class Library extends Entity {

    public static ArrayList<Song> songs;
    public static ArrayList<Album> albums;
    public static ArrayList<Artist> artist;

    public static ArrayList<Album> getAlbums() {
        return albums;
    }

    public static void setAlbums(ArrayList<Album> albums) {
        Library.albums = albums;
    }

    public static ArrayList<Artist> getArtist() {
        return artist;
    }

    public static void setArtist(ArrayList<Artist> artist) {
        Library.artist = artist;
    }

    protected boolean liked;

    public Library() {
        songs = new ArrayList<>();
        albums = new ArrayList<>();
        artist = new ArrayList<>();
    }

    public static void addAlbums(Album a) {
        Boolean check =
                albums.stream()
                        .anyMatch(alb -> a.equals(alb));
        if (!check) {
            albums.add(a);
        }
    }

    public static void addArtist(Artist ar) {
        Boolean check =
                artist.stream()
                        .anyMatch(art -> ar.equals(art));
        if (!check) {
            artist.add(ar);
        }
    }

    public static void addSongs(Song s) {
        if (!songs.contains(s)) {
            songs.add(s);
            addAlbums(s.getAlbum());
            addArtist(s.getPerformer());
        }
    }

    public void addSongsXML(Song s) {
        if (!songs.contains(s)) {
            songs.add(s);
//            addAlbums(s.getAlbum());
            addArtist(s.getPerformer());
        }

    }

    public static void duplicates() {

        ArrayList<Integer> elID = new ArrayList<>();

        dup duptest = (Song sng) -> {

            if (elID.contains(sng.entityID)) {
                return false;
            } else {

                for (Song s : songs) {

                    if (sng.entityID != s.entityID) {
                        if (sng
                                .getName()
                                .equals(s
                                        .getName()) &&
                                (sng
                                        .getAlbum()
                                        .getName().equals(s
                                                .getAlbum()
                                                .getName()) ||
                                        sng
                                                .getPerformer()
                                                .getName()
                                                .equals(s
                                                        .getPerformer()
                                                        .getName()))) {
                            elID.add(s.entityID);
                            return true;
                        } else if (
                                sng
                                        .getAlbum()
                                        .getName()
                                        .equals(s
                                                .getAlbum()
                                                .getName()) &&
                                        sng
                                                .getPerformer()
                                                .getName()
                                                .equals(s
                                                        .getPerformer()
                                                        .getName()) &&
                                        sng
                                                .getName()
                                                .toLowerCase()
                                                .replaceAll("\\p{Punct}", "")
                                                .equals(s
                                                        .getName()
                                                        .toLowerCase()
                                                        .replaceAll("\\p{Punct}", ""))) {
                            elID.add(s.entityID);
                            return true;
                        }

                    }
                }
            }
            return false;
        };


        Boolean disnt =
                songs.stream()
                        .anyMatch(sng -> duptest.duptes(sng));

        if (disnt == true) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("There are duplicates in the library. Do you wish to remove them? (y/n)");
            String call = myObj.nextLine();
            if (call.equals("y")) {
                songs.removeIf(sng -> elID.contains(sng.entityID));
                System.out.println(songs);
            }
        } else {
            System.out.println("There are no duplicate songs in your Library!");
        }
    }

    public boolean findSongs(Song s) {
        return songs.contains(s);
    }

    public boolean getLiked(boolean liked) {

        if (liked == true) {
            return true;
        }
        return false;

    }


    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void XML() {

        try {
            FileWriter myWriter = new FileWriter("XMLCreate.xml");
            myWriter.write("<Library>\n");
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

            myWriter.write("</Library>");

            myWriter.close();
            System.out.println("Successfully written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {


        Library lib1 = new Library();
        Song s1, s2, s3, s4;

        s1 = new Song("Happy Birthday ", 184, "album1", "perfm1", true);
        s2 = new Song("Hel,ter Skelter", 184, "album2", "perfm2", false);
        s3 = new Song("HElter Ske", 184, "album1", "perfm1", false);
        s4 = new Song("Helter Skelter", 184, "album2", "perfm2", false);

        lib1.addSongs(s1);
        lib1.addSongs(s2);
        lib1.addSongs(s3);
        lib1.addSongs(s4);

        lib1.duplicates();

        System.out.println("your song list is: ");
        for (Song s : songs
             ) {
            System.out.println(s);
        }


        for (int i = 0; i < lib1.albums.size(); i++) {
            System.out.println(lib1.albums.get(i).getName());
        }

        for (int i = 0; i < lib1.artist.size(); i++) {
            System.out.println(lib1.artist.get(i).getName());
        }
    }


}


