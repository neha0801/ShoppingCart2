

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Userprofile;
import customTools.DBUtil;

/**
 * Servlet implementation class ServletEditCart
 */
@WebServlet("/EditCart")
public class ServletEditCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		int prodId=0;
		String idStr= request.getParameter("prodId");
		if(idStr!=null){
			prodId = Integer.parseInt(idStr);
		}
		HttpSession session = request.getSession();
		Userprofile user = (Userprofile) session.getAttribute("user");
		String emptyCart = request.getParameter("empty");
		if(emptyCart !=null){
			if(emptyCart.equalsIgnoreCase("y"))
				DBUtil.deleteAll(user);
		}else
			DBUtil.delete(prodId);
		getServletContext().getRequestDispatcher("/Cart").forward(request, response);
	}

}
