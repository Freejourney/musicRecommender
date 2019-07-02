package bean;

public class Recommend {
    public String id;
    public String uid;
    public String recomtracks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRecomtracks() {
        return recomtracks;
    }

    public void setRecomtracks(String recomtracks) {
        this.recomtracks = recomtracks;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", recomtracks='" + recomtracks + '\'' +
                '}';
    }
}
