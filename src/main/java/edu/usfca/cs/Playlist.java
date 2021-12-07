package edu.usfca.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Deep Mistry
 **/
public class Playlist extends Entity {

    private ArrayList<Song> songs;

    public Playlist(String name) {
        super(name);
        songs = new ArrayList<Song>();
    }


    public boolean addSong(Song s) {
        return songs.add(s);
    }

    public boolean deleteSong(Song s) {
        return songs.remove(s);
    }

    public boolean findSong(Song s) {
        return songs.contains(s);
    }

    public List<Song> sortList() {
        List<Song> sort = songs.stream()
                .sorted(Comparator
                        .comparing(Song::getLiked))
                .collect(Collectors
                        .toList());
        return sort;
    }

    public ArrayList<Song> getSongs() {
        ArrayList<Song> list = new ArrayList<Song>();
        list.addAll(songs);
        return list;
    }

    public List<Song> mergeLists(List<Song> secondList) {

        List<Song> merger = new ArrayList<>(songs);
        merger.removeAll(secondList);
        songs.addAll(merger);
        return songs;

    }

    public List<Song> shuffleList() {
        Collections.shuffle(songs);
        return songs;
    }

    public List<Song> randomList(String g) {
        List<Song> randList =
                songs.stream()
                        .filter(s -> s.genre.equals(g))
                        .collect(Collectors.toList());
        return randList;
    }
}
