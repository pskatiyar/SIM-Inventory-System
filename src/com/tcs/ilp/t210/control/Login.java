package com.tcs.ilp.t210.control;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tcs.ilp.t210.dao.LoginDao;
import com.tcs.ilp.t210.model.UserBean;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		// TODO Auto-generated method stub
		
        
        RequestDispatcher rd = null;
		
		String userId = request.getParameter("un");
		String password = request.getParameter("pw");
		
		HttpSession session = request.getSession();
		
		Integer username = Integer.parseInt(userId);
		LoginDao lDao=new LoginDao();
		if (lDao.checkLogin(username, password)) {
		    //Login Successful
			UserBean ubean=lDao.findUserType(username);
			if(ubean.getRole().equalsIgnoreCase("SERVICE PROVIDER"))
			{
				session.setAttribute("userdetail",ubean);
				rd = getServletContext().getRequestDispatcher("/jsp/ServiceProviderHome.jsp");
				rd.forward(request, response);
			}
			else if(ubean.getRole().equalsIgnoreCase("admin"))
			{
				session.setAttribute("userdetail",ubean);
				rd = getServletContext().getRequestDispatcher("/jsp/AdminHome.jsp");
				rd.forward(request, response);
			}
			else {
				//Failure
			}
		}
		else {
		    
			//Login unsuccessful
			//Redirect to Login Page
			
			System.out.println("invalid user");
			
			session.setAttribute("error", "Username or Password is incorrect");
		    rd = getServletContext().getRequestDispatcher("/jsp/Login.jsp");
			rd.forward(request, response);
			}
	}
}
