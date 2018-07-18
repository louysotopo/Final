package controller.resources;

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
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourcesControllerView extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccessControllerPermit.pruebita(req, resp);
		try{
		resp.setContentType("text/plain");		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(req.getParameter("resourceId")).longValue());
	
		      Resource ver = pm.getObjectById(Resource.class, k);
   
           // pass the list to the jsp
   req.setAttribute("resource", ver);
   // forward the request to the jsp
   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Resources/view.jsp"); 
   dispatcher.forward(req, resp);  
		}catch(Exception e){}
	
	}
}
