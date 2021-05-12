/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.yelp.service;

import com.demo.yelp.domain.UserReviews;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author BlackCrossMSI64
 */
public interface YelpService {
    List<UserReviews> getAllReviews();
    List<UserReviews> getSelectedReviews(HashMap map);
}
