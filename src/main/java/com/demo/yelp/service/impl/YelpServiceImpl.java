/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.yelp.service.impl;

import com.demo.yelp.domain.UserReviews;
import com.demo.yelp.service.YelpService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author BlackCrossMSI64
 */
@Service
public class YelpServiceImpl implements YelpService{  
          
    @Override
    public List<UserReviews> getAllReviews(){       
        List<UserReviews> reviewList = new ArrayList<>();
        
        WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(10 * 1024 * 1024)) // 10 MB
                        .build())
                .build();

        String result = webClient
                .get()
                .uri("https://www.yelp.com/biz/native-burger-san-francisco")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        Document doc = Jsoup.parse(result);
        Elements divs = doc.select("div.review__373c0__13kpL");
        
        for(Element e : divs) {
            UserReviews userReviews = new UserReviews();
            
//            System.out.println("--" + e.toString());
            // image link
            System.out.println("--" + e.select("div.css-0 a img").attr("src"));
            // username
            System.out.println("--" + e.select("span.fs-block").text());
            // userAddress
            System.out.println("--" + e.select("span.css-n6i4z7").text());
            // userReview
            System.out.println("--" + e.select("p.comment__373c0__1M-px").text()); 
            // stars
            System.out.println("--" + e.select("span.display--inline__373c0__2SfH_ div").attr("aria-label")); 
            // reviewDate
            System.out.println("--" + e.select("span.css-e81eai").text());
            
            
            String star = Objects.toString(e.select("span.display--inline__373c0__2SfH_ div").attr("aria-label").charAt(0), "0");
            
            userReviews.setUserName(e.select("span.fs-block").text());
            userReviews.setUserAddress(e.select("span.css-n6i4z7").text());
            userReviews.setUserReview(e.select("p.comment__373c0__1M-px").text());
            userReviews.setImageLink(e.select("div.css-0 a img").attr("src"));
            userReviews.setStars(Integer.parseInt(star));
            userReviews.setReviewDate(e.select("span.css-e81eai").text());
            reviewList.add(userReviews);
        }
         
        return reviewList;
    }
    
    @Override
    public List<UserReviews> getSelectedReviews(HashMap map){
//        System.out.println("--" + map.toString());
        List<UserReviews> reviewList = this.getAllReviews(); 
        
        // list of filters
        String userName = Objects.toString(map.get("user_name").toString(), "");
        String userAddress = Objects.toString(map.get("user_address").toString(), "");
        int stars = Integer.parseInt(Objects.toString(map.get("stars").toString(), "0"));
        
        List<UserReviews> result = new ArrayList<>();
//        System.out.println("--" + userName + " " + userAddress + " " + stars);
        for (UserReviews ur : reviewList) { 
            if(!userName.isEmpty() && !userAddress.isEmpty() && stars > 0) {
                if(ur.getUserName().equalsIgnoreCase(userName) 
                    && ur.getUserAddress().equalsIgnoreCase(userAddress)
                    && ur.getStars() == stars) {
                    result.add(ur);
                } 
            } else if( (!userName.isEmpty() && !userAddress.isEmpty()) 
                    || (!userAddress.isEmpty() && stars > 0)
                    || (!userName.isEmpty() && stars > 0)){
                if((ur.getUserName().equalsIgnoreCase(userName) 
                    && ur.getUserAddress().equalsIgnoreCase(userAddress))
                    || (ur.getUserName().equalsIgnoreCase(userName) 
                    && ur.getStars() == stars)
                    || (ur.getUserAddress().equalsIgnoreCase(userAddress) 
                    && ur.getStars() == stars)) {
                    result.add(ur);
                }
            } else {
                if( (!userName.isEmpty() && ur.getUserName().equalsIgnoreCase(userName))
                    || (!userAddress.isEmpty() && ur.getUserAddress().equalsIgnoreCase(userAddress))
                    || (stars > 0 && ur.getStars() == stars)
                    ) {
                    result.add(ur);
                }
            }
        }
        
        return result;
    }
}
