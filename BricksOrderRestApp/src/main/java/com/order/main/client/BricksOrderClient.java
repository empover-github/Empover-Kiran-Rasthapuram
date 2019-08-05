package com.order.main.client;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.order.controller.BricksOrderURIConstants;
import com.order.model.BricksOrder;

public class BricksOrderClient {

	public static final String SERVER_URI = "http://localhost:8080/spring";
	
	public static void main(String args[]){
		BricksOrderClient boc = new BricksOrderClient();
		System.out.println("*****Create Order****");
		boc.createOrder(5000);
		boc.createOrder(8000);
		System.out.println("*****Get Order******");
		boc.getOrder("2");
		System.out.println("*****Update Order******");
		boc.updateOrder("2", 6000);
		System.out.println("*****All Orders******");
		boc.getAllOrders();
		System.out.println("*****FulFil Orders******");
		boc.fulFilOrder("2");
	}
	
	/*
	 * Create Order
	 */
	public boolean createOrder(int qty) {
		if(qty > 0) {
			RestTemplate restTemplate = new RestTemplate();
			BricksOrder ord = new BricksOrder();
			ord.setOrderQty(qty);
			ord.setStatus("Pending");
			BricksOrder response = restTemplate.postForObject(SERVER_URI+BricksOrderURIConstants.CREATE_ORD, ord, BricksOrder.class);
			printOrderData(response);
			return true;
		}else {
			return false; 
		}

	}

	/*
	 * Get 0rder by giving order ref no
	 */
	public boolean getOrder(String ordRefNo) {
		RestTemplate restTemplate = new RestTemplate();
		BricksOrder ord = restTemplate.getForObject(SERVER_URI+"/rest/ord/"+ordRefNo, BricksOrder.class);
		if(ord != null) {
			printOrderData(ord);
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * Update order by giving order ref no
	 */
	public  boolean updateOrder(String ordRefNo, int qty) {
		System.out.println("*****updateOrder ****"+ordRefNo);
		RestTemplate restTemplate = new RestTemplate();
		BricksOrder ord = restTemplate.getForObject(SERVER_URI+"/rest/ord/"+ordRefNo, BricksOrder.class);
		if(!ord.getOrderRefNo().equals(ordRefNo)) {
			return false;
		}else if(ord.getStatus().equals("Dispatched")){
			return false;
		}else {
			ord.setOrderRefNo(ordRefNo);ord.setOrderQty(qty);
			BricksOrder response = restTemplate.postForObject(SERVER_URI+BricksOrderURIConstants.UPDATE_ORD, ord, BricksOrder.class);
			printOrderData(response);
			return true;
		}
	}

	/*
	 * Get All orders 
	 */
	public boolean  getAllOrders() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<BricksOrder> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> ords = restTemplate.getForObject(SERVER_URI+BricksOrderURIConstants.GET_ALL_ORD, List.class);
		for(LinkedHashMap map : ords){
			System.out.println("orderRefNo="+map.get("orderRefNo")+",orderQty="+map.get("orderQty")+",CreatedDate="+map.get("createdDate"));;
		}
		if(ords.size() > 0) {
			return true;
		}else {
			return false;
		}
	}

	/*
	 * FulFil order by giving order
	 */
	public boolean fulFilOrder(String ordRefNo) {
		RestTemplate restTemplate = new RestTemplate();
		BricksOrder ord = restTemplate.getForObject(SERVER_URI+"/rest/ord/"+ordRefNo, BricksOrder.class);
		if(ord != null) {
			ord.setStatus("Dispatched");
			BricksOrder response = restTemplate.postForObject(SERVER_URI+BricksOrderURIConstants.UPDATE_ORD, ord, BricksOrder.class);
			printOrderData(response);
			return true;
		}else {
			return false;
		}
	}
	
	public void printOrderData(BricksOrder ord){
		System.out.println("Order Ref no ="+ord.getOrderRefNo()+",Order Qty="+ord.getOrderQty()+",Status="+ord.getStatus()+",Created Date="+ord.getCreatedDate());
	}
}
