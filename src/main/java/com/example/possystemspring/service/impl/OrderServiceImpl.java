package com.example.possystemspring.service.impl;

import com.example.possystemspring.dao.CustomerDao;
import com.example.possystemspring.dao.ItemDao;
import com.example.possystemspring.dao.OrderDao;
import com.example.possystemspring.dto.impl.ItemDTO;
import com.example.possystemspring.dto.impl.OrderDTO;
import com.example.possystemspring.entity.impl.Customer;
import com.example.possystemspring.entity.impl.Item;
import com.example.possystemspring.entity.impl.Order;
import com.example.possystemspring.entity.impl.OrderDetail;
import com.example.possystemspring.exception.DataPersistException;
import com.example.possystemspring.service.OrderService;
import com.example.possystemspring.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ItemDao itemDao;
    @Override
    public void save(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(AppUtil.generateOrderId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setTotal(orderDTO.getTotal());
        order.setTxtCash(orderDTO.getTxtCash());
        order.setTxtDiscount(orderDTO.getTxtDiscount());


        Customer customer;
        if (customerDao.existsById(orderDTO.getCusIdOption())){
            customer = customerDao.getReferenceById(orderDTO.getCusIdOption());
        }else {
            throw new DataPersistException("Customer not found");
        }
        order.setCustomer(customer);

        ArrayList<OrderDetail> orderList = new ArrayList<>();

        for (ItemDTO itemDTO: orderDTO.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(AppUtil.generateOrderDetailId());
            orderDetail.setOrder(order);

            Item item = itemDao.findById(itemDTO.getId()).orElseThrow(() -> new RuntimeException("Item not found"));

            orderDetail.setItem(item);
            orderDetail.setOrderQty(itemDTO.getQty());

            orderList.add(orderDetail);
        }

        order.setOrderDetails(orderList);

        Order saveorder = orderDao.save(order);
        if (saveorder == null) {
            throw new DataPersistException("Error saving order");
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(String id, OrderDTO dto) {

    }

    @Override
    public OrderDTO get(String id) {
        return null;
    }

    @Override
    public List<OrderDTO> getAll() {
        return null;
    }
}
