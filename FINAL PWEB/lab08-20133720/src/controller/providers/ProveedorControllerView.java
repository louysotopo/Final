package controller.providers;

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
import model.entity.Provider;

@SuppressWarnings("serial")
public class ProveedorControllerView extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		
		AccessControllerPermit.pruebita(req, resp);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			
			resp.setContentType("text/plain");	
		Key k = KeyFactory.createKey(Provider.class.getSimpleName(), new Long(req.getParameter("providerId")).longValue());
	
		      Provider ver = pm.getObjectById(Provider.class, k);
   
           // pass the list to the jsp
   req.setAttribute("proveedor", ver);
   // forward the request to the jsp
   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Providers/view.jsp"); 
   dispatcher.forward(req, resp);  
			
		}catch(Exception e){}
	
	
	}
}
