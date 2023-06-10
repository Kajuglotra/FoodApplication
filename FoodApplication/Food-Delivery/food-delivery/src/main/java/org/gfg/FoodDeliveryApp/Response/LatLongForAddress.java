package org.gfg.FoodDeliveryApp.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LatLongForAddress {
    String latitude;

    String longitude;

}
