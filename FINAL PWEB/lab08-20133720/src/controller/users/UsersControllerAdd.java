package controller.users;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import model.entity.Role;
import model.entity.User;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		AccessControllerPermit.pruebita(request, response);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
		if(request.getParameter("aux")==null){
		try{
		Query u = pm.newQuery(model.entity.User.class); // es estooo
		Query r = pm.newQuery(model.entity.Role.class); 
				
		// es estooo
			List<User> users = (List<User>) u.execute();
			List<Role> roles = (List<Role>) r.execute();
			request.setAttribute("users", users);
			request.setAttribute("roles", roles);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/Users/add.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			System.out.println(e);
		}	
	}
			
			if(request.getParameter("email")!=null){
				
				   
				         try {
				        	 Date birth = null;
				         
						     try{
				        		 String date = request.getParameter("birth[day]")+"/"+request.getParameter("birth[month]")+"/"+request.getParameter("birth[year]");
						         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					             birth = df.parse(date);
						     }catch(Exception e)
					         {
					        	 System.out.println("Error de fecha");
					         }
					         	boolean gender;
								
								if(request.getParameter("gender").equalsIgnoreCase("true")) //femenino
					        		 gender=true;
					        	 else
					        		 gender=false;
						// User(String email, Role role, Date birth, boolean gender )
								
								//Key k = KeyFactory.createKey(Role.class.getSimpleName(), new Long(request.getParameter("role")).longValue());
					        	Long role = new Long(request.getParameter("role")).longValue();
							   User a = new User(
							    request.getParameter("email"),
							    role,
							    birth,
							    gender
							   );
							   // persist the entity
				        pm.makePersistent(a);
				       
				         }catch(Exception e)
				         {
				        	 System.out.println("Error de constructor User");
				         }
				         finally {
				        	   pm.close();
				        	  response.sendRedirect("/users");
				         }
			
				         
			}
	}
}
