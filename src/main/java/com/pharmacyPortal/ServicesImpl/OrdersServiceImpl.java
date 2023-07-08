package com.pharmacyPortal.ServicesImpl;

import com.pharmacyPortal.Exception.MedicineNotFoundException;
import com.pharmacyPortal.Exception.OrdersNotFoundException;
import com.pharmacyPortal.entity.Medicine;
import com.pharmacyPortal.entity.Orders;
import com.pharmacyPortal.repository.MedicineRepository;
import com.pharmacyPortal.repository.OrdersRepository;
import com.pharmacyPortal.service.MedicineService;
import com.pharmacyPortal.service.OrdersService;
import com.pharmacyPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MedicineService medicineService;

    @Override
    public Orders setOrders(Orders ord) {
        ord.setOrderStatus("pending");
        ord.setDeliveredStatus("Not Delivered");
        Medicine medicine = ord.getMedicine();
        Medicine byId = this.medicineService.findMedicineById(medicine.getId());
        ord.setAmount(byId.getMedicinePrice()*ord.getQuantity());
        byId.setQuantity(byId.getQuantity()-ord.getQuantity());
        this.medicineService.updateMedicine(byId,byId.getId());

        return this.ordersRepository.save(ord);
    }

    @Override
    public Orders updateOrdersByAdmin(Integer orderId) {
        Orders orders1 = this.ordersRepository.findById(orderId).orElseThrow(() -> new OrdersNotFoundException("Orders not found with this id " + orderId));
        orders1.setOrderStatus("confirm");
        return ordersRepository.save(orders1);
    }

    @Override
    public Orders updateCancelOrdersByAdmin(Integer orderId,Integer mId) {
        Orders orders1 = this.ordersRepository.findById(orderId).orElseThrow(() -> new OrdersNotFoundException("Orders not found with this id " + orderId));
        orders1.setOrderStatus("cancel");
        Integer Id = orders1.getMedicine().getId();
        Medicine medicineById = this.medicineService.findMedicineById(Id);
        medicineById.setQuantity(medicineById.getQuantity()+orders1.getQuantity());
        this.medicineService.addMedicine(medicineById);
        return ordersRepository.save(orders1);
    }

    @Override
    public Orders updateCancelOrdersByCustomer(Integer orderId, Integer mid) {
        Orders orders1 = this.ordersRepository.findById(orderId).orElseThrow(() -> new OrdersNotFoundException("Orders not found with this id " + orderId));
        orders1.setOrderStatus("cancel");
        Integer Id = orders1.getMedicine().getId();
        Medicine medicineById = this.medicineService.findMedicineById(Id);
        medicineById.setQuantity(medicineById.getQuantity()+orders1.getQuantity());
        this.medicineService.addMedicine(medicineById);
        return ordersRepository.save(orders1);
    }

    @Override
    public Orders updateOrdersByDistributor(Integer orderId,Integer did) throws Exception {
        Orders orders1 = this.ordersRepository.findById(orderId).orElseThrow(() -> new OrdersNotFoundException("Orders not found with this id " + orderId));
        if(orders1.getDistributorId()==did){
            orders1.setDeliveredStatus("Delivered");
            return this.ordersRepository.save(orders1);
        }
        throw new Exception("Distributor is not matched login and order one");
    }

    @Override
    public List<Orders> findOrdersByUser(int id) {
        return this.ordersRepository.findByUser(this.userService.getUserById(id));
    }

    @Override
    public List<Orders> findByOrderStatus() {
        return this.ordersRepository.findOrdersByPending();
    }

    @Override
    public Orders findById(int id) {
        return this.ordersRepository.findById(id).orElseThrow(()->new OrdersNotFoundException("Orders not found with this id "+id));
    }

    @Override
    public List<Orders> findAllUndeliveryOrder(int did) {
        return this.ordersRepository.findOrderByUnDeliveredStatus(did);
    }

    @Override
    public List<Orders> findAllDeliveryOrder(int did) {
        return this.ordersRepository.findOrderByDeliveredStatus(did);
    }

    @Override
    public void deleteOrder(int id) {

    }
}
