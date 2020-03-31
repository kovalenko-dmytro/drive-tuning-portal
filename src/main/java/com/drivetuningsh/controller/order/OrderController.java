package com.drivetuningsh.controller.order;

import com.drivetuningsh.dto.OrderRequestDTO;
import com.drivetuningsh.entity.user.User;
import com.drivetuningsh.exception.ApplicationException;
import com.drivetuningsh.service.order.OrderService;
import com.drivetuningsh.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping(value = "/orders")
    public ModelAndView findOrders(Principal principal) {
        ModelAndView view = new ModelAndView();
        User user = userService.findByEmail(principal.getName());
        view.addObject("orders", orderService.findUserOrders(user));
        view.setViewName("/pages/order/orders");

        return view;
    }

    @GetMapping(value="/orders/{orderId}")
    public ModelAndView findOrderById(@PathVariable("orderId") long orderId, Locale locale) {
        ModelAndView view = new ModelAndView();
        try {
            view.addObject("order", orderService.findUserOrderById(orderId, locale));
        } catch (ApplicationException e) {
            view.addObject("error", e.getMessage());
        }
        view.setViewName("/pages/order/order");
        return view;
    }

    @GetMapping(value = "/orders/create")
    public ModelAndView createOrder() {
        ModelAndView view = new ModelAndView();
        view.addObject("data", new OrderRequestDTO());
        view.setViewName("/pages/order/order-create");
        return view;
    }

    @PostMapping(value = "/orders")
    public ModelAndView createOrder(@Valid @ModelAttribute("data") OrderRequestDTO data, BindingResult result, Locale locale) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.addObject("data", data);
            view.addAllObjects(result.getModel());
            view.setViewName("/pages/order/order-create");
            return view;
        }
        try {
            orderService.save(data, locale);
        } catch (ApplicationException e) {
            view.addObject("error", e.getMessage());
        }
        view.setViewName("redirect:/orders");
        return view;
    }

    @GetMapping(value = "/orders/{orderId}/update")
    public ModelAndView updateOrder(@PathVariable("orderId") long orderId, Locale locale) {
        ModelAndView view = new ModelAndView();
        try {
            OrderRequestDTO data = orderService.findUserOrderById(orderId, locale).toDto();
            view.addObject("data", data);
            view.setViewName("/pages/order/order-update");
        } catch (ApplicationException e) {
            view.addObject("error", e.getMessage());
            view.setViewName("redirect:/orders");
        }
        return view;
    }

    @PostMapping(value = "/orders/{orderId}/edit")
    public ModelAndView updateOrder(@PathVariable("orderId") long orderId,
                                    @Valid @ModelAttribute("data") OrderRequestDTO data,
                                    BindingResult result,
                                    Locale locale) {

        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.addObject("data", data);
            view.addAllObjects(result.getModel());
            view.setViewName("/pages/order/order-update");
            return view;
        }
        try {
            orderService.update(data, locale);
        } catch (ApplicationException e) {
            view.addObject("error", e.getMessage());
        }
        view.setViewName("redirect:/orders");
        return view;
    }

    @PostMapping(value = "/orders/{orderId}/delete")
    public ModelAndView deleteOrder(@PathVariable("orderId") long orderId) {
        ModelAndView view = new ModelAndView();
        orderService.delete(orderId);
        view.setViewName("redirect:/orders");
        return view;
    }
}
