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
 * Servlet implementation class ServletLogin
 */
@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    String error="";
    @Override
    public void init()
    {
    	error = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("logout").equalsIgnoreCase("yes")){
			HttpSession session = request.getSession();
			session.invalidate();
			getServletContext().getRequestDispatcher("/LoginForm.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String admin = request.getParameter("admin");
		System.out.println(admin);
		if(admin!=null){
			if(admin.equalsIgnoreCase("yes"))
				getServletContext().getRequestDispatcher("/Admin").forward(request, response);
		}
		System.out.println("dopost");
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
			if(!DBUtil.validateUser(email, pwd)){
				response.sendError(400,"User does not exist");
			}
			
			Userprofile user =DBUtil.getUser(email);
			HttpSession session = request.getSession();		
			System.out.println("session " +session.getAttribute("user"));
			if (session.getAttribute("user")==null){
				session.setAttribute("user", user);
			}
			getServletContext().getRequestDispatcher("/Checkout").forward(request, response);
		}
	}

}