package net.alily.servlet;

import net.alily.conf.OrderStat;
import net.alily.conf.SelectType;
import net.alily.entity.*;
import net.alily.utils.Condition;
import net.alily.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * UserServlet
 * Created by Alan on 5/16/17.
 */
public class UserServlet extends BaseServlet {

    public UserServlet() {
        super();
    }


    /**
     * 登陆检查
     * @param request
     * @param response
     * @return Object
     * @throws ServletException servlet
     * @throws IOException IO
     */
    public Object checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        String userId =  userService.checkLogin(user);
        if(userId != null) {
            session.setAttribute("userName", userName);
            session.setAttribute("userId", userId);
            Map<String, List<Map<String, List<String>>>> stockingShelves = userService.queryStockingShelves();
            session.setAttribute("stockingShelves", stockingShelves);
            uri = request.getRequestDispatcher("index.jsp");
        } else {
            uri = request.getRequestDispatcher("login.jsp");
        }

        return uri;
    }

    /**
     * 注册
     * @param request
     * @param response
     * @return Object
     * @throws ServletException servlet
     * @throws IOException IO
     */
    public Object signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setLevel("1");

        if(userService.save(user)) {
            session.setAttribute("userName", userName);
            uri = request.getRequestDispatcher("index.jsp");
        } else {
            uri = request.getRequestDispatcher("signUp.jsp");
        }

        return uri;
    }

    /**
     * 用户信息展示
     * @param request
     * @param response
     * @return Object
     * @throws ServletException servlet
     * @throws IOException IO
     */
    public Object showUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            uri =  request.getRequestDispatcher("login.jsp");
            return uri;
        }

        try{
            String userName = session.getAttribute("userName").toString();

            User user = userService.findByUserName(userName);
            UserDetailedInfo userDetailedInfo = userService.findByid(user.getId()+"");
            request.setAttribute("user", user);
            if(user.getId() != 0 ) {
                if(userDetailedInfo != null && userDetailedInfo.getId() != 0) {
                    request.setAttribute("userInfo", userDetailedInfo);
                    uri = request.getRequestDispatcher("userInfo.jsp");
                } else {
                    uri = request.getRequestDispatcher("userInfo.jsp");
                }
            } else {
                uri = request.getRequestDispatcher("login.jsp");
            }
        } catch (Exception e) {
            uri = request.getRequestDispatcher("login.jsp");
        }

        return uri;
    }

    /**
     * @param request
     * @param response
     * @return Object
     * @throws ServletException servlet
     * @throws IOException IO
     */
    public Object updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null) {
            return request.getRequestDispatcher("login.jsp");
        }

        String id = request.getParameter("id").split(" ")[1];
        String level = request.getParameter("level").split(" ")[1];
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        User user = new User();
        user.setId(id);
        user.setLevel(level);
        user.setUserName(userName);
        user.setPassword(password);

        UserDetailedInfo userDetailedInfo = new UserDetailedInfo();
        userDetailedInfo.setId(Integer.parseInt(id));
        userDetailedInfo.setName(name);
        userDetailedInfo.setAge(Integer.parseInt(age));
        if(sex.equals("?")) {
            sex = "0";
        } else {
            sex = "1";
        }
        userDetailedInfo.setSex(Integer.parseInt(sex));
        userDetailedInfo.setPhone(phone);
        userDetailedInfo.setEmail(email);
        userDetailedInfo.setAddress(address);

        if(userService.update(user, userDetailedInfo)) {
            request.setAttribute("user", user);
            request.setAttribute("userInfo", userDetailedInfo);
            uri = request.getRequestDispatcher("userInfo.jsp");
        } else {
            uri = request.getRequestDispatcher("userInfo.jsp");
        }


        return uri;
    }

    /**
     * @param request
     * @param response
     * @return Object
     * @throws ServletException servlet
     * @throws IOException IO
     */
    public Object showOrderFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null) {
            return request.getRequestDispatcher("login.jsp");
        }

        try{
            String userName = request.getSession().getAttribute("userName").toString();

            User user = userService.findByUserName(userName);
            List<OrderFrom> orderFroms = userService.findOrderById(user.getId()+"");

            if(orderFroms != null) {
                request.setAttribute("orderFroms", orderFroms);
                uri = request.getRequestDispatcher("orderFrom.jsp");
            } else {
                uri = request.getRequestDispatcher("orderFrom.jsp");
            }
        } catch (Exception e) {
            uri = request.getRequestDispatcher("login.jsp");
        }

        return uri;
    }

    public Object showCommodities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null) {
            return request.getRequestDispatcher("login.jsp");
        }

        String[] conditions = null;
        String isQuery = request.getParameter("isQuery");
        String name = request.getParameter("name");
        String discount = request.getParameter("discount");
        // 获取 pageBean
        PageBean<Commodity> pageBean = new PageBean<>();
        pageBean.setPageCount(8);
        // 第一次访问 设置当前页为 1
        String currentPage = request.getParameter("currentPage");
        // 判断
        if (currentPage == null || "".equals(currentPage.trim())) {
            // 第一次访问 当前页设置为 1
            pageBean.setCurrentPage(1);
            if(isQuery == null ) {
                conditions = request.getParameter("condition").split(":");
                session.setAttribute("conditions", conditions);
            } else if (isQuery.trim().equals("query")){
                conditions = new String[]{name, name, name};
                session.setAttribute("conditions", conditions);
            } else if (isQuery.equals("dis")) {
                conditions = new String[]{"今日优惠", "高于5折", "优惠商品"};
                session.setAttribute("conditions", conditions);
                if(Double.parseDouble(discount) <= 0.5) {
                    conditions = new String[]{"今日特价", "低于5折", "特价商品"};
                    session.setAttribute("conditions", conditions);
                }
            }
        } else {
            // 设置当前页参数
            pageBean.setCurrentPage(Integer.parseInt(currentPage));
            conditions = (String[]) session.getAttribute("conditions");
        }


        Condition condition = new Condition();
        condition.setSelectType(SelectType.COMMODITY_PRECISE);

        if(isQuery != null) {
            if(isQuery.trim().equals("query")) {
                condition.setName(name);
            } else if(isQuery.trim().equals("dis")){
                condition.setDiscount(new BigDecimal(discount));
                condition.setSelectType(SelectType.COMMODITY_DISCOUNT);
            }
        } else {
            condition.setStore(conditions[0]);
            condition.setCommodityType(conditions[1]);
            condition.setBrand(conditions[2]);
        }



        // 设置条件对象到 pageBean中
        pageBean.setCondition(condition);
        // 分页查询
        userService.queryCommodities(pageBean);
        // 保存 pageBean 到域对象中
        request.setAttribute("commoditiesBean", pageBean);

        // 转发
        uri = request.getRequestDispatcher("products.jsp");


        return uri;
    }

    public Object singleCommodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null) {
            return request.getRequestDispatcher("login.jsp");
        }

        String commodityId = request.getParameter("commodityId");
        Commodity commodity = adminService.findCommodityById(commodityId);
        request.setAttribute("commodity", commodity);

        uri = request.getRequestDispatcher("single.jsp");

        return uri;
    }


    public Object cancellationOfOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null) {
            return request.getRequestDispatcher("login.jsp");
        }

        String orderNumber = request.getParameter("orderNumber");
        adminService.deleteOrder(orderNumber);

        uri = showOrderFrom(request, response);

        return uri;
    }

    public Object addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null || session.getAttribute("userId") == null ) {
            return request.getRequestDispatcher("login.jsp");
        }

        String userId = session.getAttribute("userId").toString();
        String commodityId = request.getParameter("commodityId");
        String commodityName = request.getParameter("commodityName");
        String thePurchasePrice = request.getParameter("thePurchasePrice");

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setCommodityName(commodityName);
        cart.setPrice(new BigDecimal(thePurchasePrice));
        cart.setCommodityId(commodityId);
        cart.setNumber("1");

        userService.alterCart(cart, 0);

        return uri;
    }

    public Object showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null || session.getAttribute("userId") == null ) {
            return request.getRequestDispatcher("login.jsp");
        }

        String userId = session.getAttribute("userId").toString();

        List<Cart> carts = userService.queryCarts(userId);
        BigDecimal total = BigDecimal.ZERO;
        for(Cart cart: carts) {
            total = total.add(cart.getTotal());
        }

        request.setAttribute("carts", carts);
        request.setAttribute("cartsTotal", total);
        session.setAttribute("cartTotal", total);

        uri = request.getRequestDispatcher("cart.jsp");

        return uri;
    }

    public Object cartAlter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null || session.getAttribute("userId") == null ) {
            return request.getRequestDispatcher("login.jsp");
        }

        String al = request.getParameter("al");
        String cartId = request.getParameter("cartId");
        Cart cart = new Cart();
        cart.setId(cartId);
        if(al.trim().equals("add")) {
            userService.alterCart(cart, 1);
        } else {
            userService.alterCart(cart, -1);
        }

        uri = showCart(request,response);

        return uri;
    }

    public Object pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        if(session == null || session.getAttribute("userId") == null ) {
            return request.getRequestDispatcher("login.jsp");
        }

        String userId = session.getAttribute("userId").toString();
        List<Cart> carts = userService.queryCarts(userId);
        String commodityInfo = "";
        for(Cart cart: carts) {
            Commodity commodity = adminService.findCommodityById(cart.getCommodityId());
            commodity.setNumber((Integer.parseInt(commodity.getNumber()) - Integer.parseInt(cart.getNumber()))+"");
            userService.updateCommodityNumber(commodity);
            userService.alterCart(cart, 2);
            commodityInfo += cart.getCommodityId() +":"+ cart.getCommodityName() + ":" + cart.getNumber() + ":" + commodity.getPrice() + ":" + commodity.getThePurchasePrice() + ";";
        }

        OrderFrom order = new OrderFrom(userId);
        order.setOrderStat(OrderStat.IN_TRANSIT);
        order.setName(request.getParameter("name"));
        order.setPhone(request.getParameter("phone"));
        order.setDeliveryAddress(request.getParameter("deliveryAddress"));
        order.setOrderDetails(commodityInfo.substring(0, commodityInfo.length()-1));
        order.setOrderTotal(new BigDecimal(session.getAttribute("cartTotal").toString()));
        userService.placeAnOrder(order);

        uri = showOrderFrom(request, response);

        return uri;
    }



}
