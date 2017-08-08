package net.alily.dao.impl;

import net.alily.dao.IAdminDao;
import net.alily.dao.IUserDao;
import net.alily.db.SqlHelper;
import net.alily.entity.*;
import net.alily.factory.BeanFactory;
import net.alily.utils.Condition;
import net.alily.utils.PageBean;
import net.alily.utils.PageUtil;
import org.apache.log4j.Logger;
import org.w3c.dom.ls.LSException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户业务逻辑实现类 UserDaoImpl
 * Created by Alan on 5/15/17.
 */
public class UserDaoImpl implements IUserDao {
    private SqlHelper sqlHelper;
    private ResultSet resultSet;
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);


    public UserDaoImpl() {

    }

    public boolean save(User user) {
        try {
            sqlHelper = new SqlHelper();
            String sql = "insert into user(username, password, level) values(?, ?, ?)";
            String[] parameter = new String[]{user.getUserName(), user.getPassword(), user.getLevel()+""};
            if(sqlHelper.alteration(sql, parameter)) {
                logger.info("User save success.");
                return true;
            } else {
                logger.error("User save defeated, username: " + user.getUserName());
                return false;
            }

        } catch (Exception e) {
            logger.error("Save Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }

        return false;
    }

    public boolean update(User user, UserDetailedInfo userDetailedInfo) {
        boolean a = false;
        boolean b = false;
        try {
            sqlHelper = new SqlHelper();
            String updateUser = "update user set userName = ?, password = ?, level = ? where id = ?";
            String[] updateUserParameter = new String[]{user.getUserName(), user.getPassword(), user.getLevel()+"", user.getId()+""};

            String updateUserInfo = "update userDetailedInfo set name = ?, age = ?, sex = ?, phone = ?, email = ?, address = ? where id = ?";
            String[] updateUserInfoParameter = new String[]{userDetailedInfo.getName(), userDetailedInfo.getAge()+"", userDetailedInfo.getSex()+"", userDetailedInfo.getPhone(), userDetailedInfo.getEmail(), userDetailedInfo.getAddress(), userDetailedInfo.getId()+""};

            String insertUserInfo = "insert into userDetailedInfo values(?, ?, ?, ?, ?, ?, ?)";
            String[] insertUserInfoParameter = new String[]{userDetailedInfo.getId()+"", userDetailedInfo.getName(), userDetailedInfo.getAge()+"", userDetailedInfo.getSex()+"", userDetailedInfo.getEmail(), userDetailedInfo.getPhone(), userDetailedInfo.getAddress()};

            String queryUserInfoId = "select id from userDetailedInfo where id = ?";
            String[] queryUserInfoIdParameter = new String[]{userDetailedInfo.getId()+""};

            a = sqlHelper.alteration(updateUser, updateUserParameter);
            if(sqlHelper.query(queryUserInfoId, queryUserInfoIdParameter).next()) {
                logger.info("update: " + userDetailedInfo.toString());
                b = sqlHelper.alteration(updateUserInfo, updateUserInfoParameter);
            } else {
                logger.info("insert: " + userDetailedInfo.toString());
                b = sqlHelper.alteration(insertUserInfo, insertUserInfoParameter);
            }

        }catch (Exception e) {
            logger.error("Update Exception: " + e);
        }finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }
        return a&&b;
    }

    public boolean delete(int id) {
        sqlHelper = new SqlHelper();
        try {
            String deleteUserSql = "delete from user where id = ?";
            String deleteUserDetailedInfoSql = "delete from userDetailedInfo where id = ?";
            String[] parameter = new String[]{id + ""};
            return sqlHelper.alteration(deleteUserSql, parameter) && sqlHelper.alteration(deleteUserDetailedInfoSql, parameter);
        } catch (Exception e) {
            logger.error("Delete user Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }

        return false;
    }

    private void buildUser(ResultSet resultSet, User user) throws Exception{
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id") + "");
            user.setUserName(resultSet.getString("userName"));
            user.setPassword(resultSet.getString("password"));
            user.setLevel(resultSet.getString("level"));
        }
    }

    public User findByUserName(String userName) {
        User user = null;
        try{
          sqlHelper = new SqlHelper();
          String sql = "select id,userName,password,level from user where userName = ? ";
          String[] parameter = new String[]{userName};
          resultSet = sqlHelper.query(sql, parameter);
          user = new User();
          buildUser(resultSet, user);
        }catch (Exception e) {
            logger.error("Find User Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }

        return user;
    }

    public UserInfo findUserInfoById(String id) {
        UserInfo userInfo = null;
        try{
            sqlHelper = new SqlHelper();
            String sql = "select id,userName,password,level from user where id = ?";
            String[] parameter = new String[]{id};
            resultSet = sqlHelper.query(sql, parameter);
            User user = new User();

            buildUser(resultSet, user);

            UserDetailedInfo userDetailedInfo = this.findById(id);
            userInfo = new UserInfo();
            userInfo.setUser(user);
            userInfo.setUserDetailedInfo(userDetailedInfo);
        }catch (Exception e) {
            logger.error("Find UserInfo Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }

        return userInfo;
    }


    public UserDetailedInfo findById(String id) {
        UserDetailedInfo user = null;
        try{
            sqlHelper = new SqlHelper();
            String sql = "select id, name, age, sex, phone, email, address from userDetailedInfo where id = ? ";
            String[] parameter = new String[]{id};
            resultSet = sqlHelper.query(sql, parameter);
            user = new UserDetailedInfo();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setSex(resultSet.getInt("sex"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
            }

        }catch (Exception e) {
            logger.error("Find UserDetailedInfo Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }

        return user;
    }

    public String checkLogin(User user) {
        String id = null;
        try {
            sqlHelper = new SqlHelper();
            String sql = "select id from user where userName=? and password=? ";
            String[] parameter = new String[]{user.getUserName(), user.getPassword()};
            resultSet = sqlHelper.query(sql, parameter);

            resultSet.next();
            id = resultSet.getInt("id") + "";

        } catch (Exception e) {
            logger.error("CheckLogin Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }
        return id;
    }

    public List<OrderFrom> findOrderById(String id) {
        List<OrderFrom> result = null;
        try{
            sqlHelper = new SqlHelper();
            result = new ArrayList<OrderFrom>();
            String sql = "select orderNumber, presetId, orderDetails, orderTime, orderTotal, deliveryAddress, phone, orderStat, name from orderFrom where presetId = ?";
            String[] parameter = new String[]{id};

            ResultSet resultSet = sqlHelper.query(sql, parameter);
            while(resultSet.next()) {
                OrderFrom orderFrom = new OrderFrom();
                orderFrom.setOrderNumber(resultSet.getString("orderNumber"));
                orderFrom.setPresetId(resultSet.getString("presetId"));
                orderFrom.setOrderDetails(resultSet.getString("orderDetails"));
                orderFrom.setOrderTime(Long.parseLong(resultSet.getString("orderTime")));
                orderFrom.setOrderTotal(resultSet.getBigDecimal("orderTotal"));
                orderFrom.setDeliveryAddress(resultSet.getString("deliveryAddress"));
                orderFrom.setPhone(resultSet.getString("phone"));
                orderFrom.setOrderStat(resultSet.getString("orderStat"));
                orderFrom.setName(resultSet.getString("name"));

                result.add(orderFrom);

            }

        } catch (Exception e) {
            logger.error("Find order by id Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }



        return result;
    }

    public boolean placeAnOrder(OrderFrom orderFrom) {
        try {
            sqlHelper = new SqlHelper();
            String sql = "insert into orderFrom(orderNumber, presetId, orderDetails, orderTime, orderTotal, deliveryAddress, phone, orderStat, name) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String[] parameter = new String[]{orderFrom.getOrderNumber(), orderFrom.getPresetId(), orderFrom.getOrderDetails(), orderFrom.getTimeStamp()+"", orderFrom.getOrderTotal() + "", orderFrom.getDeliveryAddress(), orderFrom.getPhone(), orderFrom.getOrderStat(), orderFrom.getName()};
            if(sqlHelper.alteration(sql, parameter)) {
                logger.info("Place an order success.");
                return true;
            } else {
                logger.error("Place an order be defeated, orderNumber: " + orderFrom.getOrderNumber());
                return false;
            }

        } catch (Exception e) {
            logger.error("Save Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }

        return false;
    }

    public Map<String, List<Map<String, List<String>>>> queryStockingShelves() {
        Map<String, List<Map<String, List<String>>>> stockingShelves = null;

        try {
            sqlHelper = new SqlHelper();
            stockingShelves = new HashMap<>();
            String storeCountSql = "select distinct store from commodity";
            resultSet = sqlHelper.query(storeCountSql);
            while (resultSet.next()) {
                String store = resultSet.getString("store");
                String typeSql = "select distinct commodityType from commodity where store = ?";
                String[] paras = new String[]{store};
                ResultSet types = sqlHelper.query(typeSql, paras);
                ArrayList<Map<String, List<String>>> typeAndBrandsList = new ArrayList<>();
                while (types.next()) {
                    String type = types.getString("commodityType");
                    String brandSql = "select distinct brand from commodity where commodityType = ?";
                    String[] typeParas = new String[]{type};
                    ResultSet brands = sqlHelper.query(brandSql, typeParas);
                    Map<String,List<String>> typeMapBrands = new HashMap<>();
                    ArrayList<String> brandList = new ArrayList<>();
                    while (brands.next()) {
                        brandList.add(brands.getString("brand"));
                    }
                    typeMapBrands.put(type, brandList);
                    typeAndBrandsList.add(typeMapBrands);
                }
                stockingShelves.put(store, typeAndBrandsList);

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Query stocking shelves Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }

        return stockingShelves;
    }

    @Override
    public void queryCommodities(PageBean<Commodity> pageBean) {
        ArrayList<String> parasList = new ArrayList<>();
        Condition condition = pageBean.getCondition();

        //商品
        String store = condition.getStore();
        String type = condition.getCommodityType();
        String brand = condition.getBrand();
        String name = condition.getName();
        BigDecimal discount = condition.getDiscount();

        try {
            //拼装SQL
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SELECT");
            stringBuffer.append("           commodityId, commodityName, thePurchasePrice, price, commodityType, brand, picture, store, number, illustrate, costPrice, source ");
            stringBuffer.append("FROM ");
            stringBuffer.append("            commodity ");
            stringBuffer.append("WHERE");
            stringBuffer.append("          1 = 1 ");
            if(store != null && !store.trim().equals("")) {
                stringBuffer.append(" AND store = ? ");
                parasList.add(store);
            }

            if(type != null && !type.trim().equals("")) {
                stringBuffer.append(" AND commodityType = ? ");
                parasList.add(type);
            }

            if(brand != null && !brand.trim().equals("")) {
                stringBuffer.append(" AND brand = ? ");
                parasList.add(brand);
            }

            if(name != null && !name.trim().equals("")) {
                stringBuffer.append(" AND commodityName LIKE ? ");
                parasList.add("%" + name + "%");
            }

            if(discount != null) {
                stringBuffer.append(" AND thePurchasePrice <=  price * ? ");
                parasList.add(discount+"");
            }

            stringBuffer.append("ORDER BY commodityId ");
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

                ArrayList<Commodity> list = new ArrayList<>();
                while (resultSet.next()) {
                    Commodity commodity = new Commodity();
                    commodity.setCommodityId(resultSet.getString("commodityId"));
                    commodity.setCommodityName(resultSet.getString("commodityName"));
                    commodity.setPrice(resultSet.getBigDecimal("price"));
                    BigDecimal thePurchasePrice = resultSet.getBigDecimal("thePurchasePrice");
                    if(thePurchasePrice == null) {
                        thePurchasePrice = new BigDecimal(0);
                    }
                    commodity.setThePurchasePrice(thePurchasePrice);
                    commodity.setCommodityType(resultSet.getString("commodityType")); //price, commodityType, brand, picture, store, number
                    commodity.setBrand(resultSet.getString("brand"));
                    commodity.setPicture(resultSet.getString("picture"));
                    commodity.setStore(resultSet.getString("store"));
                    commodity.setNumber(resultSet.getBigDecimal("number") + "");
                    commodity.setIllustrate(resultSet.getString("illustrate"));
                    commodity.setCostPrice(resultSet.getBigDecimal("costPrice"));
                    commodity.setSource(resultSet.getString("source"));

                    list.add(commodity);
                }
                pageBean.setPageData(list);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Query commodities error: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }
    }

    public List<Cart> queryCarts(String userId) {
        List<Cart> list = null;
        try {
            sqlHelper = new SqlHelper();
            String sql = "select id, commodityId, commodityName, number, userId, price from shoppingCart where userId = ?";
            String[] parameter = new String[]{userId};
            resultSet = sqlHelper.query(sql, parameter);
            list = new ArrayList<>();
            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getString("id"));
                cart.setCommodityId(resultSet.getString("commodityId"));
                cart.setCommodityName(resultSet.getString("commodityName"));
                cart.setNumber(resultSet.getBigDecimal("number") + "");
                cart.setUserId(resultSet.getString("userId"));
                cart.setPrice(resultSet.getBigDecimal("price"));
                cart.setTotal(resultSet.getBigDecimal("number").multiply(resultSet.getBigDecimal("price")));
                list.add(cart);
            }

        } catch (Exception e) {
            logger.error("Query carts Exception: " + e);
        } finally {
            sqlHelper.close();
        }

        return list;
    }

    public BigDecimal queryCartNumber(String cartId) {
        BigDecimal number = null;
        try {
            sqlHelper = new SqlHelper();
            String sql = "select number from shoppingCart where id = ?";
            String[] parameter = new String[]{cartId};
            resultSet = sqlHelper.query(sql, parameter);

            while (resultSet.next()) {
                number = resultSet.getBigDecimal("number");
            }

        } catch (Exception e) {
            logger.error("Query cart number Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }

        return number;
    }

    public boolean alterCart(Cart cart, int al) {
        boolean isSuccess = false;
        SqlHelper sqlHelper1 = null;
        try {
            sqlHelper1 = new SqlHelper();
            String insertSql = "insert into shoppingCart(commodityId, commodityName, number, userId, price) values(?, ?, ?, ?, ?)";
            String[] insertParameter = new String[]{cart.getCommodityId(), cart.getCommodityName(), cart.getNumber(), cart.getUserId(), cart.getPrice()+""};

            String updateSql = "update shoppingCart set number = ? where id = ?";
            String[] updateParameter;
            BigDecimal number;
            switch (al){
                case -1:
                    number = queryCartNumber(cart.getId());

                    if(number.compareTo(BigDecimal.ONE) > 0) {
                        updateParameter = new String[]{number.subtract(BigDecimal.ONE) + "", cart.getId()};
                        isSuccess = sqlHelper1.alteration(updateSql, updateParameter);
                    } else {
                        isSuccess = sqlHelper1.alteration("delete from shoppingCart where id = ?", new String[]{cart.getId()});
                    }
                    break;
                case 0:
                    resultSet = sqlHelper1.query("select number, id from shoppingCart where commodityId = ? and userId = ?", new String[]{cart.getCommodityId(), cart.getUserId()});

                    if(resultSet == null) {
                        isSuccess = sqlHelper1.alteration(insertSql, insertParameter);
                    } else if (resultSet.next()){
                        number = resultSet.getBigDecimal("number");
                        String id = resultSet.getString("id");
                        if(number != null) {
                            updateParameter = new String[]{number.add(BigDecimal.ONE) + "", id};
                            isSuccess = sqlHelper1.alteration(updateSql, updateParameter);
                        } else {
                            isSuccess = sqlHelper1.alteration(insertSql, insertParameter);
                        }
                    } else {
                        isSuccess = sqlHelper1.alteration(insertSql, insertParameter);
                    }
                    break;
                case 1:
                    number = queryCartNumber(cart.getId());
                    updateParameter = new String[]{number.add(BigDecimal.ONE) + "", cart.getId()};
                    isSuccess = sqlHelper1.alteration(updateSql, updateParameter);
                    break;

                case 2:
                    isSuccess = sqlHelper1.alteration("delete from shoppingCart where id = ?", new String[]{cart.getId()});
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Alter cart Exception: " + e);
        } finally {
            if(sqlHelper1 != null){
                sqlHelper1.close();
            }
        }

        return isSuccess;
    }

    public boolean updateCommodityNumber(Commodity commodity) {
        try {
            sqlHelper = new SqlHelper();
            String sql = "update commodity set number = ? where commodityId = ?";
            String[] parameter = new String[]{commodity.getNumber(), commodity.getCommodityId()};
            return sqlHelper.alteration(sql, parameter);
        } catch (Exception e) {
            logger.error("Update commodity number Exception: " + e);
        } finally {
            if(sqlHelper != null){
                sqlHelper.close();
            }
        }
        return false;
    }



}
