package com.hiq.hm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alan Tibbetts on 2016-09-12.
 */
@Controller
public class RandomSortController {

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public String homepage() {
        return "forward:index.html";
    }
}
