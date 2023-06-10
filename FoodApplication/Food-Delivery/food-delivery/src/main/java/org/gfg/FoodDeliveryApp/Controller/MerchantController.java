package org.gfg.FoodDeliveryApp.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.gfg.FoodDeliveryApp.Service.MerchantService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping("/restaurant/onboard")
    public ResponseEntity<JSONObject> restaurantOnboard (@RequestBody @Valid JSONObject restaurantOnboard) throws JsonProcessingException {
        return merchantService.restaurantOnboard(restaurantOnboard);
    }


}
