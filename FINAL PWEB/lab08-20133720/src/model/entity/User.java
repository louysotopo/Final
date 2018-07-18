package model.entity;

import javax.jdo.annotations.IdentityType;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import controller.PMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long userId;
	@Persistent
	private String email;
	@Persistent
	private Long roleId;
	@Persistent
	private Date birth;
	@Persistent
	private Boolean gender;  // true femenino, false masculino
	@Persistent
	private boolean status;
	@Persistent
	private Date created;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
 
	public User (){}
	
	public User(String email, Long role, Date birth, boolean gender ) {
		
		this.email = email;
		this.roleId = role;
		this.birth = birth;
		this.gender=gender;
		this.status=true;
		this.created=new Date(); // fecha de creacion
		
	}
	
	public Long getUserId() {
		return userId;
	}
	public String getId() {
		return Long.toString(userId);
	}
	public void setId(String idPersona) {
		Long clave =Long.parseLong(idPersona);
		this.userId = clave;
	}
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleId() {
		return this.roleId;
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

	public void setRoleId(Long role) {
		this.roleId = role;
	}
	public String getBirthFormatted() {
		return formatter.format(this.birth);
	}
	public Date getBirth() {
		return this.birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getGenderMostrar() {
		if (this.gender==true)
			return "Femenino";
		else
			return "Masculino";
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