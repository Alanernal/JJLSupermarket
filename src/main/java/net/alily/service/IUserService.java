package net.alily.service;

import net.alily.entity.*;
import net.alily.utils.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 用户服务接口 IUserService
 * Created by Alan on 5/15/17.
 */
public interface IUserService {
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
     * 查找用户信息
     * @param userName
     * @return User
     */
    public User findByUserName(String userName);

    /**
     * 查找用户详细信息
     * @param id
     * @return User
     */
    public UserDetailedInfo findByid(String id);


    /**
     * 登陆验证
     * @param user
     * @return boolean
     */
    public String checkLogin(User user);

    /**
     * 查询订单信息
     * @param id
     * @return
     */
    public List<OrderFrom> findOrderById(String id);

    /**
     * 下订单
     * @param orderFrom 订单
     * @return
     */
    public boolean placeAnOrder(OrderFrom orderFrom);

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    public UserInfo findUserInfoById(String id);

    /**
     * 查询货架
     * @return
     */
    public Map<String, List<Map<String, List<String>>>> queryStockingShelves();

    /**
     * 分页查询全部商品信息
     * @param pageBean PageBean
     */
    void queryCommodities(PageBean<Commodity> pageBean);

    /**
     * 购物车操作
     * @param cart
     * @param al
     * @return
     */
    public boolean alterCart(Cart cart, int al);

    /**
     * 查询购物车
     * @param userId
     * @return
     */
    public List<Cart> queryCarts(String userId);

    /**
     * 更新库存
     * @param commodity
     * @return
     */
    public boolean updateCommodityNumber(Commodity commodity);
}

