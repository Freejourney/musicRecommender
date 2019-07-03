package dao;

import bean.User;

import java.util.List;

public interface UserDao {

    /**
     * 保存用户-trackids推荐
     * @param user
     */
    public void save(User user);

    /**
     * 更新用户-trackids推荐
     * @param user
     */
    public void update(User user);

    /**
     * 删除用户-trackids推荐
     * @param uid
     */
    public void delete(String uid);

    /**
     * 查询所有用户-trackids推荐
     * @return
     */
    public List<User> getAllUser();

    /**
     * 根据id查询用户-trackids推荐
     * @param uid
     * @return
     */
    public User getUser(String uid);

    /**
     * 根据页数查询用户-trackids推荐，每页显示number条记录
     * @param page
     * @param number
     * @return
     */
    public List<User> getByPage(Integer page, Integer number);
}
