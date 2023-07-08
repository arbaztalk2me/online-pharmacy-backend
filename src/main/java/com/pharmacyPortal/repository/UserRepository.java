package com.pharmacyPortal.repository;

import com.pharmacyPortal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("from User u where u.role='ROLE_CUSTOMER'")
    List<User> findAllByCustomer();
    @Query("from User u where u.role='ROLE_DISTRIBUTOR'")
    List<User>findAllByDistributor();
    Optional<User> findByEmail(String email);
}
