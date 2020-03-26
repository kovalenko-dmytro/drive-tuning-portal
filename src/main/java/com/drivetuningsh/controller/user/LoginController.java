package com.drivetuningsh.controller.user;


import com.drivetuningsh.dto.UserRequestDto;
import com.drivetuningsh.entity.user.User;
import com.drivetuningsh.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping(value="/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pages/login");
        return modelAndView;
    }

    @GetMapping(value="/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userRequestDto", new UserRequestDto());
        modelAndView.setViewName("/pages/registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration(@Valid UserRequestDto userRequestDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByEmail(userRequestDto.getEmail());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/pages/registration");
        } else {
            userService.save(userRequestDto);
            modelAndView.setViewName("redirect:/index");
        }
        return modelAndView;
    }
}
