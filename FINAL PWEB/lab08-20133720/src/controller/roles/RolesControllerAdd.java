package controller.roles;

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
import model.entity.Role;

@SuppressWarnings("serial")
public class RolesControllerAdd extends HttpServlet {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AccessControllerPermit.pruebita(request, response);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
		if(request.getParameter("aux")==null){
		try{
		Query q = pm.newQuery(model.entity.Role.class); // es estooo
			List<Role> roles = (List<Role>) q.execute();
			request.setAttribute("roles", roles);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Roles/add.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			System.out.println(e);
		}	
	}
			
			if(request.getParameter("name")!=null){
				   
				         try {
				        	// create the new Provider
				        
							   Role a = new Role(request.getParameter("name"));
							   // persist the entity
							   pm.makePersistent(a); // se agregoo, yeah :)
				       
				         }catch(Exception e)
				         {
				        	 System.out.println("Error, no se pudo ingresar");
				         }
				         finally {
				        	   pm.close();
				        	  response.sendRedirect("/roles");
				         }
			}
		  
}
}
