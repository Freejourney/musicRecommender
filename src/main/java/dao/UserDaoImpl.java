package dao;

import bean.Recommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements RecommendDao {

    @Override
    public void save(Recommend recommend) {
        this.executeSql("insert into rec_music values(?,?,?)", recommend.getId(), recommend.getUid(),recommend.getRecomtracks());
    }

    @Override
    public void update(Recommend recommend) {
        this.executeSql("update rec_music set uid=?, recomtracks=?, where id=?", recommend.getUid(), recommend.getRecomtracks(), recommend.getId());
    }

    @Override
    public void delete(String id) {
        this.executeSql("Delete from rec_music where id=?", id);
    }

    @Override
    public List<Recommend> getAllRecommend() {
        List<Recommend> recommends = new ArrayList<>();
        Connection conn = this.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select id, uid, recomtracks from rec_music order by id");
            rs = ps.executeQuery();
            while (rs.next()) {
                Recommend recommend = new Recommend();
                recommend.setId(rs.getString("id"));
                recommend.setUid(rs.getString("uid"));
                recommend.setRecomtracks(rs.getString("recomtracks"));
                recommends.add(recommend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }
        return recommends;
    }

    @Override
    public Recommend getRecommend(String id) {
        Connection conn = this.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Recommend recommend = new Recommend();
        try {
            ps = conn.prepareStatement("select id, uid, recomtracks from rec_music where id=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                recommend.setId(rs.getString("id"));
                recommend.setUid(rs.getString("uid"));
                recommend.setRecomtracks(rs.getString("recomtracks"));
            }
            return recommend;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }
        return recommend;
    }

    @Override
    public List<Recommend> getByPage(Integer page, Integer number) {
        List<Recommend> recommends=new ArrayList<Recommend>();
        Connection conn=this.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select id, uid, recomtracks from rec_music order by id limit ?,?");
            ps.setInt(1,(page-1)*number);
            ps.setInt(2, number);
            rs=ps.executeQuery();
            while(rs.next()) {
                Recommend recommend=new Recommend();
                recommend.setId(rs.getString("id"));
                recommend.setUid(rs.getString("uid"));
                recommend.setRecomtracks(rs.getString("recomtracks"));
                recommends.add(recommend);
            }

            return recommends;

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }

        return recommends;

    }
}
