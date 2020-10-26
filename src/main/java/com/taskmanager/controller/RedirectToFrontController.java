package com.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectToFrontController {
    @GetMapping
    public String redirectToFront() {
        return "redirect:https://kos-todo.herokuapp.com/";
    }
}
