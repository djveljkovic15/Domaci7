package dzo.mvcrest.shop;

import java.util.List;

/**
 * Servisni sloj se bavi svom "biznis logikom"
 */
public class ShopService {

    public List<Shop> getShops(String shopName){
        return ShopRepository.getShops(shopName);
    }

    public Shop getShopById(Integer id){
        return ShopRepository.getShopById(id);
    }

    public Shop addShop(Shop shop){
        return ShopRepository.addShop(shop);
    }

    public Shop getShopByName(String shopName) {
        return ShopRepository.getShopByName(shopName);
    }
}
