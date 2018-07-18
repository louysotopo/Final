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
import model.entity.Ventas;
import model.entity.Mercaderia;
import model.entity.Client;

@SuppressWarnings("serial")
public class VentasControllerEdit extends HttpServlet {
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControllerPermit.pruebita(request, response);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
			Ventas a;
			if( request.getParameter("ventaId") != null ){
				Key k = KeyFactory.createKey(Ventas.class.getSimpleName(), new Long(request.getParameter("ventaId")).longValue());
				a = pm.getObjectById(Ventas.class, k);
			}else{
				a = new Ventas(new Long(0), "", new Long(0),0, 0.0);
			}

			request.setAttribute("venta", a);
			
			Query q = pm.newQuery(model.entity.Client.class); // es estooo
			List<Client> clientes = (List<Client>)q.execute();
			request.setAttribute("clientes", clientes);
			Query p = pm.newQuery(model.entity.Mercaderia.class); // es estooo
			List<Mercaderia> productos = (List<Mercaderia>)p.execute();
			request.setAttribute("productos", productos);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Ventas/edit.jsp");
			dispatcher.forward(request, response);
			}catch(Exception e){}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// info del registro a modificar
		Key k = KeyFactory.createKey(Ventas.class.getSimpleName(), new Long(request.getParameter("ventaId")).longValue());
		Ventas a = pm.getObjectById(Ventas.class, k);
		int cantidad=a.getCantidad();
		double costoU=a.getCostoUV();
		
		// información posiblemente afectada
		Mercaderia productoAnterior = a.getProductIdObject(a.getProductId());
		
		
		//info del producto a afectar
	    Key pro = KeyFactory.createKey(Mercaderia.class.getSimpleName(), new Long(request.getParameter("productoId")).longValue());
		Mercaderia producto = pm.getObjectById(Mercaderia.class, pro);
		
		int nuevacantidad= Integer.parseInt(request.getParameter("cantidad"));
		double nuevoprecioU= Double.parseDouble(request.getParameter("unitario"));
		
		// info de proveedor
	    Key j = KeyFactory.createKey(Client.class.getSimpleName(), new Long(request.getParameter("clientId")).longValue());
	    Client client = pm.getObjectById(Client.class, j);
	    
		if(productoAnterior!=null && productoAnterior.getCantidad()>=nuevacantidad)
			a.actualizarRegistro(client.getClientId(), request.getParameter("documento"), producto.getId(), nuevacantidad, nuevoprecioU);
		
		if(productoAnterior!=null && productoAnterior.getId()!=producto.getId() && productoAnterior.getCantidad()>=nuevacantidad){ 
			productoAnterior.aumentarStock(costoU, cantidad);
			producto.disminuirStockVenta(nuevacantidad);
		}
		if(productoAnterior!=null && productoAnterior.getId()==producto.getId()){ 
			productoAnterior.aumentarStock(costoU, cantidad);
			productoAnterior.disminuirStockVenta(nuevacantidad);
		}
	  	
		response.sendRedirect("/ventas");
	}
}
