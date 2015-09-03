

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Payment;
import model.Userprofile;
import customTools.DBUtil;

/**
 * Servlet implementation class ServletOrderConfirmation
 */
@WebServlet("/OrderConfirmation")
public class ServletOrderConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrderConfirmation() {
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
		HttpSession session = request.getSession();		
		Userprofile user = (Userprofile) session.getAttribute("user");
		String message = "";
		System.out.println("fvdsjgfd");
		if(paymentRecorded(request,response,user))
		{
			Random r = new Random();
			int confirmationNumber = 1+ r.nextInt(1000000);

			message += "<h1> Your Order confirmation number is " + confirmationNumber + "</h1>";
			message += showCart(user);
			DBUtil.updateStatus(1,user);

			
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/OrderConfirmation.jsp").forward(request, response);
	
		} else{
			System.out.println("payment not recoreded");
			request.setAttribute("error", "Invalid card details!!!");
			getServletContext().getRequestDispatcher("/Checkout").forward(request, response);
		}
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
			System.out.println();
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
		tableData+="<div><a href='OrderHistory' class='btn pull-left btn-primary'>Order History</a></div>";
		return tableData;
	}

	private boolean paymentRecorded(HttpServletRequest request,HttpServletResponse response,Userprofile user){
		String cardNoStr = request.getParameter("cardNo");
		String dateStr = request.getParameter("expiryDate");
		String cvvStr = request.getParameter("cvv");
		String shippingAdd = request.getParameter("shipaddress") +", " 
							+ request.getParameter("shipcity") + ", "
							+ request.getParameter("shipstate") + ", "
							+ request.getParameter("shipzipcode");

		String billingAdd = request.getParameter("billaddress") +", " 
							+ request.getParameter("billcity") + ", "
							+ request.getParameter("billstate") + ", "
							+ request.getParameter("billzipcode");
		
		if(!Validator.validateInt(cardNoStr) || !Validator.validateInt(cvvStr)||
				!Validator.validateDateWithFormat(dateStr)){
			System.out.println("error");
			return false;
		}
		Payment pay = new Payment();
		Long card = Long.parseLong(cardNoStr);
		//System.out.println("card number " + card);
		pay.setCardnumber(card);
		pay.setShippingAddress(shippingAdd);
		pay.setBillingAddress(billingAdd);
		pay.setUserprofile(user);
		
		//check existing user
		if(DBUtil.userPayExist(user)){
			DBUtil.updatePayUser(user);
		}else
			DBUtil.insert(pay);
		return true;
		
	}

}
