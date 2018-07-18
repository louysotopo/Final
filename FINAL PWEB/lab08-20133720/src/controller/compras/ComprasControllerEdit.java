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
public class ComprasControllerEdit extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControllerPermit.pruebita(request, response);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
			Compras a;
			if( request.getParameter("compraId") != null ){
				Key k = KeyFactory.createKey(Compras.class.getSimpleName(), new Long(request.getParameter("compraId")).longValue());
				a = pm.getObjectById(Compras.class, k);
			}else{
				a = new Compras(new Long(0), "", new Long(0),0, 0.0);
			}

			request.setAttribute("compra", a);
			
			Query q = pm.newQuery(model.entity.Provider.class); // es estooo
			List<Provider> providers = (List<Provider>)q.execute();
			request.setAttribute("providers", providers);
			Query p = pm.newQuery(model.entity.Mercaderia.class); // es estooo
			List<Mercaderia> productos = (List<Mercaderia>)p.execute();
			request.setAttribute("productos", productos);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Compras/edit.jsp");
			dispatcher.forward(request, response);
			}catch(Exception e){}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// info del registro a modificar
		Key k = KeyFactory.createKey(Compras.class.getSimpleName(), new Long(request.getParameter("compraId")).longValue());
		Compras a = pm.getObjectById(Compras.class, k);
		int cantidad=a.getCantidad();
		double costoU = a.getCostoU();
		
		// información posiblemente afectada
		Mercaderia productoAnterior = a.getProductIdObject(a.getProductId());
		
		
		//info del producto a afectar
	    Key pro = KeyFactory.createKey(Mercaderia.class.getSimpleName(), new Long(request.getParameter("productoId")).longValue());
		Mercaderia producto = pm.getObjectById(Mercaderia.class, pro);
		
		int nuevacantidad= Integer.parseInt(request.getParameter("cantidad"));
		double nuevocostoU= Double.parseDouble(request.getParameter("unitario"));
		
		if(productoAnterior!=null && productoAnterior.getId()!=producto.getId() && productoAnterior.getCantidad()>=nuevacantidad){ 
			productoAnterior.disminuirStockCompra(costoU, cantidad);
			producto.aumentarStock(nuevocostoU, nuevacantidad);
		}
		if(productoAnterior!=null && productoAnterior.getId()==producto.getId()){ 
			productoAnterior.disminuirStockCompra(costoU, cantidad);
				productoAnterior.aumentarStock(nuevocostoU, nuevacantidad);
		}
		
		
		
		// info de proveedor
	    Key j = KeyFactory.createKey(Provider.class.getSimpleName(), new Long(request.getParameter("providerId")).longValue());
	    Provider provider = pm.getObjectById(Provider.class, j);
	    
	   	a.actualizarRegistro(provider.getProviderid(), request.getParameter("documento"), producto.getId(), nuevacantidad, nuevocostoU);

		response.sendRedirect("/compras");
	}
	
	}