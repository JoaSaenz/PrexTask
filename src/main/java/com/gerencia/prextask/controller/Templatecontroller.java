package com.gerencia.prextask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Templatecontroller {

    @GetMapping("login")
    public String getLogin(){
        return "login";
    }
    
    @GetMapping("clientes")
    public String getClientes(){
        return "clientes";
    }

}