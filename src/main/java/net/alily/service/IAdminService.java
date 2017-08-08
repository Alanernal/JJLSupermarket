package net.alily.service;

import net.alily.entity.*;
import net.alily.utils.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 管理员服务接口
 * Created by Alan on 5/22/17.
 */
public interface IAdminService {

    /**
     * 登陆验证
     * @param user User
     * @return id
     */
    public String checkLogin(User user);

    /**
     * 保存用户信息
     * @param user
     */
    public boolean save(User user);

    /**
     * 更新用户信息
     * @param user
     * @return boolean
     */
    public boolean update(User user, UserDetailedInfo userDetailedInfo);

    /**
     * 删除用户信息
     * @param id
     * @return boolean
     */
    public boolean delete(int id);

    /**
     * 根据用户名查找用户信息
     * @param userName
     * @return User
     */
    public User findByUserName(String userName);

    /**
     * 根据id查找用户详细信息
     * @param id
     * @return User
     */
    public UserDetailedInfo findById(String id);

    /**
     * 分页查询全部用户信息
     * @param pageBean PageBean
     */
    void queryUsers(PageBean<UserDetailedInfo> pageBean);

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    public UserInfo findUserInfoById(String id);

    /**
     * 查询订单信息
     * @param id
     * @return list
     */
    public List<OrderFrom> findOrderById(String id);

    /**
     * 添加商品
     * @param commodity 商品
     * @return boolean
     */
    public boolean insertCommodity(Commodity commodity);

    /**
     * 根据条件名查出所有的条件
     * @param conditionName
     * @return
     */
    public List<String> queryCondition(String conditionName);

    /**
     * 分页查询全部商品信息
     * @param pageBean PageBean
     */
    void queryCommodities(PageBean<Commodity> pageBean);

    /**
     * 根据id查找商品详细信息
     * @param id
     * @return User
     */
    public Commodity findCommodityById(String id);

    /**
     * 删除商品
     * @param id
     * @return
     */
    public boolean commodityDelete(String id);

    /**
     * 查询订单
     * @param pageBean
     */
    public void queryOrders(PageBean<OrderFrom> pageBean);

    /**
     * 根据订单ID查询订单
     * @param id
     * @return
     */
    public OrderFrom queryOrderById(String id);

    /**
     * 更新订单
     * @param order
     * @return
     */
    public boolean updateOrder (OrderFrom order);

    /**
     * 删除订单
     * @param id
     * @return
     */
    public boolean deleteOrder(String id);

}
