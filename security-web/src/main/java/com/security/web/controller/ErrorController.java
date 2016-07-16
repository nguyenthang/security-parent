package com.security.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by thangnguyen-imac on 7/16/16.
 */
@Controller
public class ErrorController {

    private Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String Error403Page(){
        logger.info("an error occurred during attempt logging action");
        return "error/403";
    }

}
