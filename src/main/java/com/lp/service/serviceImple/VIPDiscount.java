package com.lp.service.serviceImple;

import com.lp.service.Discount;
import org.springframework.stereotype.Service;

@Service
public class VIPDiscount implements Discount {
    private double points; //积分

    public VIPDiscount() {
        this.points = 0;
    }

    public double getPoints() {
        return points;
    }



    @Override
    public double calculateCost(double originalPrice) {
        originalPrice *= 0.6;
        points += originalPrice;
        return originalPrice;
    }
}
