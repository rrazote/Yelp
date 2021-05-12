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
import org.springframework.stereotype.Service;

/**
 *
 * @author BlackCrossMSI64
 */
@Service
public class YelpServiceImpl implements YelpService{  
          
    @Override
    public List<UserReviews> getAllReviews(){       
        List<UserReviews> reviewList = new ArrayList<>();
        UserReviews userReviews = new UserReviews();
        userReviews.setUserName("Megan E.");
        userReviews.setUserAddress("San Diego, CA");
        userReviews.setUserReview("WOW. Native burger was an incredible experience. The burgers were flawwwwless and I'm daydreaming about when we can get them again. A bit of a hole in the wall spot, not a lot of space for dining in. We called ahead and ordered them for takeout. Sad that the corn poppers were removed from the menu  the fries are cooked so perfectly. They're my favorite fries I've found in SF. The garlic ones tasted like real garlic rather than garlic powder and they were delightful. Unfortunately they got hit with a rush and we had to wait around 10 extra minutes for our order to be ready, but it's so worth the wait and we just ran into BevMo while we were waiting :) we love you Native Burger!!");
        userReviews.setStars(5);
        userReviews.setImageLink("https://www.yelp.com/user_details?userid=Q-i77VKkj5e_WHwuoii7IA");
        reviewList.add(userReviews);
        userReviews = new UserReviews();
        userReviews.setUserName("Leah M.");
        userReviews.setUserAddress("San Jose, CA");
        userReviews.setUserReview("Tried this place on a whim last week since we were up in the city and kids were craving burgers for dinner.  Although it was take out only, they did not skimp on the level of service or the quality of the food.  The burgers were good size, the veggies were fresh (even my kids that are usually very picky with veggies LOVED it, they said it's very flavorful), they were not super greasy or soggy.  I had the Loco Moco (because I did not realize they offered GF buns...and the portion was HUGE, and the gravy has mushrooms...soooo good!  The guy that took our order on the phone was super friendly, as well as when we picked up.  He made sure we had everything we needed in the bag (plastic utensils, napkins, ketchup), and even asked if we wanted to have the bottled drinks opened (required bottle opener).");
        userReviews.setStars(5);
        userReviews.setImageLink("https://www.yelp.com/user_details?userid=0h3uy4hY98Jm-u3RWskHwA");
        reviewList.add(userReviews);
        userReviews = new UserReviews();
        userReviews.setUserName("Gauri G.");
        userReviews.setUserAddress("San Francisco, CA");
        userReviews.setUserReview("I'm a broken record with the phrases I write in my reviews, but I'll say it louder for people in the back.\n" +
            "\n" +
            "NATIVE BURGER IS HIGHLY UNDERRATED!!\n" +
            "\n" +
            "I once ordered delivery for myself when my friend was over my house and claimed the burgers were better than anything. She thought I was exaggerating until she ordered it for herself- and then she also became a regular there. The meat is fresh, juicy, and delicious- it leaves your mouth watering as you eat it. The brioche buns that hold the bread are also an excellent choice- I love a good brioche and they are pillowy and soft and absorb the delicious sauce well. Their homemade (I think it's homemade) barbeque sauce is great as well, the taste of the brown sugar is robust and pairs well with the meat that it coats, and I haven't met a barbeque sauce I've liked as much as that one. The fries are yummy, too-  I recommend ordering them with jalapenos for that additional kick. Just a warning- sometimes they're soggy when they get delivered, but that's a small note.\n" +
            "\n" +
            "Bonus: The millionaire's bacon here is great on the burgers. I'm glad they adapted this SF specialty in case it's a weekday and I can't order from Sweet Maple or Kitchen Story.");
        userReviews.setStars(5);
        userReviews.setImageLink("https://www.yelp.com/user_details?userid=JV4sSPlc0OT3bnOgm6hmgg");
        reviewList.add(userReviews);
        userReviews = new UserReviews();
        userReviews.setUserName("Juan Miguel S.");
        userReviews.setUserAddress("Richmond District, San Francisco, CA");
        userReviews.setUserReview("Best burger spot along Geary.\n" +
            "The veggie burger was also delicious.\n" +
            "Simple menu, straightforward.\n" +
            "I've tried everything on the menu and they're all good.\n" +
            "\n" +
            "No bs. No trying to be fancy shenanigans that other places try to do.\n" +
            "\n" +
            "The food speaks for itself, reasonably priced and you will leave feeling happy and full.\n" +
            "\n" +
            "Great place for an evening snack before heading out to a bar, or just nice little\n" +
            "Laid back dinner spot if you want a burger.\n" +
            "\n" +
            "The service is great, and I highly recommend this place to anyone that needs a burger fix.");
        userReviews.setStars(5);
        userReviews.setImageLink("https://www.yelp.com/user_details?userid=Q-i77VKkj5e_WHwuoii7IA");
        reviewList.add(userReviews);
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
