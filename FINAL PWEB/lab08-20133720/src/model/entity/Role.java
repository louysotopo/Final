package model.entity;

import javax.jdo.annotations.IdentityType;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Role{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long roleId;
	@Persistent
	private String name;
	@Persistent
	private boolean status;
	@Persistent
	private Date created;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
 
	public Role (){}
	
	public Role(String name) {
		
		this.name = name;
		this.status=true;
		this.created=new Date(); // fecha de creacion
		
	}
	
	public Long getRoleId() {
		return roleId;
	}
	public String getId() {
		return Long.toString(roleId);
	}
	public void setId(String id) {
		Long clave =Long.parseLong(id);
		this.roleId = clave;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStatusMostrar() {
		if (this.status==true)
			return "Activo";
		else
			return "No activo";
	}
	
	public Date getCreated() {
		return created;
	}
	public String getCreatedFormatted() {
		return formatter.format(this.created);
	}
	
	
}