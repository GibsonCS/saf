package br.org.sistemafesu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) auth.getPrincipal();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        String password = ((UserDetails) auth.getPrincipal()).getPassword();

        //model.addAttribute("name", username);
        model.addAttribute("name", user.getUsername());

        return "index";
    }
}
