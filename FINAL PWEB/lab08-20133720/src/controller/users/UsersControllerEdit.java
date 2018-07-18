package controller.users;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import controller.access.AccessControllerPermit;
import model.entity.Role;
import model.entity.User;

@SuppressWarnings("serial")
public class UsersControllerEdit extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccessControllerPermit.pruebita(req, resp);
	
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		
		   if(req.getParameter("id")==null){
			   Key k = KeyFactory.createKey(User.class.getSimpleName(), new Long(req.getParameter("userId")).longValue());
			   User a = pm.getObjectById(User.class, k);
			   req.setAttribute("user", a); // el objeto cuyo id es el que queremos modificar
			   
			   try{
			   Query q = pm.newQuery(model.entity.Role.class); // envio lista de roles
				List<Role> roles = (List<Role>) q.execute();
				req.setAttribute("roles", roles);
				Query p = pm.newQuery(model.entity.User.class); // envio lista de users
				List<User> users= (List<User>) p.execute();
				req.setAttribute("users", users);
		   }catch(Exception e){
				System.out.println(e + " Error en envio de listas de roles y usuarios... ");
			}	
		}
						
		   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Users/edit.jsp"); 
		   dispatcher.forward(req, resp); // forward the request to the jsp
		}catch(Exception e)  {
			System.out.println(e);
		} 
		//hasta aqui envia normal  al jsp
		
		
		if (req.getParameter("id")!=null){
			try {
		   Key j = KeyFactory.createKey(User.class.getSimpleName(), new Long(req.getParameter("id")).longValue());
				User editado = pm.getObjectById(User.class, j);
				
				Long role = new Long(req.getParameter("role")).longValue();
				
				editado.setRoleId(role);
				editado.setEmail(req.getParameter("email"));
				String date = req.getParameter("birth[day]")+"/"+req.getParameter("birth[month]")+"/"+req.getParameter("birth[year]");
		         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	             Date birth = df.parse(date);
				editado.setBirth(birth);
				
				if(req.getParameter("gender").equalsIgnoreCase("Femenino")) //femenino
	        		 editado.setGender(true);
	        	 else
	        		 editado.setGender(false);

				if(req.getParameter("status").equalsIgnoreCase("No Activo"))
					editado.setStatus(false);
				else
				editado.setStatus(true);
					
			}catch(Exception e){
				System.out.println("Error de modificación");
		}finally {
				pm.close();
				resp.sendRedirect("/users/view?userId="+req.getParameter("id"));
			}
	      
		}
		
		
	}

}
