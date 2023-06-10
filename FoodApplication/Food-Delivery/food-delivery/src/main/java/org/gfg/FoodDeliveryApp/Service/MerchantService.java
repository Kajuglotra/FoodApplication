package org.gfg.FoodDeliveryApp.Service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MerchantService {

    @Value(value =  "${merchant.host}")
    private String merchantHost;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<JSONObject> restaurantOnboard(JSONObject restaurantOnboard) {
        String url = merchantHost+"/api/addRestaurantDetails";
        return restTemplate.postForEntity(url, restaurantOnboard,JSONObject.class);
    }
}
