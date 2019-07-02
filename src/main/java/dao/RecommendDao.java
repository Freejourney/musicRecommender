package dao;

import bean.Recommend;

import java.util.List;

public interface RecommendDao {

    /**
     * 保存用户-trackids推荐
     * @param recommend
     */
    public void save(Recommend recommend);

    /**
     * 更新用户-trackids推荐
     * @param recommend
     */
    public void update(Recommend recommend);

    /**
     * 删除用户-trackids推荐
     * @param id
     */
    public void delete(String id);

    /**
     * 查询所有用户-trackids推荐
     * @return
     */
    public List<Recommend> getAllRecommend();

    /**
     * 根据id查询用户-trackids推荐
     * @param id
     * @return
     */
    public Recommend getRecommend(String id);

    /**
     * 根据页数查询用户-trackids推荐，每页显示number条记录
     * @param page
     * @param number
     * @return
     */
    public List<Recommend> getByPage(Integer page, Integer number);
}
