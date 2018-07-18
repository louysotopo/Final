package controller.products;

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
import model.entity.Mercaderia;

@SuppressWarnings("serial")
public class ProductControllerEdit extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControllerPermit.pruebita(request, response);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
			Mercaderia a;
			if( request.getParameter("mercaderiaId") != null ){
				Key k = KeyFactory.createKey(Mercaderia.class.getSimpleName(), new Long(request.getParameter("mercaderiaId")).longValue());
				a = pm.getObjectById(Mercaderia.class, k);
			}else{
				a = new Mercaderia("", "", "0", "0");
			}

			request.setAttribute("producto", a);

			RequestDispatcher dispatcher =
					getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/edit.jsp");
			dispatcher.forward(request, response);
			}catch(Exception e){}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Mercaderia.class.getSimpleName(), new Long(request.getParameter("mercaderiaId")).longValue());
		Mercaderia a = pm.getObjectById(Mercaderia.class, k);

		a.setName(request.getParameter("name"));
		a.setMedida(request.getParameter("medida"));
	
		response.sendRedirect("/products");
	}
}
