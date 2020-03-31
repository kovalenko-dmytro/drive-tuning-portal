package com.drivetuningsh.service.order.impl;

import com.drivetuningsh.dto.OrderRequestDTO;
import com.drivetuningsh.entity.order.Order;
import com.drivetuningsh.entity.order.OrderStatus;
import com.drivetuningsh.entity.user.User;
import com.drivetuningsh.exception.ApplicationException;
import com.drivetuningsh.repository.order.OrderRepository;
import com.drivetuningsh.service.order.OrderService;
import com.drivetuningsh.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MessageSource messageSource;
    private final UserService userService;

    @Override
    public List<Order> findUserOrders(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Order findUserOrderById(long orderId, Locale locale) throws ApplicationException {
        return orderRepository
            .findById(orderId)
            .orElseThrow(() ->
                new ApplicationException(messageSource.getMessage("order.not.found", null, locale)));
    }

    @Override
    public void save(OrderRequestDTO orderRequestDto, Locale locale) throws ApplicationException {
        User user = Optional
            .ofNullable(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()))
            .orElseThrow(() ->
                new ApplicationException(messageSource.getMessage("error.user.not.exist", null, locale)));
        Order order = new Order();
        LocalDateTime now = LocalDateTime.now();
        order.setOrderId(now.toInstant(ZoneOffset.UTC).toEpochMilli());
        order.setCreated(now);
        order.setOrderStatus(OrderStatus.SENT);
        order.setUser(user);
        orderRepository.save(order);
    }

    @Override
    public void update(OrderRequestDTO orderRequestDto, Locale locale) throws ApplicationException {
        Order order = findUserOrderById(orderRequestDto.getOrderId(), locale);
        //stub
        order.setOrderStatus(OrderStatus.VALIDATED);
        orderRepository.save(order);
    }

    @Override
    public void delete(long orderId) {
        orderRepository.deleteById(orderId);
    }
}
