

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Userprofile;
import customTools.DBUtil;

/**
 * Servlet implementation class ServletCheckout
 */
@WebServlet("/Checkout")
public class ServletCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCheckout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("checkout do get");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("checkout do post");
		HttpSession session = request.getSession();		
		Userprofile user = (Userprofile) session.getAttribute("user");
		request.setAttribute("user", user.getUserName());
		//String mesg="";
		//mesg=(String) request.getAttribute("error");
		String message="";
		//message = "<h2 style='color:red'>"+mesg+ "</h2>";
		message += showCart(user);
		request.setAttribute("message", message);
	
		getServletContext().getRequestDispatcher("/Checkout.jsp").forward(request, response);
	}
	
	private String showCart(Userprofile user){
		List<Cart> cartList = DBUtil.getCart(user);
		
		String tableData ="";

		if(cartList!=null)
		{
			tableData+="<table class='table table-bordered table-striped'>";
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
				tableData += "</tr>";
			}
			tableData += "</table>";
			tableData+= "<input type='submit' class='btn pull-right btn-warning' value='Place your order'></input>";
		}else
			tableData="Your Cart is empty";

		return tableData;
	}

}
