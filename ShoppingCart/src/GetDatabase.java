import static org.junit.Assert.*;

import java.util.List;

import model.Cart;
import model.Product;
import model.Userprofile;

import org.junit.Test;

import customTools.DBUtil;


public class GetDatabase {

	@Test
	public void testGetAllProducts() {
		System.out.println("Test if product has 5 items");
		List<Product> testList = DBUtil.getAllProducts();
		assertTrue(testList.size()==5);
	}

	@Test
	public void testGetSelectedProducts() {
		System.out.println("Test if specified product exists");
		Product testProduct = DBUtil.getSelectedProducts(5);
		System.out.println(testProduct.getProductid());
		assertTrue(testProduct!=null);
	}

	@Test
	public void testGetCart() {
		System.out.println("Test if anything exists in cart");
		Userprofile user = DBUtil.getUser("neha@gmail.com");
		List<Cart> testCartList = DBUtil.getCart(user);
		assertTrue(!testCartList.isEmpty());
	}
}
