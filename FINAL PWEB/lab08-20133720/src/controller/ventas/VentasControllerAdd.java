package controller.ventas;

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
import model.entity.Client;
import model.entity.Mercaderia;
import model.entity.Ventas;

@SuppressWarnings("serial")
public class VentasControllerAdd extends HttpServlet {
int contador=0;
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AccessControllerPermit.pruebita(request, response);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			
			Query q = pm.newQuery(model.entity.Client.class); // es estooo
			List<Client> clientes = (List<Client>) q.execute();
			request.setAttribute("clientes", clientes);
			Query p = pm.newQuery(model.entity.Mercaderia.class); // es estooo
			List<Mercaderia> productos = (List<Mercaderia>) p.execute();
			request.setAttribute("productos", productos);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Ventas/add.jsp");
			rd.forward(request, response);
		}catch(Exception e){}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
	   if (request.getParameter("clientId")!=null){
		   // info de proveedor
		    Key j = KeyFactory.createKey(Client.class.getSimpleName(), new Long(request.getParameter("clientId")).longValue());
		    Client client = pm.getObjectById(Client.class, j);
		    
		    //info del producto a afectar
		    Key pro = KeyFactory.createKey(Mercaderia.class.getSimpleName(), new Long(request.getParameter("productoId")).longValue());
			Mercaderia producto = pm.getObjectById(Mercaderia.class, pro);
			
			int cantidad= Integer.parseInt(request.getParameter("cantidad"));
			double precioU= Double.parseDouble(request.getParameter("unitario"));
			
			if(cantidad<=producto.getCantidad()){
			
			
			Ventas c = new Ventas(
					client.getClientId(),
				    request.getParameter("documento"),
				    producto.getId(),
				    cantidad,
				    precioU
				   );
			contador++;
			c.setNumRegistro(contador);
			producto.disminuirStockVenta(cantidad);
			pm.makePersistent(c);
			 
			 
			}
			 
	   }
	   response.sendRedirect("/ventas");
		}catch (Exception e) {
		} finally {
			pm.close();
		}
	}
}
