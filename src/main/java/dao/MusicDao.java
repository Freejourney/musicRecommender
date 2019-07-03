package dao;

import bean.Music;

import java.util.List;

public interface MusicDao {

    /**
     * 保存用户-trackids推荐
     * @param music
     */
    public void save(Music music);

    /**
     * 更新用户-trackids推荐
     * @param music
     */
    public void update(Music music);

    /**
     * 删除用户-trackids推荐
     * @param trackid
     */
    public void delete(String trackid);

    /**
     * 查询所有用户-trackids推荐
     * @return
     */
    public List<Music> getAllMusic();

    /**
     * 根据id查询用户-trackids推荐
     * @param trackid
     * @return
     */
    public Music getMusic(String trackid);

    /**
     * 根据页数查询用户-trackids推荐，每页显示number条记录
     * @param page
     * @param number
     * @return
     */
    public List<Music> getByPage(Integer page, Integer number);
}
