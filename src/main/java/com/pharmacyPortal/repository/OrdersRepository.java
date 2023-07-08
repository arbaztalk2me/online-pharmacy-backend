package com.pharmacyPortal.repository;

import com.pharmacyPortal.entity.Orders;
import com.pharmacyPortal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findByUser(User user);
    @Query("from Orders o where o.orderStatus='pending'")
    List<Orders> findOrdersByPending();

    @Query("from Orders o where o.orderStatus='confirm' and o.deliveredStatus='Delivered' and o.distributorId=:did")
    List<Orders>findOrderByDeliveredStatus(@Param("did") Integer did);

    @Query("from Orders o where o.orderStatus='confirm' and o.deliveredStatus='Not Delivered' and distributorId=:did")
    List<Orders>findOrderByUnDeliveredStatus(@Param("did") Integer did);
}
