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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import model.entity.Role;
import model.entity.Resource;
import model.entity.Access;

@SuppressWarnings("serial")
public class AccessControllerEdit extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccessControllerPermit.pruebita(req, resp);
	
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		
		   if(req.getParameter("id")==null){
			   Key k = KeyFactory.createKey(Access.class.getSimpleName(), new Long(req.getParameter("accessId")).longValue());
			   Access a = pm.getObjectById(Access.class, k);
			   req.setAttribute("access", a); // el objeto cuyo id es el que queremos modificar
			   
			   try{
			   Query ro = pm.newQuery(model.entity.Role.class);
			   Query re = pm.newQuery(model.entity.Resource.class); // envio lista de roles
				List<Role> roles = (List<Role>) ro.execute();
				List<Resource> resources = (List<Resource>) re.execute();
				req.setAttribute("roles", roles);
				req.setAttribute("resources", resources);
				
		   }catch(Exception e){
				System.out.println(e + " Error en envio de listas de roles y resources... ");
			}	
		}
						
		   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/edit.jsp"); 
		   dispatcher.forward(req, resp); // forward the request to the jsp
		}catch(Exception e)  {
			System.out.println(e);
		} 
		//hasta aqui envia normal  al jsp
		
		
		if (req.getParameter("id")!=null){
			try {
		   Key j = KeyFactory.createKey(Access.class.getSimpleName(), new Long(req.getParameter("id")).longValue());
		   Access editado = pm.getObjectById(Access.class, j);
				
				Long role = new Long(req.getParameter("role")).longValue();
				Long resource = new Long(req.getParameter("resource")).longValue();
				
				editado.setRole(role);
				editado.setResource(resource);
				
				if(req.getParameter("status").equalsIgnoreCase("No Activo"))
					editado.setStatus(false);
				else
				editado.setStatus(true);
					
			}catch(Exception e){
				System.out.println("Error de modificación");
		}finally {
				pm.close();
				resp.sendRedirect("/access/view?accessId="+req.getParameter("id"));
			}
	      
		}
		
		
	}

}
