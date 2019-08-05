package com.order.TDD;

import org.junit.Assert;
import org.junit.Test;

import com.order.main.client.BricksOrderClient;

public class BricksOrderTDD {
	
	private BricksOrderClient boc = new BricksOrderClient();
	
	/*
	 * Verify the Order creation with valid quantity
	 */
	@Test
	public void CreateOrderTest()
	{
		int expectedResult = 5000;
		boolean ActuaResult = boc.createOrder(expectedResult);
		Assert.assertEquals(true , ActuaResult);	
	}
	
	/*
	 * Verify the Order creation with invalid quantity
	 */
	@Test
	public void CreateOrderWithInvalidQtyTest()
	{
		int expectedResult = -5000;
		boolean ActuaResult = boc.createOrder(expectedResult);
		Assert.assertEquals(false , ActuaResult);	
	}
	
	/*
	 * Verify Get Order details with valid Order ref number input
	 */
	@Test
	public void getOrderTest()
	{
		boolean ActuaResult = boc.getOrder("1");
		Assert.assertEquals(true , ActuaResult);	
	}
	
	/*
	 * Verify Get Order details with invalid Order ref number input
	 */
	@Test
	public void getOrderWithInvalidRefNoTest()
	{
		boolean ActuaResult = boc.getOrder("2xy");
		Assert.assertEquals(false , ActuaResult);	
	}
	
	/*
	 * Verify Update  Order with valid input
	 */
	@Test
	public void updateOrderTest()
	{
		boolean ActuaResult = boc.updateOrder("1", 9000);
		Assert.assertEquals(true , ActuaResult);	
	}
	
	/*
	 * Verify Update of Order with  invalid input
	 */
	@Test
	public void updateOrderInvalidInputTest()
	{
		boolean ActuaResult = boc.updateOrder("2xy", 9000);
		Assert.assertEquals(false , ActuaResult);	
	}
	
	/*
	 * Verify Dispatch Order functionality with valid input
	 */
	@Test
	public void fulFillOrderTest()
	{
		boolean ActuaResult = boc.fulFilOrder("1");
		Assert.assertEquals(true , ActuaResult);	
	}
	
	/*
	 * Verify Dispatch Order functionality with Invalid input
	 */
	@Test
	public void fulFillOrderInvalidInputTest()
	{
		boolean ActuaResult = boc.fulFilOrder("2xy");
		Assert.assertEquals(false , ActuaResult);	
	}
	
	/*
	 * Verify update order functionality for dispatched order
	 */
	@Test
	public void updateOrderForDispatchedOrderTest()
	{
		boolean ActuaResult = boc.updateOrder("1", 9000);
		Assert.assertEquals(false , ActuaResult);	
	}
	
	@Test
	public void getAllOrdersTest()
	{
		boolean ActuaResult = boc.getAllOrders();
		Assert.assertEquals(true , ActuaResult);
	}
}
