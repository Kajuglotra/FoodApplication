package org.gfg.FoodDeliveryApp.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOnboard {
    @NonNull
    private String restName;
    @NonNull
    private String location;
    @NonNull
    private String startTime;
    @NonNull
    private String endTime;

}
