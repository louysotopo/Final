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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import controller.access.AccessControllerPermit;
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourcesControllerEdit extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccessControllerPermit.pruebita(req, resp);
	
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(req.getParameter("resourceId")).longValue());
		Resource a = pm.getObjectById(Resource.class, k);
		   req.setAttribute("resource", a); // el objeto cuyo id es el que queremos modificar
		   // forward the request to the jsp
		   
		   if(req.getParameter("id")==null){
			   try{
			   Query q = pm.newQuery(model.entity.Resource.class); // es estooo
				List<Resource> resources = (List<Resource>) q.execute();
				req.setAttribute("resources", resources);
		   }catch(Exception e){
				System.out.println(e);
			}	
		}
		   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Resources/edit.jsp"); 
		   dispatcher.forward(req, resp); 
		}catch(Exception e)  {
			System.out.println(e);
		} 
		//hasta aqui envia nnormal  al jsp
		
		
		if (req.getParameter("id")!=null){
			try {
		   Key j = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(req.getParameter("id")).longValue());
		   Resource editado = pm.getObjectById(Resource.class, j);
				editado.setName(req.getParameter("name"));
				
				if(req.getParameter("status").equalsIgnoreCase("No Activo"))
					editado.setStatus(false);
				else
				editado.setStatus(true);
					
			}catch(Exception e){
				System.out.println("Error de modificación");
		}finally {
				pm.close();
				resp.sendRedirect("/resources/view?resourceId="+req.getParameter("id"));
			}
	      
		}
		
		
	}

}
