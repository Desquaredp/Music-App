package edu.usfca.cs;

public class Song extends Entity {
    /**
     * Deep Mistry
     **/
    protected Album album;
    protected Artist performer;
    protected SongInterval duration;
    protected String genre;
    protected Boolean liked;


    public Song(String name, int length, String album, String performer, boolean liked) {
        super(name);
        this.duration = new SongInterval(length);
        this.performer = new Artist(performer);
        this.album = new Album(album, performer);
        this.liked = liked;
        genre = "";
    }

    public Song(String name, int length, String album, String performer, boolean liked, String genre) {
        super(name);
        this.duration = new SongInterval(length);
        this.performer = new Artist(performer);
        this.album = new Album(album, performer);
        this.liked = liked;
        this.genre = genre;
    }

    public Song(String name, int length) {
        super(name);
        duration = new SongInterval(length);
        genre = "";
    }

    public Song() {
    }

    public boolean equals(Song otherSong) {
        if ((this.album.name.equals(otherSong.getAlbum().name) &&
                this.name.equals(getName()) &&
                this.performer.name.equals(otherSong.getPerformer().name))) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLength(int length) {
        duration = new SongInterval(length);
    }

    public String showLength() {
        return duration.toString();
    }


    protected Album getAlbum() {
        return album;
    }

    protected void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getPerformer() {
        return performer;
    }

    public void setPerformer(Artist performer) {
        this.performer = performer;
    }

    public String toString() {
        return super.toString() + " " + this.performer + " " + this.album + " ";

    }


    public String XML() {
        return "\t<song id= \"" + this.entityID + "\">\n" +
                "\t\t<title>" + this.name + "</title>\n" +
                "\t\t<artist id=\"" + this.performer.entityID + "\">" + this.performer.name + "</artist>\n"
                + "\t\t<album id=\"" + this.album.entityID + "\">" + this.album.name + "</album> </song>";
    }

    public String JSON() {
        return "{\n" +
                "\t\"id\": \"" + this.entityID + "\",\n" +

                "\t\"title\": \"" + this.name + "\",\n" +
                "\t\"artist\": {\n" +
                "\t\t\"id\": \"" + this.performer.entityID + "\",\n" +
                "\t\t\"name\": \"" + this.performer.name + "\"\n" +
                "\t},\n" +
                "\t\"album\": {\n" +
                "\t\t\"id\": \"" + this.album.entityID + "\",\n" +
                "\t\t\"name\": \"" + this.album.name + "\"\n" +
                "\t}\n" +
                "}\n";
    }


}


