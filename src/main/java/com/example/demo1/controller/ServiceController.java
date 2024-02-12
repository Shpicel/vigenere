package com.example.demo1.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ServiceController implements ErrorController {

    @GetMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {
        String msg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString();
        if(msg == null || msg.equals("")){
            model.addAttribute("errorMsg", "На этой странице что-то пошло не так!");
        }else {
            model.addAttribute("errorMsg", msg);
        }
        model.addAttribute("errorCode", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return "error";
    }
}
