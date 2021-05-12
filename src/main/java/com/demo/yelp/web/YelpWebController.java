/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.yelp.web;

import com.demo.yelp.domain.UserReviews;
import com.demo.yelp.service.YelpService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BlackCrossMSI64
 */
@Validated
@RestController
@RequestMapping("/yelp")
public class YelpWebController {
    @Autowired
    private YelpService yelpService; 
    
    @GetMapping("/getAllUserReviews")
    public ResponseEntity<List<UserReviews>> getAllUserReviews() {
        return ResponseEntity.ok().body(yelpService.getAllReviews());
    }
    
    @PostMapping("/getSelectedReviews")
    public ResponseEntity<List<UserReviews>> getSelectedReviews(@RequestBody HashMap<String, Object> input) {
        return ResponseEntity.ok().body(yelpService.getSelectedReviews(input));
    }
}
