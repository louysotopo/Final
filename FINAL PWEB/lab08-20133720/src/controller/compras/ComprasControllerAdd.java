package controller.compras;

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
import model.entity.Provider;
import model.entity.Mercaderia;
import model.entity.Compras;

@SuppressWarnings("serial")
public class ComprasControllerAdd extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AccessControllerPermit.pruebita(request, response);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			
			Query q = pm.newQuery(model.entity.Provider.class); // es estooo
			List<Provider> providers = (List<Provider>) q.execute();
			request.setAttribute("providers", providers);
			Query p = pm.newQuery(model.entity.Mercaderia.class); // es estooo
			List<Mercaderia> productos = (List<Mercaderia>) p.execute();
			request.setAttribute("productos", productos);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Compras/add.jsp");
			rd.forward(request, response);
		}catch(Exception e){}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
	   if (request.getParameter("providerId")!=null){
		   // info de proveedor
		    Key j = KeyFactory.createKey(Provider.class.getSimpleName(), new Long(request.getParameter("providerId")).longValue());
		    Provider provider = pm.getObjectById(Provider.class, j);
		    
		    //info del producto a afectar
		    Key pro = KeyFactory.createKey(Mercaderia.class.getSimpleName(), new Long(request.getParameter("productoId")).longValue());
			Mercaderia producto = pm.getObjectById(Mercaderia.class, pro);
			
			int cantidad= Integer.parseInt(request.getParameter("cantidad"));
			double costoU= Double.parseDouble(request.getParameter("unitario"));
			producto.aumentarStock(costoU, cantidad);
			
			Compras c = new Compras(
				    provider.getProviderid(),
				    request.getParameter("documento"),
				    producto.getId(),
				    cantidad,
				    costoU
				   );
			 pm.makePersistent(c);
			 
	   }
	   response.sendRedirect("/compras");
		}catch (Exception e) {
		} finally {
			pm.close();
		}
	}
}
