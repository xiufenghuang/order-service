package top.hxf.orderservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.hxf.orderservice.entity.OrderStatus;

/**
 * 功能：
 * 作者：程序员青戈
 * 日期：2024/9/4 10:22
 */
@RestController
public class OrderController{
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/order")public String createOrder(@RequestParam String username,
                                                   @RequestParam String orderId,
                                                   @RequestParam String goodId,
                                                   @RequestParam OrderStatus status
    ){String userServiceUrl="http://localhost:8081/user?username="+username;
        String userInfo=restTemplate.getForObject(userServiceUrl,String.class);
        String goodServiceUrl="http://localhost:8086/good?goodId="+goodId;
        String goodInfo=restTemplate.getForObject(goodServiceUrl,String.class);
        String orderStatusMessage = switch (status) {case PENDING -> "您的订单正在等待处理。";
            case PROCESSING -> "您的订单正在处理中。";case COMPLETED -> "您的订单已完成。";case CANCELLED -> "您的订单已取消。";};


        return"订单 ID: "+orderId+"，下单人： "+userInfo+"，商品信息： "+goodInfo+"，订单状态："+status+". "+orderStatusMessage;

        }
}
