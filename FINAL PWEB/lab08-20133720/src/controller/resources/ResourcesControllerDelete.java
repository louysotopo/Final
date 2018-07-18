package controller.resources;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Resource;
import model.entity.User;

@SuppressWarnings("serial")
public class ResourcesControllerDelete extends HttpServlet{
	@Override
public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		
		// comprobacion		
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
		String error="";
		if(uGoogle==null){
			error="No hay usuario logeado";
			request.setAttribute("error", error);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
			rd.forward(request, response);
		}
		else{
			final  PersistenceManager pm = PMF.get().getPersistenceManager();
			
			String query1 =  "select from "+User.class.getName()+
					" where email=='" + uGoogle.getEmail() + "'" +
					" && status==true";
			@SuppressWarnings("unchecked")
			List<model.entity.User> uSearch = (List<model.entity.User>) pm.newQuery(query1).execute();
			if(uSearch.isEmpty()){
				error ="El correo logeado no está registrado como usuario";
				request.setAttribute("error", error);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
				rd.forward(request, response);
			}
			else{
				String query2 =  "select from " + Resource.class.getName()+
						" where name=='" + request.getServletPath() + "'" +
						" && status==true";
				
				@SuppressWarnings("unchecked")
				List<Resource> rSearch = (List<Resource>) pm.newQuery(query2).execute();
				if(rSearch.isEmpty()){
					error="El recurso no está enpadronado";
					request.setAttribute("error", error);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
					rd.forward(request, response);
				}
				else{
					
					String query3 =  "select from " + Access.class.getName()+
							" where roleId== " + uSearch.get(0).getRoleId()  +
							" && resourceId== " + rSearch.get(0).getId()  +
							" && status==true";
					@SuppressWarnings("unchecked")
					List<Access> aSearch = (List<Access>) pm.newQuery(query3).execute();
					
					if(aSearch.isEmpty()){
						error="El acceso no está enpadronado";
						request.setAttribute("error", error);
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
						rd.forward(request, response);
					}
					else{
						// aqui termina conprobacion
		
						Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(request.getParameter("resourceId")).longValue());
						   Resource a = pm.getObjectById(Resource.class, k);
						
						   pm.deletePersistent(a);
						   response.sendRedirect("/resources");
		 
					}}}}
	}
	
	
	
}
