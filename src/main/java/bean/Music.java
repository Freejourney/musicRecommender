package bean;

public class Music {

    private String trackid;
    private String artist;
    private String song;
    private int favorites;

    public Music() {

    }

    public Music(String trackid, String artist, String song, int favorites) {
        this.trackid = trackid;
        this.artist = artist;
        this.song = song;
        this.favorites = favorites;
    }

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "Music{" +
                "trackid='" + trackid + '\'' +
                ", artist='" + artist + '\'' +
                ", song='" + song + '\'' +
                ", favorites=" + favorites +
                '}';
    }
}
