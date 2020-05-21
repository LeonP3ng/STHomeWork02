package com.lp.service.serviceImple;

import com.lp.service.Discount;
import org.springframework.stereotype.Service;

@Service
public class StudentDiscount implements Discount {
    @Override
    public double calculateCost(double originalPrice) {
        double count = originalPrice/100; //满减的次数
        return originalPrice-count*10;
    }
}
