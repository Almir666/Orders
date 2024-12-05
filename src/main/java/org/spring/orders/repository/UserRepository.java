package org.spring.orders.repository;

import org.spring.orders.model.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<StoreUser, Long> {
    StoreUser findByName(String name);
}
