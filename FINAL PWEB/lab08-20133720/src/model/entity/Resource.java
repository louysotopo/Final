package model.entity;

import javax.jdo.annotations.IdentityType;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Resource{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long resourceId;
	@Persistent
	private String name;
	@Persistent
	private boolean status;
	@Persistent
	private Date created;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
 
	public Resource (){}
	
	public Resource(String name) {
		
		this.name = name;
		this.status=true;
		this.created=new Date(); // fecha de creacion
		
	}
	
	public Long getResourceId() {
		return resourceId;
	}
	public String getId() {
		return Long.toString(resourceId);
	}
	public void setId(String id) {
		Long clave =Long.parseLong(id);
		this.resourceId = clave;
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