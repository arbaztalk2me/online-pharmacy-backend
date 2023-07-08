package com.pharmacyPortal.controller;

import com.pharmacyPortal.entity.Orders;
import com.pharmacyPortal.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @PostMapping("/addOrders")
    private ResponseEntity<Orders>createOrders(@RequestBody Orders orders){
        return new ResponseEntity<>(this.ordersService.setOrders(orders), HttpStatus.CREATED);
    }

    @GetMapping("/getOrderById/{id}")
    private ResponseEntity<Orders>createOrders(@PathVariable Integer id){
        return new ResponseEntity<>(this.ordersService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllOrderByUser/{id}")
    private ResponseEntity<List<Orders>>getAllOrdersByUser(@PathVariable Integer id){
        return new ResponseEntity<>(this.ordersService.findOrdersByUser(id), HttpStatus.OK);
    }

    @GetMapping("/getAllOrderByPending")
    private ResponseEntity<List<Orders>>getAllOrderByPending(){
        return new ResponseEntity<>(this.ordersService.findByOrderStatus(), HttpStatus.OK);
    }
    @PutMapping("/updateOrderByAdmin/{oId}")
    private ResponseEntity<Orders>updateOrderByAdmin(@PathVariable Integer oId){
        return new ResponseEntity<>(this.ordersService.updateOrdersByAdmin(oId), HttpStatus.OK);
    }

    @PutMapping("/updateOrderByAdminCancel/{oId}/admin/{mId}")
    private ResponseEntity<Orders>updateCancelOrderByAdmin(@PathVariable Integer oId,@PathVariable Integer mId){
        return new ResponseEntity<>(this.ordersService.updateCancelOrdersByAdmin(oId,mId), HttpStatus.OK);
    }

    @PutMapping("/updateOrderByDistributor/{oId}/distributor/{did}")
    private ResponseEntity<Orders>updateOrderByDistributor(@PathVariable Integer oId,@PathVariable Integer did) throws Exception {
        return new ResponseEntity<>(this.ordersService.updateOrdersByDistributor(oId,did), HttpStatus.OK);
    }

    @PutMapping("/updateOrderByCustomer/{oId}/customer/{mId}")
    private ResponseEntity<Orders>updateOrderByCustomer(@PathVariable Integer oId,@PathVariable Integer mId) throws Exception {
        return new ResponseEntity<>(this.ordersService.updateCancelOrdersByCustomer(oId,mId), HttpStatus.OK);
    }


    @GetMapping("/getAllUndeliveredOrders/{did}")
    private ResponseEntity<List<Orders>>getAllUndeliveredOrders(@PathVariable Integer did){
        return new ResponseEntity<>(this.ordersService.findAllUndeliveryOrder(did), HttpStatus.OK);
    }

    @GetMapping("/getAllDeliveredOrders/{did}")
    private ResponseEntity<List<Orders>>getAllDeliveredOrders(@PathVariable Integer did){
        return new ResponseEntity<>(this.ordersService.findAllDeliveryOrder(did), HttpStatus.OK);
    }
}
