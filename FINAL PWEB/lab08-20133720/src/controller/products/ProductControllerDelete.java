package controller.products;

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
import controller.access.AccessControllerPermit;
import model.entity.Access;
import model.entity.Client;
import model.entity.Mercaderia;
import model.entity.Resource;
import model.entity.User;

@SuppressWarnings("serial")
public class ProductControllerDelete extends HttpServlet{
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
				error ="El correo logeado no est� registrado como usuario";
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
					error="El recurso no est� enpadronado";
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
						error="El acceso no est� enpadronado";
						request.setAttribute("error", error);
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
						rd.forward(request, response);
					}
					else{
						// aqui termina conprobacion
		
						Key k = KeyFactory.createKey(Mercaderia.class.getSimpleName(), new Long(request.getParameter("mercaderiaId")).longValue());
						Mercaderia a = pm.getObjectById(Mercaderia.class, k);
						
						if(a.getCantidad()==0 && a.getCostoU()==0.0)
						pm.deletePersistent(a);
						
						response.sendRedirect("/products");	
		
	   response.sendRedirect("/client");
		 
					}}}}
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}

