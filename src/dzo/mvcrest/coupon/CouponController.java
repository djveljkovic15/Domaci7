package dzo.mvcrest.coupon;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Controller ili Endpoint ce se baviti povezivanjem HTTP METODE/URL do biznis logike
 */
@Path("/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController() {
        this.couponService = new CouponService();
    }

    /**
     * Dohvatanje svih Coupon-a
     * Putanja je rest/coupons
     *
     * Takodje je moguce isfiltrirati kupone po imenu.
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON) //Bitno je da navedemo sta je rezultujuci content type ove metode
    public List<Coupon> getCoupons(@QueryParam("couponName") String couponName) {
        return couponService.getCoupons(couponName);
    }

    /**
     * Dohvatanje jednog resursa po ID-u.
     * Putanja bi bila npr. rest/coupons/4
     * @param id
     * @return
     */
    @GET
    @Path("/{id}") // {id} navodi da se radi o promenljivoj id koja ce se proslediti kao argument metode
    @Produces(MediaType.APPLICATION_JSON)
    public Coupon getCouponById(@PathParam("id") int id) { // Da bi se u tacan argument prosledio id mora da se oznaci anotacijom
        return couponService.getCouponById(id);
    }

    /**
     * Cuvanje jednog resursa. Rezultat je taj resurs sa ID-em.
     * Putanja je rest/coupons
     * @param coupon
     * @return
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON) // Prilikom POST-a nam je bitno sta je content type onoga sto ova metoda prima zbog deserijalizacije
    @Produces(MediaType.APPLICATION_JSON)
    public Coupon addCoupon(Coupon coupon){
        return couponService.addCoupon(coupon);
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCoupon(@PathParam("id") Integer id){
        couponService.deleteCoupon(id);
    }
}
