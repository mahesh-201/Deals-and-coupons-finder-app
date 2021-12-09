package com.CatalogService.CatalogService.ServiceTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.CatalogService.CatalogService.model.Coupon;
import com.CatalogService.CatalogService.service.CouponService;



@SpringBootTest
public class CouponServiceTest {

	@Autowired
	CouponService couponService;
	
   @Test
	public void saveTest() {
		Coupon coupon = new Coupon("2","food",12,"12%","EdnaBakery");
		Coupon coupon1 = couponService.save(coupon);
	    assertEquals(coupon.getCouponId(),coupon1.getCouponId());	
		assertEquals(coupon.getCategory(),coupon1.getCategory());
		assertEquals(coupon.getCount(),coupon1.getCount());	
		assertEquals(coupon.getOffer(),coupon1.getOffer());
		assertEquals(coupon.getCompanyName(),coupon1.getCompanyName());		
		
		 String pro2 = couponService.deleteById("2");
	        assertEquals("Id 2 deleted!", pro2); 
	}
	
	 @Test
	 public void findByIdTest() {
		 Optional<Coupon> result = couponService.findByCouponId("1");
		 assertEquals("1",result.get().getCouponId());
		 assertEquals("food",result.get().getCategory());
		 assertEquals(12,result.get().getCount());
		 assertEquals("12%",result.get().getOffer());
		 assertEquals("PizzaHut",result.get().getCompanyName());	
	 } 

	 @Test
	 public void deleteByIdTest() {
	  String result= couponService.deleteById("8");
	  assertEquals("Id 8 deleted!",result);
	  
	 Coupon coupon = new Coupon("8","beverage",170,"95%","Dominos");
      couponService.save(coupon); 
	  
	  String result1= couponService.deleteById("98");
	  assertEquals("Id 98 is not found",result1);  
	  }
}
