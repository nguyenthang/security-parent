package com.security.web.controller;

import com.security.service.auth.PrincipalHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by thangnguyen-imac on 7/16/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String UserPage(ModelMap modelMap){
        modelMap.addAttribute("user", PrincipalHandler.getPrincipal());

        return "user/index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String AdminPage(ModelMap modelMap){
        modelMap.addAttribute("admin", PrincipalHandler.getPrincipal());

        return "admin/index";
    }

    @RequestMapping(value = "/dba", method = RequestMethod.GET)
    public String DbaPage(ModelMap modelMap){
        modelMap.addAttribute("dba", PrincipalHandler.getPrincipal());

        return "dba/index";
    }
}