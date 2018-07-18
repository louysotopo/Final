package controller.users;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import controller.access.AccessControllerPermit;
import model.entity.User;

@SuppressWarnings("serial")
public class UsersControllerView extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccessControllerPermit.pruebita(req, resp);
		try{
		resp.setContentType("text/plain");		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Key k = KeyFactory.createKey(User.class.getSimpleName(), new Long(req.getParameter("userId")).longValue());
	
		User ver = pm.getObjectById(User.class, k);
   
           // pass the list to the jsp
   req.setAttribute("user", ver);
   // forward the request to the jsp
   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/view.jsp"); 
   dispatcher.forward(req, resp);  
	
		}catch(Exception e){
	}
	}
}
