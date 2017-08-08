package net.alily.dao.impl;

import net.alily.conf.SelectType;
import net.alily.dao.IAdminDao;
import net.alily.dao.IUserDao;
import net.alily.db.SqlHelper;
import net.alily.entity.*;
import net.alily.factory.BeanFactory;
import net.alily.utils.Condition;
import net.alily.utils.PageBean;
import net.alily.utils.PageUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员数据操作实现类
 * Created by Alan on 5/22/17.
 */
public class AdminDaoImpl implements IAdminDao{
    private Logger logger = Logger.getLogger(AdminDaoImpl.class);
    private SqlHelper sqlHelper;
    private ResultSet resultSet;

    //代理
    private IUserDao userDao = BeanFactory.getInstance("userDao", UserDaoImpl.class);

    public String checkLogin(User user) {
            String id = null;
            try {
                sqlHelper = new SqlHelper();
                String sql = "select id from admin where userName=? and password=? ";
                String[] parameter = new String[]{user.getUserName(), user.getPassword()};
                resultSet = sqlHelper.query(sql, parameter);

                resultSet.next();
                id = resultSet.getInt("id") + "";

            } catch (Exception e) {
                logger.error("CheckLogin Exception: " + e);
            } finally {
                sqlHelper.close();
            }

            return id;
    }

    public boolean save(User user) {
        return userDao.save(user);
    }

    public boolean update(User user, UserDetailedInfo userDetailedInfo) {
        return userDao.update(user, userDetailedInfo);
    }

    public boolean delete(int id) {
        return userDao.delete(id);
    }

    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public UserDetailedInfo findById(String id) {
        return userDao.findById(id);
    }

    public void queryUsers(PageBean<UserDetailedInfo> pageBean) {
        ArrayList<String> parasList = new ArrayList<String>();
        Condition condition = pageBean.getCondition();

        //用户
        //用户ID
        String userId = condition.getUserId();
        //姓名
        String name = condition.getName();

        try {
            //拼装SQL
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SELECT");
            stringBuffer.append("           id, name, age, sex, email, phone, address ");
            stringBuffer.append("FROM ");
            stringBuffer.append("            userDetailedInfo ");
            stringBuffer.append("WHERE");
            stringBuffer.append("          1 = 1 ");
            if(userId != null && !userId.trim().equals("")) {
                stringBuffer.append(" AND ID = ? ");
                parasList.add(userId);
            }

            if(name != null && !name.trim().equals("")) {
                stringBuffer.append(" AND NAME LIKE ? ");
                parasList.add("%" + name  + "%");
            }

            stringBuffer.append("ORDER BY ID ");
            stringBuffer.append("LIMIT     ");
            stringBuffer.append("?, ?");


            // 先获取总记录数
            int totalCount = PageUtil.getTotalCount(pageBean);
            // 判断是否执行以下语句
            if (totalCount != 0) {
                // 设置分页参数 总记录数
                pageBean.setTotalCount(totalCount);

                if (pageBean.getCurrentPage() < 1) {
                    pageBean.setCurrentPage(1);
                } else if (pageBean.getCurrentPage() > pageBean.getTotalPage()) {
                    pageBean.setCurrentPage(pageBean.getTotalPage());
                }

                // 起始行
                int index = (pageBean.getCurrentPage() - 1) * pageBean.getPageCount();
                //每页的记录数
                int count = pageBean.getPageCount();

                parasList.add(String.valueOf(index));
                parasList.add(String.valueOf(count));

                String sql = stringBuffer.toString();
                sqlHelper = new SqlHelper();

                resultSet = PageUtil.executeSql(sql, parasList, sqlHelper);

                ArrayList<UserDetailedInfo> list = new ArrayList<UserDetailedInfo>();
                while (resultSet.next()) {
                    UserDetailedInfo user = new UserDetailedInfo();
                    user.setId(Integer.parseInt(resultSet.getString("id")));
                    user.setName(resultSet.getString("name"));
                    user.setSex(Integer.parseInt(resultSet.getString("sex")));
                    user.setAge(resultSet.getInt("age"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAddress(resultSet.getString("address"));
                    list.add(user);
                }
                pageBean.setPageData(list);
            }

        } catch (Exception e) {
            logger.error("Query users error: " + e);
        } finally {
            sqlHelper.close();
        }

    }

    public List<OrderFrom> findOrderById(String id) {
        return userDao.findOrderById(id);
    }

    @Override
    public UserInfo findUserInfoById(String id) {
        return userDao.findUserInfoById(id);
    }

    public boolean insertCommodity(Commodity commodity) {
        boolean isSuccess = false;
        sqlHelper = new SqlHelper();
        try {
            String insertSql = "insert into commodity(commodityId, commodityName, price, thePurchasePrice, commodityType, brand, picture, store, number, illustrate, costPrice, source ) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String[] insertParas = new String[]{commodity.getCommodityId(), commodity.getCommodityName(), commodity.getPrice() + "", commodity.getThePurchasePrice() + "", commodity.getCommodityType(), commodity.getBrand(), commodity.getPicture(), commodity.getStore(), commodity.getNumber(), commodity.getIllustrate(), commodity.getCostPrice() + "", commodity.getSource()};
            String updateSql = "update commodity set commodityName = ?, price = ?, thePurchasePrice = ?, commodityType = ?, brand = ?, picture = ?, store = ?, number = ?, illustrate = ?, costPrice = ?, source = ?  where commodityId = ?";
            String[] updateParas = new String[]{commodity.getCommodityName(), commodity.getPrice() + "", commodity.getThePurchasePrice() + "", commodity.getCommodityType(), commodity.getBrand(), commodity.getPicture(), commodity.getStore(), commodity.getNumber(), commodity.getIllustrate(), commodity.getCostPrice() + "", commodity.getSource(), commodity.getCommodityId() };

            if(commodity.isInsert() == 1) {
                isSuccess = sqlHelper.alteration(insertSql, insertParas);
                logger.info("Set commodity: " + isSuccess);
            }

            if (commodity.isInsert() == 0) {
                isSuccess = sqlHelper.alteration(updateSql, updateParas);
                logger.info("Update commodity: " + isSuccess);
            }
        } catch (Exception e) {
            logger.error("Insert commodity exception: " + e);
        } finally {
            sqlHelper.close();
        }

        return isSuccess;
    }

    @Override
    public List<String> queryCondition(String conditionName) {
        sqlHelper = new SqlHelper();
        List<String> conditionList = null;
        try {
            String sql = "select distinct "+ conditionName + " from commodity";
            resultSet = sqlHelper.query(sql);
            conditionList = new ArrayList<>();
            while (resultSet.next()) {
                conditionList.add(resultSet.getString(conditionName));
            }

        }catch (Exception e) {
            logger.error("Query condition Exception: " + e);
        }finally {
            sqlHelper.close();
        }

        return conditionList;
    }

    public void queryCommodities(PageBean<Commodity> pageBean) {
        userDao.queryCommodities(pageBean);
    }

    @Override
    public Commodity findCommodityById(String id) {
        sqlHelper = new SqlHelper();
        Commodity commodity = null;
        try {
            String sql = "select commodityId, commodityName, thePurchasePrice, price, commodityType, brand, picture, store, number, illustrate, costPrice, source from commodity where commodityId = ?";
            String[] paras = new String[]{id};
            resultSet = sqlHelper.query(sql, paras);
            commodity = new Commodity();

            while (resultSet.next()) {
                commodity.setCommodityId(resultSet.getString("commodityId"));
                commodity.setCommodityName(resultSet.getString("commodityName"));
                commodity.setPrice(resultSet.getBigDecimal("price"));
                BigDecimal thePurchasePrice = resultSet.getBigDecimal("thePurchasePrice");
                if(thePurchasePrice == null) {
                    thePurchasePrice = new BigDecimal(0);
                }
                commodity.setThePurchasePrice(thePurchasePrice);
                commodity.setCommodityType(resultSet.getString("commodityType"));
                commodity.setBrand(resultSet.getString("brand"));
                commodity.setPicture(resultSet.getString("picture"));
                commodity.setStore(resultSet.getString("store"));
                commodity.setNumber(resultSet.getBigDecimal("number") + "");
                commodity.setIllustrate(resultSet.getString("illustrate"));
                commodity.setCostPrice(resultSet.getBigDecimal("costPrice"));
                commodity.setSource(resultSet.getString("source"));
            }
        } catch (Exception e) {
          logger.error("Find commodity by id Exception: " + e);
        }finally {
            sqlHelper.close();
        }

        return commodity;
    }

    @Override
    public boolean commodityDelete(String id) {
        sqlHelper = new SqlHelper();
        try {
            String sql = "delete from commodity where commodityId = ?";
            String[] parameter = new String[]{id + ""};
            return sqlHelper.alteration(sql, parameter);
        } catch (Exception e) {
            logger.error("Delete commodity Exception: " + e);
        }finally {
            sqlHelper.close();
        }

        return false;
    }

    @Override
    public void queryOrders(PageBean<OrderFrom> pageBean) {
        ArrayList<String> parasList = new ArrayList<String>();
        Condition condition = pageBean.getCondition();

        String orderId = condition.getOrderId();
        String phone = condition.getPhone();

        try {
            //拼装SQL
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SELECT");
            stringBuffer.append("           orderNumber, presetId, orderTime, orderTotal, phone, orderStat ");
            stringBuffer.append("FROM ");
            stringBuffer.append("            orderFrom ");
            stringBuffer.append("WHERE");
            stringBuffer.append("          1 = 1 ");
            if(orderId != null && !orderId.trim().equals("")) {
                stringBuffer.append(" AND orderNumber = ? ");
                parasList.add(orderId);
            }

            if(phone != null && !phone.trim().equals("")) {
                stringBuffer.append(" AND phone = ? ");
                parasList.add(phone);
            }

            stringBuffer.append("ORDER BY orderNumber ");
            stringBuffer.append("LIMIT     ");
            stringBuffer.append("?, ?");


            // 先获取总记录数
            int totalCount = PageUtil.getTotalCount(pageBean);
            // 判断是否执行以下语句
            if (totalCount != 0) {
                // 设置分页参数 总记录数
                pageBean.setTotalCount(totalCount);

                if (pageBean.getCurrentPage() < 1) {
                    pageBean.setCurrentPage(1);
                } else if (pageBean.getCurrentPage() > pageBean.getTotalPage()) {
                    pageBean.setCurrentPage(pageBean.getTotalPage());
                }

                // 起始行
                int index = (pageBean.getCurrentPage() - 1) * pageBean.getPageCount();
                //每页的记录数
                int count = pageBean.getPageCount();

                parasList.add(String.valueOf(index));
                parasList.add(String.valueOf(count));

                String sql = stringBuffer.toString();
                sqlHelper = new SqlHelper();

                resultSet = PageUtil.executeSql(sql, parasList, sqlHelper);

                ArrayList<OrderFrom> list = new ArrayList<>();
                while (resultSet.next()) {
                    OrderFrom order = new OrderFrom();
                    order.setOrderNumber(resultSet.getString("orderNumber"));
                    order.setPresetId(resultSet.getString("presetId"));
                    order.setOrderTime(Long.parseLong(resultSet.getString("orderTime")));
                    order.setOrderTotal(resultSet.getBigDecimal("orderTotal"));
                    order.setPhone(resultSet.getString("phone"));
                    order.setOrderStat(resultSet.getString("orderStat"));

                    list.add(order);
                }
                pageBean.setPageData(list);
            }

        } catch (Exception e) {
            logger.error("Query users error: " + e);
        } finally {
            sqlHelper.close();
        }

    }

    public OrderFrom queryOrderById(String id) {
        List<OrderFrom> result = null;
        OrderFrom orderFrom = null;
        try{
            sqlHelper = new SqlHelper();
            result = new ArrayList<OrderFrom>();
            String sql = "select orderNumber, presetId, orderDetails, orderTime, orderTotal, deliveryAddress, phone, orderStat from orderFrom where orderNumber = ?";
            String[] parameter = new String[]{id};

            ResultSet resultSet = sqlHelper.query(sql, parameter);
            orderFrom = new OrderFrom();
            while(resultSet.next()) {
                orderFrom.setOrderNumber(resultSet.getString("orderNumber"));
                orderFrom.setPresetId(resultSet.getString("presetId"));
                orderFrom.setOrderDetails(resultSet.getString("orderDetails"));
                orderFrom.setOrderTime(Long.parseLong(resultSet.getString("orderTime")));
                orderFrom.setOrderTotal(resultSet.getBigDecimal("orderTotal"));
                orderFrom.setDeliveryAddress(resultSet.getString("deliveryAddress"));
                orderFrom.setPhone(resultSet.getString("phone"));
                orderFrom.setOrderStat(resultSet.getString("orderStat"));
            }

        } catch (Exception e) {
            logger.error("Find order by id Exception: " + e);
        } finally {
            sqlHelper.close();
        }

        return orderFrom;
    }

    public boolean updateOrder (OrderFrom order) {
        try{
            sqlHelper = new SqlHelper();
            String sql = "update OrderFrom set presetId=?, orderDetails=?, orderTime=?, orderTotal=?, deliveryAddress=?, phone=?, orderStat=? where orderNumber = ?";
            String[] parameter = new String[]{order.getPresetId(), order.getOrderDetails(), order.getTimeStamp()+"", order.getOrderTotal()+"", order.getDeliveryAddress(), order.getPhone(), order.getOrderStat(), order.getOrderNumber()};

            return sqlHelper.alteration(sql, parameter);
        } catch (Exception e) {
            logger.error("Update order Exception: " + e);
        } finally {
            sqlHelper.close();
        }

        return false;
    }

    public boolean deleteOrder(String id) {
        sqlHelper = new SqlHelper();
        try {
            String deleteSql = "delete from orderFrom where orderNumber = ?";
            String[] parameter = new String[]{id};
            return sqlHelper.alteration(deleteSql, parameter);
        } catch (Exception e) {
            logger.error("Delete order Exception: " + e);
        } finally {
            sqlHelper.close();
        }

        return false;
    }



}
