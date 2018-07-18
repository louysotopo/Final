package controller.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.*;
import controller.*;

@SuppressWarnings("serial")
public class UsersControllerTest<E> extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		
	
	try{
		Query r = pm.newQuery(model.entity.Resource.class);
		Query r2 = pm.newQuery(model.entity.Role.class);
		Query r3 = pm.newQuery(model.entity.Access.class);
		Query r4 = pm.newQuery(model.entity.User.class);
		List<model.entity.Resource> r1 = (List<model.entity.Resource>) r.execute();
		List<model.entity.Role> r21 = (List<model.entity.Role>) r2.execute();
		List<model.entity.Access> r31 = (List<model.entity.Access>) r3.execute();
		List<model.entity.User> r41 = (List<model.entity.User>) r4.execute();
		for(model.entity.Resource a: r1) pm.deletePersistent(a);
		for(model.entity.Role a: r21) pm.deletePersistent(a);
		for(model.entity.Access a: r31) pm.deletePersistent(a);
		for(model.entity.User a: r41) pm.deletePersistent(a);
		
	}catch(Exception e){
		System.out.println("Error en eliminar "+e);
	}
	
	try{
		// construyendo los roles
		Role role = new Role("Administrador");
		
		// construyendo los recursos
		ArrayList<Resource> resources = new ArrayList<Resource>();
		resources.add(new Resource("/ventas"));
		resources.add(new Resource("/ventas/add"));
		resources.add(new Resource("/ventas/edit"));
		resources.add(new Resource("/ventas/view"));
		resources.add(new Resource("/ventas/delete"));
		resources.add(new Resource("/compras"));
		resources.add(new Resource("/compras/add"));
		resources.add(new Resource("/compras/edit"));
		resources.add(new Resource("/compras/view"));
		resources.add(new Resource("/compras/delete"));
		resources.add(new Resource("/products"));
		resources.add(new Resource("/products/add"));
		resources.add(new Resource("/products/edit"));
		resources.add(new Resource("/products/view"));
		resources.add(new Resource("/products/delete"));
		resources.add(new Resource("/client"));
		resources.add(new Resource("/client/add"));
		resources.add(new Resource("/client/update"));
		resources.add(new Resource("/client/view"));
		resources.add(new Resource("/client/delete"));
		resources.add(new Resource("/providers"));
		resources.add(new Resource("/providers/add"));
		resources.add(new Resource("/providers/edit"));
		resources.add(new Resource("/providers/view"));
		resources.add(new Resource("/providers/delete"));
		resources.add(new Resource("/users"));
		resources.add(new Resource("/users/add"));
		resources.add(new Resource("/users/edit"));
		resources.add(new Resource("/users/view"));
		resources.add(new Resource("/users/delete"));
		resources.add(new Resource("/access"));
		resources.add(new Resource("/access/add"));
		resources.add(new Resource("/access/edit"));
		resources.add(new Resource("/access/view"));
		resources.add(new Resource("/access/delete"));
		resources.add(new Resource("/roles"));
		resources.add(new Resource("/roles/add"));
		resources.add(new Resource("/roles/edit"));
		resources.add(new Resource("/roles/view"));
		resources.add(new Resource("/roles/delete"));
		resources.add(new Resource("/resources"));
		resources.add(new Resource("/resources/add"));
		resources.add(new Resource("/resources/edit"));
		resources.add(new Resource("/resources/view"));
		resources.add(new Resource("/resources/delete"));
		
		
			pm.makePersistent(role);
		for(Resource resource:resources)
			pm.makePersistent(resource);
		
		try{ // creando usuarios administradores
			// User(String email, Long role, Date birth, boolean gender )
			Date date = new Date();
			User admin = new User("richarteq@gmail.com",role.getRoleId(),date,false);
			User alumn = new User("fanny.llq@gmail.com",role.getRoleId(),date,true);
			User alumn2 = new User("louysotopo123@gmail.com",role.getRoleId(),date,false);
			User alumn3 = new User("mireyachacondoricoa@gmail.com",role.getRoleId(),date,true);
			
			pm.makePersistent(admin);
			pm.makePersistent(alumn);
			pm.makePersistent(alumn2);
			pm.makePersistent(alumn3);
		}catch(Exception e){
			System.out.println("Error en creacion de usuario "+e);
		}
		
		
		try{
			// creando los accesos
			Query re = pm.newQuery(model.entity.Resource.class); // es estooo
			Query ro = pm.newQuery(model.entity.Role.class);
			List<model.entity.Resource> recursosAñadidos = (List<model.entity.Resource>) re.execute();
			List<model.entity.Role> rolesAñadidos = (List<model.entity.Role>) ro.execute();
		
			for(model.entity.Resource a: recursosAñadidos)
				pm.makePersistent(new model.entity.Access(rolesAñadidos.get(0).getRoleId(),a.getResourceId()));
		
		}catch(Exception e){
			System.out.println("erro en creacion de accesos" + e);
		}
			
	}catch(Exception e)
    {
   	 System.out.println("Error de constructor");
    }
   // finally {
   	   pm.close();
   	  resp.sendRedirect("/index.html");
    //}
		
	}
}
