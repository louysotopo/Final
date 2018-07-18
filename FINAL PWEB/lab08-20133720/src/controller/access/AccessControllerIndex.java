package controller.access;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.PMF;
import model.entity.Access;

@SuppressWarnings("serial")
public class AccessControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccessControllerPermit.pruebita(req, resp);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(model.entity.Access.class);
		q.setOrdering("roleId DESC");
		try{
			List<Access> accesses = (List<Access>)q.execute();
			
			req.setAttribute("accesses", accesses);
			
			RequestDispatcher despachador = req.getRequestDispatcher("/WEB-INF/Views/Access/index.jsp");
			
			despachador.forward(req, resp);
				
		}catch(Exception e){ System.out.println("Error en envío de información");}
		finally{q.closeAll();}
		
	}
}
