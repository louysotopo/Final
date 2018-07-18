package controller.client;

import controller.*;
import controller.access.AccessControllerPermit;

import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.*;
import javax.jdo.PersistenceManager;
import model.entity.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class ClientControllerUpdate  extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	AccessControllerPermit.pruebita(request, response);
	
	final  PersistenceManager pm = PMF.get().getPersistenceManager();
	try{			
						 
		 Key k = KeyFactory.createKey(Client.class.getSimpleName(), new Long(request.getParameter("lok")).longValue());
			Client  a = pm.getObjectById(Client.class, k);
		 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Client/update.jsp");
		 if(request.getParameter("name")== null){
			  request.setAttribute("ClientObj", a);
				rd.forward(request, response);
						
			}
		 else{
			 
			String address1=(String)request.getParameter("address");
			String name1 =(String)request.getParameter("name");
			String celu1 = (String)request.getParameter("celular");
			String docIde = (String)request.getParameter("ide");
			String email1 = (String)request.getParameter("email");
			String ruc1 = (String)request.getParameter("ru");
			boolean status1= false;
			
			if(((String)request.getParameter("statu")).equals("true")){
				status1 = true;
			}
			
			a.setAddress(address1);
			a.setName(name1);
			a.setCelular(celu1);
			a.setDocIde(docIde);
			a.setEmail(email1);
			a.setRuc(ruc1);
			a.insertStatus(status1);
			a.UpDateFecha();
		
			pm.close();
		  response.sendRedirect("/client");
		 
		 
	}
	}catch(Exception e){}
}
}