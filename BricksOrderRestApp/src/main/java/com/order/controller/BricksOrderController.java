package com.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.order.model.BricksOrder;

/**
 * Handles requests for the Order service.
 */
@Controller
public class BricksOrderController {
	
	//Map to store Bricks Orders, ideally we should use database
	Map<String, BricksOrder> orderData = new HashMap<String, BricksOrder>();
	int i = 0;
	
	@RequestMapping(value = BricksOrderURIConstants.CREATE_ORD, method = RequestMethod.POST)
	public @ResponseBody BricksOrder createOrder(@RequestBody BricksOrder ord) {
		i = i+1;
		ord.setCreatedDate(new Date());
		ord.setOrderRefNo(String.valueOf(i));
		orderData.put(ord.getOrderRefNo(), ord);
		return ord;
	}
	
	@RequestMapping(value = BricksOrderURIConstants.GET_ORD, method = RequestMethod.GET)
	public @ResponseBody BricksOrder getOrder(@PathVariable("id") String orderId) {
		return orderData.get(orderId);
	}
	
	@RequestMapping(value = BricksOrderURIConstants.GET_ALL_ORD, method = RequestMethod.GET)
	public @ResponseBody List<BricksOrder> getAllOrders() {
		List<BricksOrder> orders = new ArrayList<BricksOrder>();
		Set<String> orderIdKeys = orderData.keySet();
		for(String i : orderIdKeys){
			orders.add(orderData.get(i));
		}
		return orders;
	}
	
	@RequestMapping(value = BricksOrderURIConstants.UPDATE_ORD, method = RequestMethod.POST)
	public @ResponseBody BricksOrder updateOrder(@RequestBody BricksOrder ord) {
		ord.setCreatedDate(new Date());
		orderData.put(ord.getOrderRefNo(), ord);
		return ord;
	}
		
/*	@RequestMapping(value = BricksOrderURIConstants.FULFIL_ORD, method = RequestMethod.PUT)
	public @ResponseBody BricksOrder deleteOrder(@PathVariable("id") String ordId) {
		System.out.println("Start deleteEmployee.");
		BricksOrder ord = orderData.get(ordId);
		ord.setStatus("Dispatched");
		return ord;
	}*/
}
