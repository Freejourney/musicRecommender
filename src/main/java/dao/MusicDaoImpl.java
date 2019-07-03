package dao;

import bean.Music;
import com.google.common.io.Closeables;
import org.apache.mahout.common.iterator.FileLineIterator;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MusicDaoImpl extends BaseDao implements MusicDao {


    public List<Music> saveMostFavorites(int num) {
        List<Music> musics = new ArrayList<>();
        Connection conn = this.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from meta_tracks order by favorites Desc limit ?;");
            ps.setInt(1,num);
            rs = ps.executeQuery();
            while (rs.next()) {
                Music music = new Music();
                music.setTrackid(rs.getString("trackid"));
                music.setArtist(rs.getString("artist"));
                music.setSong(rs.getString("song"));
                music.setFavorites(rs.getInt("favorites"));
                musics.add(music);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }
        return musics;
    }

    @Override
    public void save(Music music) {
        this.executeSql("insert into meta_tracks values(?,?,?,?)", music.getTrackid(), music.getArtist(),music.getSong(), music.getFavorites());
    }

    @Override
    public void update(Music music) {
        this.executeSql("update meta_tracks set artist=?, song=?, favoriates=?, where trackid=?", music.getArtist(), music.getSong(), music.getFavorites(), music.getTrackid());
    }

    @Override
    public void delete(String trackid) {
        this.executeSql("Delete from meta_tracks where trackid=?", trackid);
    }

    @Override
    public List<Music> getAllMusic() {
        List<Music> musics = new ArrayList<>();
        Connection conn = this.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select trackid, artist, song, favorites from meta_tracks order by trackid");
            rs = ps.executeQuery();
            while (rs.next()) {
                Music music = new Music();
                music.setTrackid(rs.getString("trackid"));
                music.setArtist(rs.getString("artist"));
                music.setSong(rs.getString("song"));
                music.setFavorites(rs.getInt("favorites"));
                musics.add(music);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }
        return musics;
    }

    @Override
    public Music getMusic(String trackid) {
        Connection conn = this.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Music music = new Music();
        try {
            ps = conn.prepareStatement("select trackid, artist, song, favorites from meta_tracks where trackid=?");
            ps.setString(1, trackid);
            rs = ps.executeQuery();
            if (rs.next()) {
                music.setTrackid(rs.getString("trackid"));
                music.setArtist(rs.getString("artist"));
                music.setSong(rs.getString("song"));
                music.setFavorites(rs.getInt("favorites"));
            }
            return music;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }
        return music;
    }

    @Override
    public List<Music> getByPage(Integer page, Integer number) {
        List<Music> musics=new ArrayList<Music>();
        Connection conn=this.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select trackid, artist, song, favorites from meta_tracks order by trackid limit ?,?");
            ps.setInt(1,(page-1)*number);
            ps.setInt(2, number);
            rs=ps.executeQuery();
            while(rs.next()) {
                Music music=new Music();
                music.setTrackid(rs.getString("trackid"));
                music.setArtist(rs.getString("artist"));
                music.setSong(rs.getString("song"));
                music.setFavorites(rs.getInt("favorites"));
                musics.add(music);
            }

            return musics;

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }

        return musics;

    }

    public void save2db(MusicDaoImpl musicDaoimpl) throws IOException {
        File file = new File("meta_tracks.csv");
        FileLineIterator iterator = new FileLineIterator(file, false);
        String line = iterator.next();
        line = iterator.next();
        Long i = Long.valueOf(1);
        while (!line.isEmpty()) {
            String[] m = line.split(",");
            Music music = new Music(m[0], m[1], m[2], Integer.valueOf(m[3]));
            musicDaoimpl.save(music);
            System.out.print(i+++" ");
            if (iterator.hasNext()) {
                line = iterator.next();
            } else {
                break;
            }
        }
        Closeables.close(iterator, true);
    }


    public static void main(String[] args) throws IOException {
        MusicDaoImpl musicDaoimpl = new MusicDaoImpl();
        List<Music> musics = musicDaoimpl.saveMostFavorites(200);
        for (Music music : musics) {
            System.out.println(music.toString());
        }
    }
}
