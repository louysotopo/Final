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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;
import model.entity.Provider;
import controller.access.AccessControllerPermit;

@SuppressWarnings("serial")
public class ProveedorControllerEdit extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	AccessControllerPermit.pruebita(req, resp);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		Key k = KeyFactory.createKey(Provider.class.getSimpleName(), new Long(req.getParameter("providerId")).longValue());
		   Provider a = pm.getObjectById(Provider.class, k);
		   req.setAttribute("proveedor", a); // el objeto cuyo id es el que queremos modificar
		   // forward the request to the jsp
		   
		   if(req.getParameter("id")==null){
			   try{
			   Query q = pm.newQuery(model.entity.Provider.class); // es estooo
				List<Provider> providers = (List<Provider>) q.execute();
				req.setAttribute("providers", providers);
				
		   }catch(Exception e){
				System.out.println(e);
			}	
		}
			
			
		   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Providers/edit.jsp"); 
		   dispatcher.forward(req, resp); 
		}catch(Exception e)  {
			System.out.println(e);
		} 
		//hasta aqui envia nnormal  al jsp
		
		
		if (req.getParameter("id")!=null){
			try {
		   Key j = KeyFactory.createKey(Provider.class.getSimpleName(), new Long(req.getParameter("id")).longValue());
				Provider editado = pm.getObjectById(Provider.class, j);
				editado.setName(req.getParameter("name"));
				editado.setAddress(req.getParameter("address"));
				editado.setCelular(req.getParameter("celular"));
				editado.setEmail(req.getParameter("email"));
				editado.setDocIde(req.getParameter("docIde"));
				if(req.getParameter("status").equalsIgnoreCase("No Activo"))
					editado.setStatus(false);
				else
				editado.setStatus(true);
				
					
					
			}catch(Exception e){
				System.out.println("Error de modificación");
		}finally {
				pm.close();
				resp.sendRedirect("/providers/view?providerId="+req.getParameter("id"));
			}
	      
		}
		
		
	}

}
