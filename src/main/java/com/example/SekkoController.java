package com.example;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class SekkoController extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }
    
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String showHome(SekkoRequest sekkoRequest) {
        return "home";
    }
	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public String checkSekkoRequestInfo(@Valid SekkoRequest sekkoRequest, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "home";
        }
        
        model.addAttribute("Name", sekkoRequest.getName());
        model.addAttribute("Request", sekkoRequest.getRequest());
        return "results";
    }

}