

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Cart;
import model.Product;

/**
 * Servlet implementation class ServletCart
 */
@WebServlet("/Cart")
public class ServletCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quanStr= request.getParameter("quantity");
		HttpSession session = request.getSession();
		Product prodObj= (Product) session.getAttribute("product");
		String userEmail = (String) session.getAttribute("user");

		Cart cObj = new Cart();
		if(quanStr!=null){
			int quantity = Integer.parseInt(quanStr);
			cObj.setQuantity(quantity);
			Double totalPrice = quantity * prodObj.getPrice().doubleValue();  
			cObj.setTotalprice(totalPrice);
			cObj.setProduct(prodObj);
			//cObj.setUseremail(userEmail);
			cObj.setStatus(0);
			if(DBUtil.itemExists(cObj)){
				DBUtil.update(cObj);
			}else
				DBUtil.insert(cObj);
		}		
		String cartData = showCart(userEmail);
		request.setAttribute("cartData", cartData);
		String buttons= "";
		buttons +=  "<br><a href='OrderConfirmation' class='btn pull-right btn-primary btn-lg'>CheckOut & Order History</a>";
		buttons +=  "<a href='EditCart?empty=y'class='btn pull-left btn-warning btn-lg'>Empty your cart</a>";
		request.setAttribute("buttons", buttons);
		request.setAttribute("user", userEmail);
		getServletContext().getRequestDispatcher("/CartCheckout.jsp").forward(request, response);
	}
	
	private String showCart(String userEmail){
		List<Cart> cartList = DBUtil.getCart(userEmail);
		Double checkoutPrice=0.0;
		String tableData ="";

		if(cartList!=null)
		{
			tableData+="<div><table class='table table-bordered table-striped'>";		
			tableData += "<tr>";
			tableData += "<thead>";
			tableData += "<th>";
			tableData += "";
			tableData += "</th>";
			tableData += "<th>"; 
			tableData += "Product Name";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "Quantity";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "Price";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "TotalPrice";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "";
			tableData += "</th>";
			tableData += "</thead>";
			tableData += "</tr>";
			
			for(Cart c : cartList){
				tableData += "<tr>";
				tableData += "<td>";
				tableData += "<img src='" + c.getProduct().getPicturepath() + "' width ='200' height='200' style=align:center>";
				tableData += "</td>";
				tableData += "<td>";
				tableData += c.getProduct().getProductname();
				tableData += "</td>";
				tableData += "<td>";
				tableData +=  c.getQuantity();
				tableData += "</td>";
				tableData += "<td>";
				tableData +=  "$"+c.getProduct().getPrice();
				tableData += "</td>";
				tableData += "<td>";
				tableData +=  "$"+c.getTotalprice();
				tableData += "</td>";
				tableData += "<td>";
				tableData +=  "<a class='btn btn-danger btn-sm' href='EditCart?prodId=" + c.getProduct().getProductid() + "'>Delete</a>";
				tableData += "</td>";
				tableData += "</tr>";
				
				checkoutPrice+=c.getTotalprice();
			}
			tableData+="</table></div>";
			
			tableData+="<label class='btn pull-right btn-info btn-lg'>Total Price: $" + checkoutPrice + "</label><br><br>";
		}else
			tableData="Your Cart is empty";
		
		return tableData;
	}
	

}
