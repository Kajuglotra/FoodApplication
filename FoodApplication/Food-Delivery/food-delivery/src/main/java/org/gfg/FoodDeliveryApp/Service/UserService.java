package org.gfg.FoodDeliveryApp.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gfg.FoodDeliveryApp.Model.User;
import org.gfg.FoodDeliveryApp.Repository.UserRepository;
import org.gfg.FoodDeliveryApp.Request.UserRequest;
import org.gfg.FoodDeliveryApp.Response.LatLongForAddress;
import org.gfg.utils.CommonConstants;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value(value = "${google.api.host}")
    private String googleHost;
    @Value(value = "${google.api.key}")
    private String googleKey;

    @Autowired
    private RestTemplate restTemplate;

    public User userOnboard(UserRequest userRequest) throws JsonProcessingException {
        User user = userRequest.toUser();
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        LatLongForAddress latLongForAddress = getLtLgFromAddress(userRequest.getAddress());
        user.setLatitude(latLongForAddress.getLatitude());
        user.setLongitude(latLongForAddress.getLongitude());
        User userFromDb = userRepository.save(user);
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put(CommonConstants.USER_CREATION_NAME, userFromDb.getName());
        jsonObject.put(CommonConstants.USER_CREATION_EMAIL , userFromDb.getEmail());
        kafkaTemplate.send(CommonConstants.USER_CREATION_TOPIC, objectMapper.writeValueAsString(jsonObject));
        return userFromDb;
    }

    private LatLongForAddress getLtLgFromAddress(String address) {
       String responseType= "json";
       address = address.replaceAll(" ","+");
       String url= googleHost+responseType+"?key="+googleKey+"&address="+address;
       Object o  = restTemplate.getForObject(url, JSONObject.class).get("results");
       Object o1 = ((LinkedHashMap) ((ArrayList) o).get(0)).get("geometry");
       Object location = ((LinkedHashMap) o1).get("location");
        LatLongForAddress latlong = LatLongForAddress.builder().
               latitude(((LinkedHashMap) location).get("lat").toString()).
               longitude(((LinkedHashMap) location).get("lng").toString()).
               build();

        return latlong;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
