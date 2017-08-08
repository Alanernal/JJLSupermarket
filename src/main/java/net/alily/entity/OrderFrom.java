package net.alily.entity;

import net.alily.conf.OrderStat;
import net.alily.utils.IDate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 订单类 OrderFrom
 * Created by Alan on 5/13/17.
 */
public class OrderFrom {
    //订单编号
    private String orderNumber;
    //收货人姓名
    private String name;
    //预定人ID
    private String presetId;
    //商品信息 商品id:商品名:商品数量:商品价格:会员价; 商品id:商品名:商品数量:商品价格:会员价 ...
    private String orderDetails;
    //下单时间
    private String orderTime;
    //订单总价
    private BigDecimal orderTotal;
    //送货地址
    private String deliveryAddress;
    //收件人电话
    private String phone;
    //订单状态
    private String orderStat;
    //时间戳
    private long timeStamp;
    //商品集合
    private ArrayList<CommodityOfOrder> commodityOfOrders;

    public OrderFrom() {}

    public OrderFrom(String presetId) {
        setPresetId(presetId);
        long timeStamp = IDate.nowTimeStamp();
        setOrderNumber(presetId+timeStamp);
        setOrderTime(timeStamp);
        setTimeStamp(timeStamp);
    }

    public OrderFrom(String presetId, long timeStamp) {
        setOrderNumber(presetId+timeStamp);
        setOrderTime(timeStamp);
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        if(orderTotal.compareTo(BigDecimal.ZERO) < 0) {
            this.orderTotal = BigDecimal.ZERO;
        } else {
            this.orderTotal = orderTotal;
        }

    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = IDate.formatTimeStamp(orderTime);
    }

    public String getPresetId() {
        return presetId;
    }

    public void setPresetId(String presetId) {
        this.presetId = presetId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(String orderStat) {
        this.orderStat = orderStat;
    }

    public ArrayList<CommodityOfOrder> getCommodityOfOrders() {
        ArrayList<CommodityOfOrder> commodityOfOrders = null;
        if(orderDetails != null && !orderDetails.equals("")) {
            commodityOfOrders = new ArrayList<CommodityOfOrder>();
            for(String s: orderDetails.split(";")) {
                String[] commodity = s.split(":");
                CommodityOfOrder commodityOfOrder = new CommodityOfOrder();
                commodityOfOrder.setCommodityId(commodity[0]);
                commodityOfOrder.setCommodityName(commodity[1]);
                commodityOfOrder.setCommoditySum(commodity[2]);
                commodityOfOrder.setPrice(new BigDecimal(commodity[3]));
                commodityOfOrder.setVipPrice(new BigDecimal(commodity[4]));
                commodityOfOrders.add(commodityOfOrder);
            }
        }

        return commodityOfOrders;
    }
    public void setCommodityOfOrders(ArrayList<CommodityOfOrder> commodityOfOrders) {
        this.commodityOfOrders = commodityOfOrders;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setOrderTime(String orderTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = simpleDateFormat.parse(orderTime);
        long ts = date.getTime();

        this.orderTime = orderTime;
        this.timeStamp = ts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
