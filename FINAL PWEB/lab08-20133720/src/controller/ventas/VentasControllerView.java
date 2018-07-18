package controller.ventas;

import java.io.IOException;

import javax.jdo.PersistenceManager;
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

@SuppressWarnings("serial")
public class VentasControllerView extends HttpServlet  {
	@Override
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

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Ventas/view.jsp");
			dispatcher.forward(request, response);
			}catch(Exception e){}
		
	}
}
