package net.alily.service.impl;

import net.alily.dao.IAdminDao;
import net.alily.dao.impl.AdminDaoImpl;
import net.alily.entity.*;
import net.alily.factory.BeanFactory;
import net.alily.service.IAdminService;
import net.alily.utils.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 管理员服务实现类
 * Created by Alan on 5/22/17.
 */
public class AdminServiceImpl implements IAdminService {
    private IAdminDao adminDao = BeanFactory.getInstance("adminDao", AdminDaoImpl.class);

    public String checkLogin(User user) {
        return adminDao.checkLogin(user);
    }

    public boolean save(User user) {
        return adminDao.save(user);
    }

    public boolean update(User user, UserDetailedInfo userDetailedInfo) {
        return adminDao.update(user, userDetailedInfo);
    }

    public boolean delete(int id) {
        return adminDao.delete(id);
    }

    public User findByUserName(String userName) {
        return adminDao.findByUserName(userName);
    }

    public UserDetailedInfo findById(String id) {
        return adminDao.findById(id);
    }

    public void queryUsers(PageBean<UserDetailedInfo> pageBean) {adminDao.queryUsers(pageBean);}

    @Override
    public UserInfo findUserInfoById(String id) {
        return adminDao.findUserInfoById(id);
    }

    public List<OrderFrom> findOrderById(String id) {
        return adminDao.findOrderById(id);
    }

    public boolean insertCommodity(Commodity commodity) {
        return adminDao.insertCommodity(commodity);
    }

    @Override
    public List<String> queryCondition(String conditionName) {
        return adminDao.queryCondition(conditionName);
    }

    @Override
    public void queryCommodities(PageBean<Commodity> pageBean) {
        adminDao.queryCommodities(pageBean);
    }

    @Override
    public Commodity findCommodityById(String id) {
        return adminDao.findCommodityById(id);
    }

    @Override
    public boolean commodityDelete(String id) {
        return adminDao.commodityDelete(id);
    }

    @Override
    public void queryOrders(PageBean<OrderFrom> pageBean) {
        adminDao.queryOrders(pageBean);
    }

    @Override
    public OrderFrom queryOrderById(String id) {
        return adminDao.queryOrderById(id);
    }

    @Override
    public boolean updateOrder(OrderFrom order) {
        return adminDao.updateOrder(order);
    }

    @Override
    public boolean deleteOrder(String id) {
        return adminDao.deleteOrder(id);
    }


}
