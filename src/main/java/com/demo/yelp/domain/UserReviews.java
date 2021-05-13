/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.yelp.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author BlackCrossMSI64
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReviews implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String userName;
    private String userAddress;
    private String userReview;
    private int stars;
    private String imageLink;   
    private String reviewDate;
    
}
