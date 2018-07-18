package controller.products;

import java.io.IOException;
import java.util.List;

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
public class ProductControllerIndex extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {						  
		AccessControllerPermit.pruebita(req, resp);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
			String query4 = "select from " + Mercaderia.class.getName();
			@SuppressWarnings("unchecked")
			List<Mercaderia> products = (List<Mercaderia>) pm.newQuery(query4).execute();
			req.setAttribute("productos", products);
			RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/Products/index.jsp");
			dp.forward(req, resp);
			
			}catch(Exception e){}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}


