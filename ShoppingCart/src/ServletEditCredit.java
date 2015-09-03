

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
 * Servlet implementation class ServletEditCredit
 */
@WebServlet("/EditCredit")
public class ServletEditCredit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditCredit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("credit", "yes");
		System.out.println(request.getParameter("add"));
		if(request.getParameter("add")!=null){
			String email = request.getParameter("email");
			Userprofile user = DBUtil.getUser(email);
			user.setCredit(user.getCredit()+25.00);
			DBUtil.updateUsercredit(user);			
			
		}else if (request.getParameter("remove")!=null){
			String email = request.getParameter("email");
			Userprofile user = DBUtil.getUser(email);
			user.setCredit(user.getCredit()-25.00);
			DBUtil.updateUsercredit(user);			
		}
		getServletContext().getRequestDispatcher("/Admin").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
