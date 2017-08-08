package net.alily.test;

import net.alily.conf.OrderStat;
import net.alily.dao.impl.AdminDaoImpl;
import net.alily.dao.impl.UserDaoImpl;
import net.alily.db.SqlHelper;
import net.alily.entity.OrderFrom;
import net.alily.service.IUserService;
import net.alily.service.impl.UserServiceImpl;
import net.alily.utils.IDate;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Test
 * Created by Alan on 5/13/17.
 */
public class Test {
    public static void main(String... args) {
//        SqlHelper sqlHelper = new SqlHelper();
//        String sql = "select * from user";
//        ResultSet set;
//        try {
//            set = sqlHelper.query(sql);
//            while (set.next()) {
//                System.out.println(set.getString("username"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.out.print("dasd");

//        OrderFrom orderFrom = new OrderFrom("1");
//        orderFrom.setOrderStat(OrderStat.SUCCESS);
//        orderFrom.setPhone("13993140157");
//        orderFrom.setDeliveryAddress("甘肃省兰州市兰州理工大学");
//        orderFrom.setOrderDetails("K120:Logitech-Key:1:200.0:180.0;B035:B-1:2:5:4.5");
//        orderFrom.setOrderTotal(new BigDecimal(180+4.5*2));
//
//        IUserService userService = new UserServiceImpl();
//        userService.placeAnOrder(orderFrom);

//        System.out.println(IDate.nowTimeStamp());

//        UserDaoImpl userDao = new UserDaoImpl();
//        for(Map.Entry<String, List<Map<String, List<String>>>> l: userDao.queryStockingShelves().entrySet()) {
//            System.out.println(l.getKey() + ": ");
//            for(Map<String, List<String>> str: l.getValue()) {
//                for(Map.Entry<String, List<String>> entry: str.entrySet()) {
//                    System.out.print(entry.getKey() + "> ");
//                    for(String s: entry.getValue()) {
//                        System.out.print(s + " ");
//                    }
//                    System.out.println();
//                }
//            }

        String orderTime = "2017.05.17 13:36:53";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(orderTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        System.out.println(ts);



    }

}
