package com.drivetuningsh.repository.order;

import com.drivetuningsh.entity.order.Order;
import com.drivetuningsh.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUser(User user);
}
