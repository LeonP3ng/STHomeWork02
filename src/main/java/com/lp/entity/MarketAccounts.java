package com.lp.entity;

import com.lp.service.Discount;
import org.springframework.stereotype.Service;

@Service
public class MarketAccounts {
    private double price;   //商品现价
    private Discount discount;  //折扣方式

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
        if(discount != null){ //防止空指针异常
            this.price = discount.calculateCost(price);
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
