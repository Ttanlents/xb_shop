package com.yjf;

import com.yjf.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class XbShopApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        List<Order> list=new ArrayList<>();
        Order order = new Order();
        order.setId(1);
        order.setTotal(30d);
        order.setUserId(1);
        order.setState(1);
        order.setOrderTime(new Date());
        list.add(order);
        Order order1 = new Order();
        order1.setId(2);
        order1.setTotal(60d);
        order1.setUserId(2);
        order1.setState(2);
        order1.setOrderTime(new Date());
        list.add(order1);

        redisTemplate.opsForValue().set("order_id:"+":"+"15625511657",list);

        List<Order> orders=(List<Order>) redisTemplate.opsForValue().get("order_id:"+":"+"15625511657");
        System.out.println(orders);
    }

}
