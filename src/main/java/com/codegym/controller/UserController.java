package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String showForm(Model model) {
        model.addAttribute( "user", new User() );
        return "create";
    }

    @PostMapping("/create-user")
    public ModelAndView checkValidation(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        new User().validate( user, bindingResult );
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView( "create" );
            return modelAndView;
        }
        userService.save( user );
        ModelAndView modelAndView = new ModelAndView( "create" );
        modelAndView.addObject( "user", new User() );
        modelAndView.addObject( "message", "New user created successfully" );
        return modelAndView;

    }
}
