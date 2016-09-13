package com.hiq.hm.controllers;

import com.hiq.hm.model.RandomSortResult;
import com.hiq.hm.services.RandomSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alan Tibbetts on 2016-09-12.
 */
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ApiController {

    @Autowired
    private RandomSortService randomSortService;

    @RequestMapping(
            path = "/sort",
            method = RequestMethod.POST
    )
    public ResponseEntity sort(@RequestBody String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        randomSortService.sort(numbers);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            path = "/list",
            method = RequestMethod.GET
    )
    public ResponseEntity list(@RequestParam("max") Integer max) {
        int maxEntries = max == null ? 20 : max;
        List<RandomSortResult> randomSortResultList = randomSortService.loadResults(maxEntries);
        return ResponseEntity.ok(randomSortResultList);
    }

}
