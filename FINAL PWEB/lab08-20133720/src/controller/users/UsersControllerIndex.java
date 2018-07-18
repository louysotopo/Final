package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.PMF;
import controller.access.AccessControllerPermit;
import model.entity.User;

@SuppressWarnings("serial")
public class UsersControllerIndex extends HttpServlet{
	  // create the persistence manager instance
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {	
		AccessControllerPermit.pruebita(req, resp);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(model.entity.User.class); // es estooo
		q.setOrdering("created DESC");
						 
		try{
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>) q.execute();
			req.setAttribute("users", users);
			RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Users/index.jsp");
			despachador .forward(req, resp);
		}catch(Exception e){
			System.out.println(e);
		}
	
		 finally{
			q.closeAll();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		  doGet(request, response);
	 }
}
	
