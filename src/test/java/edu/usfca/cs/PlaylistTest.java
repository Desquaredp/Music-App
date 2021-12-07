package edu.usfca.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Deep Mistry
 **/
class PlaylistTest {
    Playlist pl1;
    Playlist pl2;
    Playlist pl3;
    Song s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

    @BeforeEach
    void setUp() {

        s1 = new Song("Song1", 40, "Album 1", "artist1", true, "rock");
        s2 = new Song("Song2", 339, "Album 2", "artist2", false, "pop");
        s3 = new Song("Song 3", 40, "Album 3", "artist3", true, "pop");
        s4 = new Song("Song 4", 339, "Album 4", "artist4", true, "jazz");
        s5 = new Song("Song 5", 40, "Album 5", "artist5", true, "jazz");
        s6 = new Song("Song 6", 339, "Album 6", "artist6", true, "jazz");
        s7 = new Song("Song 7", 40, "Album 7", "artist7", false, "pop");
        s8 = new Song("Song 8", 339, "Album 8", "artist8", false, "jazz");

        pl1 = new Playlist("list1");
        pl2 = new Playlist("list2");
        pl3 = new Playlist("list3");

        pl1.addSong(s1);
        pl1.addSong(s2);
        pl1.addSong(s3);
        pl1.addSong(s4);

        pl2.addSong(s5);
        pl2.addSong(s6);
        pl2.addSong(s7);
        pl2.addSong(s8);

        pl3.addSong(s1);
        pl3.addSong(s2);
        pl3.addSong(s3);
        pl3.addSong(s4);
        pl3.addSong(s5);
        pl3.addSong(s6);
        pl3.addSong(s7);
        pl3.addSong(s8);

        s9 = new Song("Song 1", 40, "Album 1", "Artist 1", true);
        s10 = new Song("Song 10", 40, "Album 1", "artist10", true);

    }


    @Test
    void testAddSong() {
        pl1.addSong(s10);
        pl1.getSongs();

    }

    @Test
    void testDeleteSong() {
        pl1.deleteSong(s1);
        pl1.getSongs();
        assertFalse(pl1.findSong(s1));
    }

    @Test
    void sortList() {
        pl1.getSongs();
        pl1.sortList();
        System.out.println(pl1);

    }

    @Test
    void mergeLists() throws Exception {
        pl2.addSong(s9);
        pl1.mergeLists(pl2.getSongs());
    }

    @Test
    void shuffleList() {
        System.out.println(pl1.getSongs());
        pl1.shuffleList();
        System.out.println(pl1.getSongs());
    }

    @Test
    void findSong() {
        assertTrue(pl1.findSong(s1));
    }

    @Test
    void randomList() {

        System.out.println(pl3.randomList("rock"));
    }
}