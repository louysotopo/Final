package model.entity;

import javax.jdo.annotations.IdentityType;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Access{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long accessId;
	@Persistent
	private Long roleId;
	@Persistent
	private Long resourceId;
	@Persistent
	private boolean status;
	@Persistent
	private Date created;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
 
	public Access (){}
	
	public Access(Long role, Long resource) {
		
		this.roleId = role;
		this.resourceId = resource;
		this.status=true;
		this.created=new Date(); // fecha de creacion
		
	}
	
	public Long getAccessId() {
		return accessId;
	}
	public String getId() {
		return Long.toString(accessId);
	}
	public void setId(String id) {
		Long clave =Long.parseLong(id);
		this.accessId = clave;
	}
	
	public String getResourceIdName() {
		try{
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Resource.class.getSimpleName(), new Long(resourceId).longValue()); //aqui
		   Resource a = pm.getObjectById(Resource.class, k); // no esta encotrando el rol eliminadoooooo
		   if(a==null)
				return "no asignado";
			else{
		return a.getName();
		}
		}catch(Exception e){
			return "no asignado";
		}
	}
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRole(Long role) {
		this.roleId = role;
	}
	
	public String getRoleIdName() {
		try{
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(Role.class.getSimpleName(), new Long(roleId).longValue()); //aqui
		   Role a = pm.getObjectById(Role.class, k); // no esta encotrando el rol eliminadoooooo
		   if(a==null)
				return "no asignado";
			else{
		return a.getName();
		}
		}catch(Exception e){
			return "no asignado";
		}
	}

	public Long getResource() {
		return resourceId;
	}

	public void setResource(Long resource) {
		this.resourceId = resource;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStatusMostrar() {
		if (this.status==true)
			return "Válido";
		else
			return "No válido";
	}
	
	public Date getCreated() {
		return created;
	}
	public String getCreatedFormatted() {
		return formatter.format(this.created);
	}
	
	
}