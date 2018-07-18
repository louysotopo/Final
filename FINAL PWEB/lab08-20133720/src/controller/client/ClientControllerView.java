package controller.client;

import java.io.IOException;

import javax.servlet.http.*;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jdo.PersistenceManager;
import model.entity.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.*;
import controller.access.AccessControllerPermit;

@SuppressWarnings("serial")
public class ClientControllerView  extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		
	AccessControllerPermit.pruebita(request, response);
		final  PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Client/view.jsp");
		
		Key k = KeyFactory.createKey(Client.class.getSimpleName(), new Long(request.getParameter("Id")).longValue()); 
		Client  a = pm.getObjectById(Client.class, k);
		
		
		request.setAttribute("ClientObj", a);
		rd.forward(request, response);
			
		
		}catch(Exception e){}
		 
			
	}
			

}