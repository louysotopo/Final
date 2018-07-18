package controller.access;

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
import model.entity.Role;
import model.entity.Resource;
import model.entity.Access;

@SuppressWarnings("serial")
public class AccessControllerAdd extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AccessControllerPermit.pruebita(request, response);
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
		if(request.getParameter("aux")==null){
		try{
		Query u = pm.newQuery(model.entity.Resource.class); // es estooo
		u.setOrdering("name DESC");
		Query r = pm.newQuery(model.entity.Role.class);
		r.setOrdering("name DESC");
				
			List<Resource> resources = (List<Resource>) u.execute();
			List<Role> roles = (List<Role>) r.execute();
			request.setAttribute("resources", resources);
			request.setAttribute("roles", roles);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Access/add.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			System.out.println(e);
		}	
	}
			
			if(request.getParameter("role")!=null){
				
				   
				         try {
				        	 Long role = new Long(request.getParameter("role")).longValue();
				        	 Long resource = new Long(request.getParameter("resource")).longValue();
							 Access a = new Access(role,resource);
							 pm.makePersistent(a);
				       
				         }catch(Exception e)
				         {
				        	 System.out.println("Error de constructor Access");
				         }
				         finally {
				        	   pm.close();
				        	  response.sendRedirect("/access");
				         }
			}
	}
}
