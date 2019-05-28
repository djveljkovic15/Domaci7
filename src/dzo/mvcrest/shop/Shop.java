package dzo.mvcrest.shop;

import java.io.Serializable;
import java.util.List;

public class Shop implements Serializable {
    private int id;
    private String shopName;

    private List<Shop> closeRelatives;

    public List<Shop> getCloseRelatives() {
        return closeRelatives;
    }

    public void setCloseRelatives(List<Shop> closeRelatives) {
        this.closeRelatives = closeRelatives;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
