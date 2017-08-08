package net.alily.utils;

import net.alily.conf.SelectType;
import net.alily.db.SqlHelper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 5/29/17.
 */
public class PageUtil {

    public static int getTotalCount(PageBean pageBean) {
        ResultSet resultSet;
        SqlHelper sqlHelper = null;

        int totalCount = 0;
        ArrayList<String> parasList = new ArrayList<String>();
        Condition condition = pageBean.getCondition();


        //用户
        //用户ID
        String userId = condition.getUserId();
        //姓名
        String name = condition.getName();


        //商品
        //id
        String commodityId = condition.getCommodityId();
        //标签
        String brand = condition.getBrand();
        //主题
        String store = condition.getStore();
        //类型
        String commodityType = condition.getCommodityType();
        //折扣
        BigDecimal discount= condition.getDiscount();

        //订单
        //订单编号
        String orderNumber = condition.getOrderId();
        //时间段 起始
        String orderStartTime = condition.getOrderStartTime();
        //时间段 结束
        String orderEndTime = condition.getOrderEndTime();
        //收件人电话
        String phone = condition.getPhone();

        try {
            //拼装SQL
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SELECT");
            stringBuffer.append("           COUNT(*) ");
            stringBuffer.append("FROM ");

            switch (condition.getSelectType()) {
                case SelectType.USER :
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

                    break;

                case SelectType.COMMODITY :
                    stringBuffer.append("            commodity ");
                    stringBuffer.append("WHERE");
                    stringBuffer.append("          1 = 1 ");

                    if(commodityId != null) {
                        stringBuffer.append(" AND commodityId = ? ");
                        parasList.add(commodityId);
                    }

                    if(brand != null && !brand.trim().equals("")) {
                        stringBuffer.append(" AND brand LIKE ? ");
                        parasList.add("%" + brand  + "%");
                    }

                    if(store != null && !store.trim().equals("")) {
                        stringBuffer.append(" AND store LIKE ? ");
                        parasList.add("%" + store  + "%");
                    }

                    if(commodityType != null && !commodityType.trim().equals("")) {
                        stringBuffer.append(" AND commodityType LIKE ? ");
                        parasList.add("%" + commodityType  + "%");
                    }

                    break;

                case SelectType.COMMODITY_PRECISE :
                    stringBuffer.append("            commodity ");
                    stringBuffer.append("WHERE");
                    stringBuffer.append("          1 = 1 ");

                    if(brand != null && !brand.trim().equals("")) {
                        stringBuffer.append(" AND brand = ? ");
                        parasList.add(brand);
                    }

                    if(store != null && !store.trim().equals("")) {
                        stringBuffer.append(" AND store = ? ");
                        parasList.add(store);
                    }

                    if(commodityType != null && !commodityType.trim().equals("")) {
                        stringBuffer.append(" AND commodityType = ? ");
                        parasList.add(commodityType);
                    }

                    if(name != null && !name.trim().equals("")) {
                        stringBuffer.append(" AND commodityName LIKE ? ");
                        parasList.add("%" + name + "%");
                    }

                    break;

                case SelectType.COMMODITY_DISCOUNT :
                    stringBuffer.append("            commodity ");
                    stringBuffer.append("WHERE");
                    stringBuffer.append("          1 = 1 ");

                    if(discount != null) {
                        stringBuffer.append(" AND thePurchasePrice <=  price * ? ");
                        parasList.add(discount+"");
                    }

                    break;

                case SelectType.ORDER :
                    stringBuffer.append("            orderFrom ");
                    stringBuffer.append("WHERE");
                    stringBuffer.append("          1 = 1 ");

                    if(commodityId != null) {
                        stringBuffer.append(" AND orderNumber = ? ");
                        parasList.add(orderNumber);
                    }

                    if(phone != null && !phone.trim().equals("")) {
                        stringBuffer.append(" AND phone LIKE ? ");
                        parasList.add("%" + phone  + "%");
                    }

                    if(orderStartTime != null && !orderStartTime.trim().equals("") && orderEndTime != null && !orderEndTime.trim().equals("") && Long.parseLong(orderStartTime) < Long.parseLong(orderEndTime)) {
                        stringBuffer.append(" AND orderTime > ? AND orderTime < ? ");
                        parasList.add(orderStartTime);
                        parasList.add(orderEndTime);
                    }


                    break;
            }


            String sql = stringBuffer.toString();
            sqlHelper = new SqlHelper();
            resultSet = executeSql(sql, parasList, sqlHelper);

            while (resultSet.next()) {
                totalCount = resultSet.getInt("COUNT(*)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.close();
        }

        return totalCount;
    }

    public static ResultSet executeSql(String sql, List<String> parasList, SqlHelper sqlHelper) throws Exception{
        String[] paras;
        ResultSet resultSet;

        if (!parasList.isEmpty()) {
            paras = new String[parasList.size()];
            for (int i = 0; i < parasList.size(); i++) {
                paras[i] = parasList.get(i);
            }
            resultSet = sqlHelper.query(sql, paras);
        } else {
            resultSet = sqlHelper.query(sql);
        }

        return resultSet;
    }
}
