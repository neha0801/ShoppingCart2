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
	public static void insert(Userprofile u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(u);
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
		String sql = "Update Cart c set c.quantity = c.quantity +" + cart.getQuantity() + 
						" where c.product.productid=" + cart.getProduct().getProductid() + " and c.status=0";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class);
		sql = "Update Cart c set c.totalprice= c.quantity *" + cart.getProduct().getPrice()
				+ " where c.product.productid=" + cart.getProduct().getProductid() + " and c.status=0";
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
	
	public static void updateStatus(int status,Userprofile user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "Update Cart c set c.userprofile = :user, c.status = 1 where c.status = 0";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class).setParameter("user", user);
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
	
	public static void delete(int prodId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "Delete from Cart c  where c.product.productid=" + prodId + " and c.status=0";
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

	public static void deleteAll(Userprofile user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String sql = "Delete from Cart c  where c.userprofile = :user and c.status=0";
		System.out.println(sql);
		Query query = em.createQuery(sql, Cart.class).setParameter("user", user);
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

	public static List<Cart> getCart() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select c from Cart c where c.status=0";
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
	
	public static List<Cart> getOrderedCart(Userprofile user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select c from Cart c where c.userprofile = :user and c.status=1";
		
		TypedQuery<Cart> query = em.createQuery(sql, Cart.class).setParameter("user", user);
		System.out.println(sql);
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
	
	public static Userprofile getUser(String email) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select u from Userprofile u where u.email='" + email + "'";
		System.out.println(sql);
		TypedQuery<Userprofile> query = em.createQuery(sql, Userprofile.class);
		Userprofile user;
		try {
			user = query.getSingleResult();
			if (user == null)
				user = null;
		} finally {
			em.close();
		}
		return user;
	}

	public static boolean itemExists(Cart cObj) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select count(c) from Cart c where c.product.productid="
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
		Query query = em.createQuery(sql, Userprofile.class);
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
	
	public static boolean emailExists(String email){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String sql = "select count(u) from Userprofile u where u.email='"
				+ email +"'";
		System.out.println(sql);
		Query query = em.createQuery(sql, Userprofile.class);
		long user;
		try {
			user = (long) query.getSingleResult();
			if (user<1)
				return false;
		} finally {
			em.close();
		}
		return true;	
	}
	
}
