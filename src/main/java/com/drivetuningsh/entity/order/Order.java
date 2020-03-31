package com.drivetuningsh.entity.order;

import com.drivetuningsh.dto.OrderRequestDTO;
import com.drivetuningsh.entity.ToDtoConverter;
import com.drivetuningsh.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order implements ToDtoConverter<OrderRequestDTO> {

    @Id
    @Column(name = "order_id")
    private long orderId;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "order_status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public OrderRequestDTO toDto() {
        //stub
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setOrderId(orderId);
        return orderRequestDTO;
    }
}
