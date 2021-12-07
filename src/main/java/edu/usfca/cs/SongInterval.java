package edu.usfca.cs;

/**
 * Deep Mistry
 **/
public class SongInterval {
    private int length;

    public SongInterval(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String toString() {
        int min, sec;

        min = length / 60;
        sec = length % 60;

        String time = (min + ":" + sec);
        return time;
    }
}

