package dzo.mvcrest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/dzo")
public class RestApp extends ResourceConfig {

    public RestApp() {
         packages("dzo.mvcrest.coupon", "dzo.mvcrest.shop");
    }
}
