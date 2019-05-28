package dzo.mvcrest.coupon;

import dzo.mvcrest.shop.Shop;

import java.io.Serializable;
import java.util.List;

public class Coupon implements Serializable {

    private Integer id;
    private Shop shop;
    private String couponName;
    private float discountPrice;
    private float originalPrice;

    private List<Coupon> closeRelatives;

    public List<Coupon> getCloseRelatives() {
        return closeRelatives;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setCloseRelatives(List<Coupon> closeRelatives) {
        this.closeRelatives = closeRelatives;
    }
}
