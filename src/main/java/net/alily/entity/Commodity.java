package net.alily.entity;

import net.alily.utils.IDate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 商品类 Commodity
 * Created by Alan on 5/13/17.
 */
public class Commodity {
    private String commodityId;
    private String commodityName;
    //折扣价
    private BigDecimal thePurchasePrice;
    //实际价
    private BigDecimal price;
    //成本价
    private BigDecimal costPrice;
    //商品类型
    private String commodityType;
    //商品标签
    private String brand;
    //商品图片
    private String picture;
    //商品主题
    private String store;
    //数量
    private String number;
    //折扣
    private String discount;
    //状态
    private String status;
    //说明
    private String illustrate;
    //说明
    private String source;
    //更新或者插入
    private int isInsert;


    //计算折扣
    public String getDiscount() {
        if(this.price.compareTo(BigDecimal.ZERO) <= 0) {
            discount = "No";
            status = "";
        } else if (this.thePurchasePrice.compareTo(this.price) == 0){
            discount = "New";
            status = "";
        } else if (this.thePurchasePrice.compareTo(this.price) > 0) {
            discount = "Err";
            status = "";
        } else {
            discount = (this.thePurchasePrice.divide(this.price,2, RoundingMode.HALF_UP)).multiply(new BigDecimal(10)) + "";
            status = "折";
        }

        return discount;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) < 0) {
            this.price = BigDecimal.ZERO;
        } else {
            this.price = price;
        }
    }

    public BigDecimal getThePurchasePrice() {
        if(this.thePurchasePrice.compareTo(BigDecimal.ZERO) <= 0) {
            return price;
        }

        return thePurchasePrice;
    }

    public void setThePurchasePrice(BigDecimal thePurchasePrice) {
        if(thePurchasePrice.compareTo(BigDecimal.ZERO) < 0) {
            this.thePurchasePrice = this.price;
        } else {
            this.thePurchasePrice = thePurchasePrice;
        }
    }

    public String getCommodityId() {
        if(commodityId == null) {
            return IDate.nowTimeStamp() + "";
        }
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int isInsert() {
        return isInsert;
    }

    public void setInsert(int insert) {
        isInsert = insert;
    }
}
