package controller.client;

import model.entity.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;


import controller.PMF;
import controller.access.AccessControllerPermit;

import javax.servlet.*;


@SuppressWarnings("serial")
public class ClientControllerAdd extends HttpServlet{
@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
	AccessControllerPermit.pruebita(req, resp);
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
						//aqui termina comprobacion
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Views/Client/add.jsp");
		if(req.getParameter("name")== null){
			rd.forward(req, resp);
		}
		else{
				boolean status1= false;
				String aux = "true";
				if(aux.equalsIgnoreCase(((String)req.getParameter("statu")))){
					status1 = true;
				}
				
				Client a = new Client( 
						req.getParameter("name"),
					    req.getParameter("address"),
					    req.getParameter("email"),
					    req.getParameter("ide"),
					    req.getParameter("celu"),
					    req.getParameter("ru"),
					    status1
				);
			try{
				pm.makePersistent(a);
			}catch(Exception e){
				System.out.println(e);
			}finally{
				pm.close();
				resp.sendRedirect("/client");
			}
			
			
			
		}
				}
				
				
				
	
	

}
	
	