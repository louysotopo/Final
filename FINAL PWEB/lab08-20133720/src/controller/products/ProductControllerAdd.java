package controller.products;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.PMF;
import controller.access.AccessControllerPermit;
import model.entity.Mercaderia;

@SuppressWarnings("serial")
public class ProductControllerAdd extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AccessControllerPermit.pruebita(request, response);
		try{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Products/add.jsp");
			rd.forward(request, response);
		}catch(Exception e){}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		final PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Mercaderia a = new Mercaderia(request.getParameter("name"),request.getParameter("medida"));
			pm.makePersistent(a);
			response.sendRedirect("/products");
		}catch (Exception e) {
			System.out.println(e);
		} finally {
			pm.close();
		}
	}
}
