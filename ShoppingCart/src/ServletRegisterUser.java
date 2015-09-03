import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Userprofile;

/**
 * Servlet implementation class ServletRegisterReviewer
 */
@WebServlet("/RegisterUser")
public class ServletRegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String error = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init()
    {
    	error = "";
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
	
		//get parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		String pwd = request.getParameter("pwd");
		Userprofile user = new Userprofile();
		if(DBUtil.emailExists(email)){
			error = "Email Address is not available";
			request.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/RegisterUser.jsp").forward(request, response);
		}
		else
		{
			//validate inputs
			if(!Validator.validateNullEmptyString(name) || !Validator.validateNullEmptyString(email) 
					|| !Validator.validateNullEmptyString(zipcode) || !Validator.validateNullEmptyString(pwd))
			{
				response.sendError(400,"Invalid Inputs!");
			}
			else
			{
				System.out.println(name + email + zipcode);
				user.setEmail(email);
				user.setUserName(name);
				user.setZipcode(zipcode);
				user.setPwd(pwd);
				DBUtil.insert(user);
				//get the reviewer id
				HttpSession session = request.getSession();
				Userprofile userData = DBUtil.getUser(user.getEmail());
				System.out.println("Registered user" + userData.getUserId());
				session.setAttribute("user", userData);
				DBUtil.updateUserCart(userData);
				getServletContext().getRequestDispatcher("/Checkout").forward(request, response);
			}
		}

		
		
	}

}