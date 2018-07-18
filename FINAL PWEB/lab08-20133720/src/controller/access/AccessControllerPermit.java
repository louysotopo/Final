package controller.access;

import java.io.IOException;
import java.util.List;


import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;



	@SuppressWarnings("serial")
	public class AccessControllerPermit extends HttpServlet{
		@SuppressWarnings("unchecked")
		public static void pruebita(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException{
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
			String error="";
			if(uGoogle == null){
				error="No hay usuario logeado";
				req.setAttribute("error", error);
				RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
				despachador .forward(req, resp);
				
				
			}else{
				String query="select from " + model.entity.User.class.getName()+  //User.class.getName()
				" where email=='" + uGoogle.getEmail()+"'"+
				" && status==true";
				List<model.entity.User> uSearch = (List<model.entity.User>) pm.newQuery(query).execute();
				
				if(uSearch.isEmpty()){
					error ="El correo logeado no está registrado como usuario";
					req.setAttribute("error", error);
					RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
					despachador.forward(req, resp);
					
					
				}else if(!uSearch.get(0).getRoleIdName().equals("Administrador")){ ///aquiii
										
					String query2="select from "+model.entity.Resource.class.getName()+
							" where name=='"+req.getServletPath()+"'"+ //
							" && status==true";
					List<model.entity.Resource> rSearch = (List<model.entity.Resource>) pm.newQuery(query2).execute();
				if(rSearch.isEmpty()){
					error="El recurso no está enpadronado";
					req.setAttribute("error", error);
					RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
					despachador .forward(req, resp);
					
					
				}else{
					String query3="select from "+model.entity.Access.class.getName()+
							" where roleId=="+uSearch.get(0).getRoleId()+
							" && resourceId=="+rSearch.get(0).getResourceId()+
							" && status==true";
					List<model.entity.Access> aSearch = (List<model.entity.Access>) pm.newQuery(query3).execute();
				if(aSearch.isEmpty()){
					error="El acceso no está enpadronado";
					req.setAttribute("error", error);
					RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Errors/deny1.jsp");
					despachador .forward(req, resp);
					
				}else{
					
				}
			}// hasta aqui
		}
	}
			
		}
		
		
	}
	