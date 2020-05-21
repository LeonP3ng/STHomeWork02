package com.lp.service.serviceImple;

import com.lp.service.Discount;
import org.springframework.stereotype.Service;

@Service
public class WorkerDiscount implements Discount {
    @Override
    public double calculateCost(double originalPrice) {
        return originalPrice*0.8;
    }
}
