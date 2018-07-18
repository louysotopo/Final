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


import controller.PMF;
import controller.access.AccessControllerPermit;
import model.entity.Compras;

@SuppressWarnings("serial")
public class ComprasControllerIndex extends HttpServlet{

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {						  
		AccessControllerPermit.pruebita(req, resp);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
						
				Query q = pm.newQuery(model.entity.Compras.class); // es estooo
				q.setOrdering("fecha DESC");
							 
				try{
					List<Compras> compras = (List<Compras>) q.execute();
					req.setAttribute("compras", compras);
					RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Compras/index.jsp");
					despachador .forward(req, resp);
				}catch(Exception e){
					System.out.println(e);
				}
			
				 finally{
					q.closeAll();
				}
			
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}


