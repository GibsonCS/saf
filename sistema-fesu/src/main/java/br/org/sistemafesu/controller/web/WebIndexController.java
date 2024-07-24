package br.org.sistemafesu.controller.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.service.UserService;

@Controller
@RequestMapping("/")
public class WebIndexController {
    @Autowired
    UserService userService;

    @GetMapping()
    public String index(Authentication auth, Model model) {
        String name = auth.getName();

        model.addAttribute("name",name);

        return "index";
    }
}
