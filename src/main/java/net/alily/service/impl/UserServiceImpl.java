package net.alily.service.impl;

import net.alily.dao.IUserDao;
import net.alily.dao.impl.UserDaoImpl;
import net.alily.entity.*;
import net.alily.factory.BeanFactory;
import net.alily.service.IUserService;
import net.alily.utils.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类 UserServiceImpl
 * Created by Alan on 5/15/17.
 */
public class UserServiceImpl implements IUserService {
    private IUserDao userDaoImpl = BeanFactory.getInstance("userDao", UserDaoImpl.class);

    public boolean save(User user) {
        return userDaoImpl.save(user);
    }

    public boolean update(User user, UserDetailedInfo userDetailedInfo) {
        return userDaoImpl.update(user, userDetailedInfo);
    }

    public boolean delete(int id) {
        return userDaoImpl.delete(id);
    }

    public User findByUserName(String userName) {
        return userDaoImpl.findByUserName(userName);
    }

    public UserDetailedInfo findByid(String id) {
        return userDaoImpl.findById(id);
    }

    public String checkLogin(User user) {
        return userDaoImpl.checkLogin(user);
    }

    public List<OrderFrom> findOrderById(String id) {
        return userDaoImpl.findOrderById(id);
    }

    public boolean placeAnOrder(OrderFrom orderFrom) {
        return userDaoImpl.placeAnOrder(orderFrom);
    }

    @Override
    public UserInfo findUserInfoById(String id) {
        return userDaoImpl.findUserInfoById(id);
    }

    @Override
    public Map<String, List<Map<String, List<String>>>> queryStockingShelves() {
        return userDaoImpl.queryStockingShelves();
    }

    @Override
    public void queryCommodities(PageBean<Commodity> pageBean) {
        userDaoImpl.queryCommodities(pageBean);
    }

    @Override
    public boolean alterCart(Cart cart, int al) {
        return userDaoImpl.alterCart(cart, al);
    }

    @Override
    public List<Cart> queryCarts(String userId) {
        return userDaoImpl.queryCarts(userId);
    }

    @Override
    public boolean updateCommodityNumber(Commodity commodity) {
        return userDaoImpl.updateCommodityNumber(commodity);
    }


}
