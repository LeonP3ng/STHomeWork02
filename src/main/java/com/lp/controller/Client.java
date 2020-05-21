package com.lp.controller;

import com.lp.entity.*;
import com.lp.service.Discount;
import com.lp.service.serviceImple.StudentDiscount;
import com.lp.service.serviceImple.VIPDiscount;
import com.lp.service.serviceImple.WorkerDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class Client {

    //通过变量名的方式注入
    @Autowired
    Discount studentDiscount;

    @Autowired
    Discount VIPDiscount;

    @Autowired
    Discount workerDiscount;

    @Autowired
    MarketAccounts marketAccounts;

    private HashMap<String,ArrayList<String>> clients = new HashMap<>();

    @ResponseBody
    @RequestMapping("/getPrice")
    public Map<String,Object> calculatePrice(String flag, double singlePrice, int num, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        Discount discount = null;
        if(singlePrice < 0){
            System.out.println("请正确输入金额！");
            result.put("SUCCESS",0); //失败 返回0
            return result;
        }

        String IPAddress = request.getRemoteAddr();
        ArrayList<String> cart = clients.get(IPAddress);
        if(cart == null){
            cart = new ArrayList<String>();
        }
        double originalPrice = singlePrice * num;
        switch (flag){
            case "八折优惠":
                discount = workerDiscount;
                break;
            case "满100减10":
                discount = studentDiscount;
                break;
            case "六折优惠":
                discount = VIPDiscount;
                break;
            default:
                break;
        }
        marketAccounts.setPrice(originalPrice);
        marketAccounts.setDiscount(discount);
        String words ="单价："+singlePrice+" 数量:"+num+" "+flag+" 合计:"+marketAccounts.getPrice();

        if("六折优惠".equals(flag)){
            VIPDiscount vipDis = (VIPDiscount) discount;
            words += " 会员积分：" + vipDis.getPoints();
        }


        cart.add(words);
        clients.put(IPAddress,cart);

        result.put("cart",cart);
        result.put("SUCCESS",1); //成功返回1
        return result;

    }

    //重置
    @RequestMapping("/reset")
    @ResponseBody
    public Map<String,Object> resetCart(HttpServletRequest request){
        Map<String,Object> result =new HashMap<>();
        String IPAddress = request.getRemoteAddr();
        clients.get(IPAddress).clear();
        result.put("SUCCESS",1);
        return result;
    }

}
