package controller.resources;

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
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourcesControllerAdd extends HttpServlet {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AccessControllerPermit.pruebita(request, response);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
		if(request.getParameter("aux")==null){
		try{
		Query q = pm.newQuery(model.entity.Resource.class); // es estooo
			List<Resource> resources = (List<Resource>) q.execute();
			request.setAttribute("resources", resources);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Resources/add.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			System.out.println(e);
		}	
	}
			
			if(request.getParameter("name")!=null){
				   
				         try {
				        	// create the new Resource
				        
							   Resource a = new Resource(request.getParameter("name"));
							   // persist the entity
							   pm.makePersistent(a); // se agregoo, yeah :)
				       
				         }catch(Exception e)
				         {
				        	 System.out.println("Error, no se pudo ingresar");
				         }
				         finally {
				        	   pm.close();
				        	  response.sendRedirect("/resources");
				         }
			}
		  
}
}
