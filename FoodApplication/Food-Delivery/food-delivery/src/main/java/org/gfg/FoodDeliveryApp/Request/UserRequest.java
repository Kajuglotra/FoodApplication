package org.gfg.FoodDeliveryApp.Request;

import lombok.*;
import org.gfg.FoodDeliveryApp.Model.User;

import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    private String name;

    private String password;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    private String userType;

    private String address;

    public User toUser() {
        return User.builder().
                name(this.name).
                email(this.email).
                password(this.password).
                phone(this.phone).
                userType(this.userType).
                build();
    }
}
