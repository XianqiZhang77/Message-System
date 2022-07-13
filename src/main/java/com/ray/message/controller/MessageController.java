package com.ray.message.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);
    @GetMapping("/list")
    public void listMessages() {
        logger.info("Listing messages");
    }
}
