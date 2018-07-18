package controller.providers;

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
import model.entity.Provider;

@SuppressWarnings("serial")
public class ProveedorControllerAdd extends HttpServlet {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AccessControllerPermit.pruebita(request, response);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
	if(request.getParameter("aux")==null){
		try{
		Query q = pm.newQuery(model.entity.Provider.class); // es estooo
			List<Provider> providers = (List<Provider>) q.execute();
			request.setAttribute("providers", providers);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Providers/add.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			System.out.println(e);
		}	
	}
			
			if(request.getParameter("name")!=null){
				   
				         try {
				        	// create the new Provider
				        	 
				        	 boolean estado;
							if(request.getParameter("status").equalsIgnoreCase("Activo"))
				        		 estado=true;
				        	 else
				        		 estado=false;
							   Provider a = new Provider(
							    request.getParameter("name"),
							    request.getParameter("address"),
							    request.getParameter("celular"),
							    request.getParameter("email"),
							    request.getParameter("docIde"),
							    estado
							   );
							   // persist the entity
				        pm.makePersistent(a);
				       
				         }catch(Exception e)
				         {
				        	 System.out.println("Error");
				         }
				         finally {
				        	   pm.close();
				        	  response.sendRedirect("/providers");
				         }
			}
	}	  

}
