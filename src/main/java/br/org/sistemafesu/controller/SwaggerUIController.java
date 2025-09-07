package br.org.sistemafesu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/swagger-ui", "/swagger-ui/"})
public class SwaggerUIController {

    @GetMapping
    public ModelAndView redirectToSwaggerUI() {
        return new ModelAndView("redirect:/swagger-ui/index.html");
    }
}
