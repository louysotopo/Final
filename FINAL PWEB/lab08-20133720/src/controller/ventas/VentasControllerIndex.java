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


import controller.PMF;
import controller.access.AccessControllerPermit;
import model.entity.Ventas;

@SuppressWarnings("serial")
public class VentasControllerIndex extends HttpServlet{

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {						  
		AccessControllerPermit.pruebita(req, resp);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
						
				Query q = pm.newQuery(model.entity.Ventas.class); // es estooo
				q.setOrdering("fecha ASCE");
							 
				try{
					List<Ventas> ventas= (List<Ventas>) q.execute();
					req.setAttribute("ventas", ventas);
					RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Ventas/index.jsp");
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


