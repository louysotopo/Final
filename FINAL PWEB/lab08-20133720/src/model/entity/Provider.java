package model.entity;

import javax.jdo.annotations.IdentityType;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Provider{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long providerid;
	
	@Persistent
	private String name;
	
	@Persistent
	private String address;
	
	@Persistent
	private String celular; 
	
	@Persistent
	private String email;
	
	@Persistent
	private String docIde;
	
	@Persistent
	private Date fecha;
	@Persistent
	private boolean status;

 	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
	public Provider (){}
	
	public Provider(String name, String address, String celular, String email, String ide, boolean status) {
		super();
		this.name = name;
		this.address = address;
		this.celular = celular;
		this.email = email;
		this.docIde = ide;
		this.fecha = new Date();
		this.status=status;
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
	
	public Long getProviderid() {
		return providerid;
	}
	public String getId() {
		return Long.toString(providerid);
	}

	public void setId(String idPersona) {
		Long clave =Long.parseLong(idPersona);
		this.providerid = clave;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String lastname) {
		this.address = lastname;
	}
	public String getCelular() {
		return this.celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocIde() {
		return this.docIde;
	}

	public void setDocIde(String docIde) {
		this.docIde = docIde;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public String getFechaFormatted() {
		return formatter.format(fecha);
	}

}