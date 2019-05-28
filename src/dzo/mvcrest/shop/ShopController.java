package dzo.mvcrest.shop;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Controller ili Endpoint ce se baviti povezivanjem HTTP METODE/URL do biznis logike
 */
@Path("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController() {
        this.shopService = new ShopService();
    }

    /**
     * Dohvatanje svih Shop-a
     * Putanja je rest/shops
     *
     * Takodje je moguce isfiltrirati shopove po imenu.
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON) //Bitno je da navedemo sta je rezultujuci content type ove metode
    public List<Shop> getShops(@QueryParam("shopName") String shopName) {
        return shopService.getShops(shopName);
    }


    /**
     * Dohvatanje jednog resursa po ID-u.
     * Putanja bi bila npr. rest/shops/4
     * @param id
     * @return
     */
    @GET
    @Path("/{id}") // {id} navodi da se radi o promenljivoj id koja ce se proslediti kao argument metode
    @Produces(MediaType.APPLICATION_JSON)
    public Shop getShopById(@PathParam("id") int id) { // Da bi se u tacan argument prosledio id mora da se oznaci anotacijom
        return shopService.getShopById(id);
    }

    /**
     * Cuvanje jednog resursa. Rezultat je taj resurs sa ID-em.
     * Putanja je rest/shops
     * @param shop
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON) // Prilikom POST-a nam je bitno sta je content type onoga sto ova metoda prima zbog deserijalizacije
    @Produces(MediaType.APPLICATION_JSON)
    public Shop addShop(Shop shop){
        shopService.addShop(shop);
        return shop;
    }


    @GET
    @Path("/name/{shopName}")
    @Produces(MediaType.APPLICATION_JSON) //Bitno je da navedemo sta je rezultujuci content type ove metode
    public Shop getShopByName(@PathParam("shopName") String shopName) {
        return shopService.getShopByName(shopName);
    }

}
