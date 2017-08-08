package net.alily.entity;

import java.math.BigDecimal;

/**
 * 商品信息在订单中表示
 * Created by Alan on 5/17/17.
 */
public class CommodityOfOrder {
    private String commodityId;
    private String commodityName;
    private String commoditySum;
    private BigDecimal price;
    private BigDecimal vipPrice;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setCommoditySum(String commoditySum) {
        this.commoditySum = commoditySum;
    }

    public String getCommoditySum() {
        return commoditySum;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }
}
