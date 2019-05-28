package dzo.mvcrest.coupon;

import dzo.mvcrest.shop.ShopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Repository se ovde bavi imitacijom komunikacije sa bazom
 */
public class CouponRepository {

    private static String[] FIRST_NAME_LIST = {"John-James", "Justine", "Ahsan", "Leja", "Jad", "Vernon", "Cara", "Eddison", "Eira", "Emily"};
//    private static String[] LAST_NAME_LIST = {"Booker", "Summers", "Reyes", "Rahman", "Crane", "Cairns", "Hebert", "Bradshaw", "Shannon", "Phillips"};
//    private static List<Integer> ID_LIST = new ArrayList<>();
    private static AtomicInteger ID = new AtomicInteger(0);
    private static List<Coupon> couponList;

    static {
        couponList = generateCoupons();
    }

    /**
     * Generise 10 korisnika birajuci nasumicno imena i prezimena iz liste
     * @return
     */
    private static List<Coupon> generateCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            Coupon coupon = new Coupon();
//            ID.getAndIncrement();
            coupon.setId(ID.getAndIncrement());
            System.out.println(coupon.getId());
            coupon.setCouponName(FIRST_NAME_LIST[random.nextInt(FIRST_NAME_LIST.length)]);
            coupon.setShop(ShopRepository.getShopById(random.nextInt(5)));
            coupon.setOriginalPrice(random.nextInt(80) + 20);
            coupon.setDiscountPrice(coupon.getOriginalPrice()-random.nextInt(20));
//            ID_LIST.add(i);
            coupons.add(coupon);
        }

        return coupons;
    }

    /**
     * Vraca sve Coupon-e u sistemu.
     * Ako je firstName dato onda ce vratiti listu svih korisnika sa tim imenom.
     * @param couponName Opcioni filter za ime
     * @return
     */
    public synchronized static List<Coupon> getCoupons(String couponName){

        if (couponName != null) {
            return couponList
                    .stream()
                    .filter(u -> u.getCouponName().equalsIgnoreCase(couponName)).collect(Collectors.toList());
        }

        return couponList;
    }

    public synchronized static Coupon getCouponById(Integer id){
//        return couponList.get(id);
        if (id != null) {
            for(Coupon coup: couponList){
                if(coup.getId().equals(id)){
                    return coup;
                }
            }
        }
        throw new Error("Coup not found!");


    }
    public synchronized static Coupon addCoupon(Coupon coupon){
//        coupon.setId(ID_LIST.size());
//        ID_LIST.add()
        coupon.setId(ID.getAndIncrement());
//        coupon.setId(50);
        couponList.add(coupon);

        return coupon;
    }

    public synchronized static void deleteCoupon(Integer id){
        couponList.remove(getCouponById(id));
//        ID_LIST.remove(coupon.getId());
    }


}
