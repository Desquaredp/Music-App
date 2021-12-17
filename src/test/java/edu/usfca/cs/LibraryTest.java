package edu.usfca.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Deep Mistry
 **/
class LibraryTest {
    Library lib1;
    Song s1, s2, s3, s4;


    @BeforeEach
    void setup() {
        lib1 = new Library();
        s1 = new Song("Happy Birthday ", 184, "album1", "perfm1", true);
        s2 = new Song("Hel,ter Skelter", 184, "album2", "perfm2", false);
        s3 = new Song("HElter Ske", 184, "album1", "perfm1", false);
        s4 = new Song("Helter Skelter", 184, "album2", "perfm2", false);

    }

    @Test
    void duplicates() {
        /**Since Junit doesn't permit user input, the test for this is
         * writting in the psvm of the Library class**/

        lib1.addSongs(s1);
        lib1.addSongs(s2);
        lib1.addSongs(s3);
        lib1.addSongs(s4);
        lib1.duplicates();
    }

    @Test
    void findSongs() {
        lib1.addSongs(s1);
        assertTrue(lib1.findSongs(s1));

        assertFalse(lib1.findSongs(s2));

    }

    @Test
    void XML() {
        lib1.addSongs(s1);
        lib1.addSongs(s2);
        lib1.addSongs(s3);
        lib1.addSongs(s4);
        lib1.XML();
    }



    //
    @Test
    void addSongs() {

        lib1.addSongs(s1);
        lib1.addSongs(s2);
        lib1.addSongs(s3);
        lib1.addSongs(s4);

        for (int i = 0; i < lib1.getAlbums().size(); i++) {
            System.out.println(lib1.getAlbums().get(i).getName());
        }

        for (int i = 0; i < lib1.getArtist().size(); i++) {
            System.out.println(lib1.getArtist().get(i).getName());
        }

    }

}