package musicRecommender;

public class Track {

    private String trackID;
    private String Artist;
    private String Song;
    private int favoriates;

    public Track(String trackID, String artist, String song, int favoriates) {
        this.trackID = trackID;
        Artist = artist;
        Song = song;
        this.favoriates = favoriates;
    }

    public Track(String trackid) {
        this.trackID = trackid;
    }

    public String getTrackID() {
        return trackID;
    }

    public String getArtist() {
        return Artist;
    }

    public String getSong() {
        return Song;
    }

    public int getFavoriates() {
        return favoriates;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Track)obj).trackID.equals(this.trackID);
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackID='" + trackID + '\'' +
                ", Artist='" + Artist + '\'' +
                ", Song='" + Song + '\'' +
                ", favoriates=" + favoriates +
                '}';
    }
}
