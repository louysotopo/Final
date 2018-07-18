package model.entity;

import javax.jdo.annotations.IdentityType;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Client {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String address;
	
	@Persistent
	private String celular; //*
	
	@Persistent
	private String email;
	
	@Persistent
	private String docIde; //*doc de identidad DNI / RUC
	 
	@Persistent
	private Date fecha;
 
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@Persistent
	private boolean status;
	
	
	@Persistent
	private String ruc; 
	
	public Client(String name, String address, String email, String ide,String cel,String ru,boolean sta) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.docIde = ide;
		this.celular = cel;
		this.fecha = new Date();
		this.ruc = ru;
		this.status = sta;
	}
	
	public Long getClientId() {
		return this.id;
	}
	public String getId() {
		return Long.toString(id);
	}

	public void setId(String idPersona) {
		Long clave =Long.parseLong(idPersona);
		this.id = clave;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocIde() {
		return this.docIde;
	}

	public void setDocIde(String color) {
		this.docIde = color;
	}
	public void setCelular(String a){
		this.celular = a;
	}
	public String getCelular(){
		return this.celular;
	}
	public void insertStatus( boolean aux){
		this.status = aux;
	}
	public String getFecha(){
		return formatter.format(fecha);
	}
	public void UpDateFecha(){
		Date date1= new Date();
		this.fecha = date1;
	}
	public void setRuc(String a){
		this.ruc = a;
	}
	public String getRuc(){
		return this.ruc;
	}
	public boolean getStatus(){
		return this.status;
	}
	
}