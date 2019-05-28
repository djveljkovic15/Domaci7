package dzo.mvcrest.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository se ovde bavi imitacijom komunikacije sa bazom
 */
public class ShopRepository {

    private static String[] FIRST_NAME_LIST = {"Shop1", "Shop2", "Shop3", "Shop4", "Shop5"};
    private static List<Shop> shopList;

    static {
        shopList = generateShops();
    }

    /**
     * Generise 5 radnji
     * @return
     */
    private static List<Shop> generateShops() {
        List<Shop> shops = new ArrayList<>();
        //Random random = new Random();

        for (int i = 0; i < 5; i++) { //FIRST_NAME_LIST.length

            Shop shop = new Shop();
            shop.setId(i);
            shop.setShopName(FIRST_NAME_LIST[i]);
            shops.add(shop);
        }

        return shops;
    }


    /**
     * Vraca sve Shop-ove u sistemu.
     * Ako je firstName dato onda ce vratiti listu svih korisnika sa tim imenom.
     * @param shopName Opcioni filter za ime
     * @return
     */
    public synchronized static List<Shop> getShops(String shopName){

        if (shopName != null) {
            return shopList
                    .stream()
                    .filter(u -> u.getShopName().equalsIgnoreCase(shopName)).collect(Collectors.toList());
        }

        return shopList;
    }

    public synchronized static Shop getShopById(Integer id){
        return shopList.get(id);
    }
    public synchronized static Shop addShop(Shop shop){

        shop.setId(shopList.size());
        shopList.add(shop);

        return shop;
    }

    public synchronized static Shop getShopByName(String shopName) {
        if (shopName != null) {
            for(Shop shop: shopList){
                if(shop.getShopName().equalsIgnoreCase(shopName)){
                    return shop;
                }
            }
        }
        throw new Error("Shop not found!");
//
//        return shopList.stream()
//                .filter(shop -> shop.getShopName().equalsIgnoreCase(shopName))
//                .collect(Collectors.toList());
    }
}
