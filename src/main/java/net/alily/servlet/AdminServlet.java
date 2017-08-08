package net.alily.servlet;

import net.alily.conf.SelectType;
import net.alily.entity.*;
import net.alily.utils.Condition;
import net.alily.utils.PageBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

/**
 * 管理员Servlet
 * Created by Alan on 5/22/17.
 */
public class AdminServlet extends BaseServlet {
    private Logger logger = Logger.getLogger(AdminServlet.class);

    public AdminServlet() {
        super();
    }

    public Object checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();

        String userName = request.getParameter("userName");
        String userCode = request.getParameter("userCode");

        if (userName != null && !"".equals(userName.trim()) && userCode != null && !"".equals(userCode.trim())) {
            User admin = new User();
            admin.setUserName(userName);
            admin.setPassword(userCode);

            String adminId = adminService.checkLogin(admin);

            if (adminId != null && !adminId.trim().equals("")) {
                session.setAttribute("adminName", userName);
                session.setAttribute("adminId", adminId);

                uri = showUsers(request, response);
            } else {
                uri = request.getRequestDispatcher("/backstage/AdminLogin.jsp");
            }
        } else {
            uri = request.getRequestDispatcher("/backstage/AdminLogin.jsp");
        }

        return uri;

    }

    /**
     * 添加商品
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object insertCommodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);
        if(session == null){
            //登陆失败
            uri = request.getRequestDispatcher("backstage/AdminLogin.jsp");
            return uri;
        }
        //获取用户名
        String userId = String.valueOf( session.getAttribute("userId"));

        if(userId == null){
            //登陆失败
            uri = request.getRequestDispatcher("backstage/AdminLogin.jsp");
            return uri;
        }

        Commodity commodity = new Commodity();

        //创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建文件上传核心类对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置单个文件最大值
        upload.setFileSizeMax(10 * 1024 * 1024);
        //设置全部文件最大值
        upload.setSizeMax(50 * 1024 *1024);
        //判断是否为文件上传表单
        if(upload.isMultipartContent(request)){
            try {
                List<FileItem> items = upload.parseRequest(request);
                for(FileItem item : items){
                    if(item.isFormField()){
                        //普通表单
                        //获取元素名称
                        String fieldName = item.getFieldName();
                        //获取元素的值
                        String value = item.getString("UTF-8");

                        if(fieldName != null && !"".equals(fieldName.trim())){
                            switch (fieldName) {
                                case "commodityId" :
                                    commodity.setCommodityId(value);
                                    break;
                                case "commodityName" :
                                    commodity.setCommodityName(value);
                                    break;
                                case "price" :
                                    commodity.setPrice(new BigDecimal(value));
                                    break;
                                case "commodityType" :
                                    commodity.setCommodityType(value);
                                    break;
                                case "store" :
                                    commodity.setStore(value);
                                    break;
                                case "brand" :
                                    commodity.setBrand(value);
                                    break;
                                case "number" :
                                    commodity.setNumber(value);
                                    break;
                                case "thePurchasePrice" :
                                    commodity.setThePurchasePrice(new BigDecimal(value));
                                    break;
                                case "illustrate" :
                                    commodity.setIllustrate(value);
                                    break;
                                case "costPrice" :
                                    commodity.setCostPrice(new BigDecimal(value));
                                    break;
                                case "source" :
                                    commodity.setSource(value);
                                    break;
                            }

                        }

                    } else {
                        //处理文件上传
                        //获取文件名
                        if(item.getSize() > 10){
                        String[] fileNames = item.getName().split("\\u005C");
                        //文件名处理
                        String id = UUID.randomUUID().toString();
                        String name =id + "-" + fileNames[fileNames.length-1];
                        //获取上传路径
                        //String basePath = getServletContext().getRealPath("backstage/upload");
                        //String basePath = "/home/Alan/Procedure/GraduationProject/pictureServer/" + commodity.getCommodityType() + "/";
                        String basePath = request.getSession().getServletContext().getRealPath("/upload");
                        //创建文件夹
                        File dir = new File(basePath);
                        if(!dir.exists()) {
                            boolean mkDir = dir.mkdir();
                            logger.info("Create dir " + basePath + " " + mkDir);
                        }
                        //创建文件对象
                        File file = new File(basePath, name);

                        item.write(file);
                        commodity.setPicture(name);
                        item.delete();
                        } else {
                            String picture = item.getFieldName();
                            if (picture != null) {
                                commodity.setPicture(picture);
                                logger.info("Update commodity picture: " + picture);
                            } else {
                                commodity.setPicture("notFound.jpg");
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int isInsert = Integer.parseInt(request.getParameter("isInsert"));
        commodity.setInsert(isInsert);
        if(adminService.insertCommodity(commodity)){
            // 转发
            if(isInsert == 1) {
                uri = request.getRequestDispatcher("backstage/CommodityInsert.jsp");
            } else {
                request.setAttribute("commodity", commodity);
                uri = request.getRequestDispatcher("backstage/CommodityAlter.jsp");
            }
        }

        return uri;
    }

    /**
     * 用户展示
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object showUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object uri = null;

        // 获取 pageBean
        PageBean<UserDetailedInfo> pageBean = new PageBean<UserDetailedInfo>();
        // 第一次访问 设置当前页为 1
        String currentPage = request.getParameter("currentPage");
        // 判断
        if (currentPage == null || "".equals(currentPage.trim())) {
            // 第一次访问 当前页设置为 1
            pageBean.setCurrentPage(1);
        } else {
            // 设置当前页参数
            pageBean.setCurrentPage(Integer.parseInt(currentPage));
        }
        // 条件对象
        Condition condition = new Condition();
        // 分页参数
        String userId = request.getParameter("inUserId");
        String name = request.getParameter("inName");

        condition.setSelectType(SelectType.USER);
        condition.setUserId(userId);
        condition.setName(name);

        // 设置条件对象到 pageBean中
        pageBean.setCondition(condition);
        // 分页查询
        adminService.queryUsers(pageBean);
        // 保存 pageBean 到域对象中
        request.setAttribute("pageBean", pageBean);

        // 转发
        uri = request.getRequestDispatcher("/backstage/UserManager.jsp");
        return uri;

    }

    /**
     * 添加用户
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;

        session = request.getSession(false);
        if(session == null){
            //登陆失败
            uri = request.getRequestDispatcher("backstage/AdminLogin.jsp");
            return uri;
        }

        String userName = request.getParameter("userName");
        String password = request.getParameter("userCode");
        String againUserCode = request.getParameter("againUserCode");

        if(!password.equals(againUserCode)) {
            return request.getRequestDispatcher("backstage/UserInsert.jsp");
        }

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setLevel("1");

        UserDetailedInfo userDetailedInfo = new UserDetailedInfo();
        userDetailedInfo.setName(name);
        userDetailedInfo.setAge(Integer.parseInt(age));
        if(sex.equals("女")) {
            sex = "0";
        } else {
            sex = "1";
        }
        userDetailedInfo.setSex(Integer.parseInt(sex));
        userDetailedInfo.setPhone(phone);
        userDetailedInfo.setEmail(email);
        userDetailedInfo.setAddress(address);

        if(adminService.save(user)) {
            user = adminService.findByUserName(user.getUserName());
            userDetailedInfo.setId(user.getId());
            adminService.update(user, userDetailedInfo);
            uri = request.getRequestDispatcher("backstage/UserInsert.jsp");
        }

        return uri;
    }

    /**
     * 删除用户
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;

        session = request.getSession(false);
        if(session == null){
            //登陆失败
            uri = request.getRequestDispatcher("backstage/AdminLogin.jsp");
            return uri;
        }
        String id = request.getParameter("userId");
        adminService.delete(Integer.parseInt(id));

        uri = showUsers(request, response);


        return uri;
    }

    /**
     * 用户操作信息读取
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object alterUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession();
        if(session == null){
            //登陆失败
            uri = request.getRequestDispatcher("backstage/AdminLogin.jsp");
            return uri;
        }

        String id = request.getParameter("userId");
        UserInfo userInfo = adminService.findUserInfoById(id);

        if(userInfo != null) {
            request.setAttribute("userInformation", userInfo);
            uri = request.getRequestDispatcher("backstage/UserAlter.jsp");
        } else {
            uri = request.getRequestDispatcher("backstage/UserAlter.jsp");
        }

        return uri;

    }

    /**
     * 更新用户信息
     * @param request 请求
     * @param response 响应
     * @return Object
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    public Object updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            return request.getRequestDispatcher("backstage/AdminLogin.jsp");
        }

        String id = request.getParameter("id");
        String level = request.getParameter("level");
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
        if(sex.equals("女")) {
            sex = "0";
        } else {
            sex = "1";
        }
        userDetailedInfo.setSex(Integer.parseInt(sex));
        userDetailedInfo.setPhone(phone);
        userDetailedInfo.setEmail(email);
        userDetailedInfo.setAddress(address);

        if(adminService.update(user, userDetailedInfo)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUser(user);
            userInfo.setUserDetailedInfo(userDetailedInfo);
            request.setAttribute("userInformation", userInfo);
            uri = request.getRequestDispatcher("backstage/UserAlter.jsp");
        } else {
            uri = request.getRequestDispatcher("backstage/UserAlter.jsp");
        }


        return uri;
    }


    public Object showCommodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            return request.getRequestDispatcher("backstage/AdminLogin.jsp");
        }

        List<String> stores = adminService.queryCondition("store");
        List<String> types = adminService.queryCondition("commodityType");
        List<String> brands = adminService.queryCondition("brand");

        request.setAttribute("stores", stores);
        request.setAttribute("types", types);
        request.setAttribute("brands", brands);

        // 获取 pageBean
        PageBean<Commodity> pageBean = new PageBean<>();
        // 第一次访问 设置当前页为 1
        String currentPage = request.getParameter("currentPage");
        // 判断
        if (currentPage == null || "".equals(currentPage.trim())) {
            // 第一次访问 当前页设置为 1
            pageBean.setCurrentPage(1);
        } else {
            // 设置当前页参数
            pageBean.setCurrentPage(Integer.parseInt(currentPage));
        }
        // 条件对象
        Condition condition = new Condition();
        // 分页参数
        String checkStore = request.getParameter("checkStore");
        String checkType = request.getParameter("checkType");
        String checkBrand = request.getParameter("checkBrand");

        condition.setSelectType(SelectType.COMMODITY_PRECISE);
        if(checkStore != null && checkStore.trim().equals("ALL")) {
            condition.setStore(null);
        }else {
            condition.setStore(checkStore);
        }

        if(checkType != null && checkType.trim().equals("ALL")) {
            condition.setCommodityType(null);
        }else {
            condition.setCommodityType(checkType);
        }

        if(checkBrand != null && checkBrand.trim().equals("ALL")) {
            condition.setBrand(null);
        }else {
            condition.setBrand(checkBrand);
        }



        // 设置条件对象到 pageBean中
        pageBean.setCondition(condition);
        // 分页查询
        adminService.queryCommodities(pageBean);
        // 保存 pageBean 到域对象中
        request.setAttribute("adminPageBean", pageBean);

        // 转发
        uri = request.getRequestDispatcher("/backstage/CommodityManager.jsp");


        return uri;
    }

    public Object commodityAlter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            return request.getRequestDispatcher("backstage/AdminLogin.jsp");
        }

        String commodityId = request.getParameter("commodityId");
        Commodity commodity = adminService.findCommodityById(commodityId);

        request.setAttribute("commodity", commodity);

        uri = request.getRequestDispatcher("backstage/CommodityAlter.jsp");

        return uri;
    }

    /**
     * 商品删除
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object commodityDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            return request.getRequestDispatcher("backstage/AdminLogin.jsp");
        }

        String commodityId = request.getParameter("commodityId");

        if(adminService.commodityDelete(commodityId)) {
            uri = showCommodity(request, response);
        } else {
            uri = request.getRequestDispatcher("error/error.jsp");
        }

        return uri;
    }

    public Object showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            return request.getRequestDispatcher("backstage/AdminLogin.jsp");
        }

        // 获取 pageBean
        PageBean<OrderFrom> pageBean = new PageBean<>();
        // 第一次访问 设置当前页为 1
        String currentPage = request.getParameter("currentPage");
        // 判断
        if (currentPage == null || "".equals(currentPage.trim())) {
            // 第一次访问 当前页设置为 1
            pageBean.setCurrentPage(1);
        } else {
            // 设置当前页参数
            pageBean.setCurrentPage(Integer.parseInt(currentPage));
        }
        // 条件对象
        Condition condition = new Condition();
        // 分页参数
        String orderId = request.getParameter("orderId");
        String orderPhone = request.getParameter("orderPhone");

        condition.setSelectType(SelectType.ORDER);
        condition.setOrderId(orderId);
        condition.setPhone(orderPhone);

        // 设置条件对象到 pageBean中
        pageBean.setCondition(condition);
        // 分页查询
        adminService.queryOrders(pageBean);
        // 保存 pageBean 到域对象中
        request.setAttribute("orderPageBean", pageBean);

        // 转发
        uri = request.getRequestDispatcher("/backstage/OrderManager.jsp");


        return uri;
    }

    public Object updateCommodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return insertCommodity(request, response);
    }

    public Object orderAlter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            return request.getRequestDispatcher("backstage/AdminLogin.jsp");
        }

        String orderNumber = request.getParameter("orderNumber");

        OrderFrom order = adminService.queryOrderById(orderNumber);

        request.setAttribute("order", order);

        uri = request.getRequestDispatcher("/backstage/OrderAlter.jsp");

        return uri;
    }

    public Object updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            return request.getRequestDispatcher("backstage/AdminLogin.jsp");
        }

        String orderNumber = request.getParameter("orderNumber");
        String presetId  = request.getParameter("presetId");
        String orderDetails  = request.getParameter("orderDetails");
        String orderTime  = request.getParameter("orderTime");
        String orderTotal  = request.getParameter("orderTotal");
        String deliveryAddress  = request.getParameter("deliveryAddress");
        String phone  = request.getParameter("phone");
        String orderStat   = request.getParameter("orderStat");

        OrderFrom order = new OrderFrom();
        order.setOrderNumber(orderNumber);
        order.setPresetId(presetId);
        order.setOrderDetails(orderDetails);
        try {
            order.setOrderTime(orderTime);
        } catch (ParseException e) {
            logger.equals(e);
            e.printStackTrace();
        }
        order.setOrderTotal(new BigDecimal(orderTotal));
        order.setDeliveryAddress(deliveryAddress);
        order.setPhone(phone);
        order.setOrderStat(orderStat);

        if(adminService.updateOrder(order)) {
            request.setAttribute("order", order);
            uri = request.getRequestDispatcher("/backstage/OrderAlter.jsp");
        } else {
            uri = request.getRequestDispatcher("/error/error.jsp");
        }

        return uri;
    }


    public Object orderDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        session = request.getSession(false);

        if(session == null) {
            return request.getRequestDispatcher("backstage/AdminLogin.jsp");
        }

        String orderNumber = request.getParameter("orderNumber");

        adminService.deleteOrder(orderNumber);

        uri = showOrders(request, response);

        return uri;
    }

}
