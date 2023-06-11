package com.blog.blog.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.boot.SpringApplication;


import org.springframework.web.bind.annotation.*;


@RestController
public class LogController {

static Logger logger= LogManager.getLogger(LogController.class);

//    public static void main(String[] args) {
//        SpringApplication.run(LogController.class,args);
//
//        System.out.println("Start Project......");
//
//        logger.debug("this is debug msg");
//
//    }


   @GetMapping("/home")
    String ShowHome()
    {
        System.out.println("Starting projects..................");
        logger.info("hit the url /home");
        logger.debug("hit the url 2nd time /home");
        return "check on console";
    }


}
