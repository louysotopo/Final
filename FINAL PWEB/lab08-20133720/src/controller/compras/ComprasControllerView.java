package controller.compras;

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
import model.entity.Compras;

@SuppressWarnings("serial")
public class ComprasControllerView extends HttpServlet {

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

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Compras/view.jsp");
			dispatcher.forward(request, response);
			}catch(Exception e){}
		
	}

		
	}