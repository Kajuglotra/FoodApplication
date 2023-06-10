package org.gfg.MerchantService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;
    public Restaurant restaurantOnboard(JSONObject restaurantOnboard) {
        Restaurant restaurant = Restaurant.builder().
                build();
        merchantRepository.save(restaurant);
        return restaurant;
    }
}
