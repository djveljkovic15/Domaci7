package dzo.mvcrest.coupon;

import java.util.List;

/**
 * Servisni sloj se bavi svom "biznis logikom"
 */
public class CouponService {

    public List<Coupon> getCoupons(String couponName){
        return CouponRepository.getCoupons(couponName);
    }

    public Coupon getCouponById(Integer id){
        return CouponRepository.getCouponById(id);
    }

    public Coupon addCoupon(Coupon coupon){
        return CouponRepository.addCoupon(coupon);
    }

    public void deleteCoupon(Integer id){
        CouponRepository.deleteCoupon(id);
    }
}
