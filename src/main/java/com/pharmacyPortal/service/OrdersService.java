package com.pharmacyPortal.service;

import com.pharmacyPortal.entity.Orders;

import java.util.List;

public interface OrdersService {
    Orders setOrders(Orders ord);
    Orders updateOrdersByAdmin(Integer orderId);

    Orders updateCancelOrdersByAdmin(Integer orderId,Integer mid);

    Orders updateCancelOrdersByCustomer(Integer orderId,Integer mid);

    Orders updateOrdersByDistributor(Integer orderId,Integer did) throws Exception;
    List<Orders> findOrdersByUser(int id);
    List<Orders> findByOrderStatus();
    Orders findById(int id);
    List<Orders> findAllUndeliveryOrder(int did);
    List<Orders> findAllDeliveryOrder(int did);
    void deleteOrder(int id);
}
