package org.gfg.FoodDeliveryApp.Repository;

import org.gfg.FoodDeliveryApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

}
