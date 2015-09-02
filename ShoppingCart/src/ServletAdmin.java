

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
 * Servlet implementation class ServletAdmin
 */
@WebServlet("/Admin")
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost of admin");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		System.out.println("email = "  + email);
		System.out.println("pwd = "  + pwd);
		if(!Validator.validateEmail(email))
		{
			response.sendError(400,"Invalid email");
		}
		else
		{
			//put button to explore
			if(!checkAdmin(email,pwd)){
				response.sendError(400,"Invalid admin email and password");
			}
			String message = showCart();
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/AdminCart.jsp").forward(request, response);
		}
	}
	
	private boolean checkAdmin(String email,String pwd){
		if(!email.equalsIgnoreCase("Admin@evilcorp.com") || !pwd.equals("admin")){
			return false;
		}
		return true;
	}
	
	private String showCart(){
		List<Cart> cartList = DBUtil.getAdminCart();
		
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
		}else
			tableData="No items are ordered";
		tableData+="<div><a href='ExploreProducts?logout=y' class='btn btn-danger'>Logout</a></div>";
		return tableData;
	}

}
