package customTools;

import java.util.List;

import model.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DBUtil {
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("ShoppingCart");

	public static EntityManagerFactory getEmFactory() {
		return emf;
	}

	public static List<Product> getAllProducts() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select p from Product p";
		System.out.println(sql);
		TypedQuery<Product> query = em.createQuery(sql, Product.class);
		List<Product> productList;
		try {
			productList = query.getResultList();
			if (productList == null || productList.isEmpty())
				productList = null;
		} finally {
			em.close();
		}
		return productList;
	}

	public static Product getSelectedProducts(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select p from Product p where p.productid=" + id;
		System.out.println(sql);
		TypedQuery<Product> query = em.createQuery(sql, Product.class);
		Product product;
		try {
			product = query.getSingleResult();
			if (product == null)
				product = null;
		} finally {
			em.close();
		}
		return product;
	}

	public static void insert(Cart c) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(c);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Cart cart) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "Update Cart c set c.quantity = c.quantity +" + cart.getQuantity() + " where c.useremail = '" 
						/*+ cart.getUseremail()*/ +"' and c.product.productid=" + cart.getProduct().getProductid() + " and c.status=0";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class);
		sql = "Update Cart c set c.totalprice= c.quantity *" + cart.getProduct().getPrice()+ " where c.useremail = '" 
				/*+ cart.getUseremail()*/ +"' and c.product.productid=" + cart.getProduct().getProductid() + " and c.status=0";
		Query query1 = em.createQuery(sql, Cart.class);
		System.out.println(sql);
		trans.begin();
		try {
			query.executeUpdate();
			query1.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void updateStatus(int status, String user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "Update Cart c set c.status = " + status + " where c.useremail = '" 
						+ user +"'";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class);
		System.out.println(sql);
		trans.begin();
		try {
			query.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void delete(String user, int prodId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "Delete from Cart c  where c.useremail = '" 
						+ user +"' and c.product.productid=" + prodId + " and c.status=0";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class);
		trans.begin();
		try {
			query.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void deleteAll(String user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "Delete from Cart c  where c.useremail = '" 
						+ user +"' and c.status=0";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class);
		trans.begin();
		try {
			query.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static List<Cart> getCart(String user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select c from Cart c where c.useremail='" + user + "' and c.status=0";
		System.out.println(sql);
		TypedQuery<Cart> query = em.createQuery(sql, Cart.class);
		List<Cart> cartList;
		try {
			cartList = query.getResultList();
			if (cartList == null || cartList.isEmpty())
				cartList = null;
		} finally {
			em.close();
		}
		return cartList;
	}
	
	public static List<Cart> getOrderedCart(String user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select c from Cart c where c.useremail='" + user + "' and c.status=1";
		System.out.println(sql);
		TypedQuery<Cart> query = em.createQuery(sql, Cart.class);
		List<Cart> cartList;
		try {
			cartList = query.getResultList();
			if (cartList == null || cartList.isEmpty())
				cartList = null;
		} finally {
			em.close();
		}
		return cartList;
	}

	public static boolean itemExists(Cart cObj) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select count(c) from Cart c where c.useremail='"
				+ /*cObj.getUseremail() +*/ "' and c.product.productid="
				+ cObj.getProduct().getProductid() + " and c.status=0";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class);
		long product;
		try {
			product = (long) query.getSingleResult();
			if (product<1)
				return false;
		} finally {
			em.close();
		}
		return true;
	}
	
	public static boolean validateUser(String email, String password){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select count(u) from Userprofile u where u.email='"
				+ email +"' and u.pwd='" + password + "'";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class);
		long product;
		try {
			product = (long) query.getSingleResult();
			if (product<1)
				return false;
		} finally {
			em.close();
		}
		return true;	
	}
}
