package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controller.PMF;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Ventas {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long ventasId;
	@Persistent private Long clientId;
	@Persistent private int numRegistro;
	@Persistent private String clientDocIde; 
	@Persistent private String clientName; 
	@Persistent private String documento; //01-F00321-00123
	@Persistent private Date fecha;
	@Persistent	private boolean status;
	int contador=0;
 	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
	//datos para la mercadería vendida
 	@Persistent private Long productId;
	@Persistent private String productMedida;
	@Persistent private String producName; 
	@Persistent private int cantidad; // independiente del objeto producto
	@Persistent private double precioU; // independiente del objeto producto
	@Persistent private double costoUV;
	
	public Ventas(Long clientId, String documento, Long productId, int cantidad, double precioU) {
		this.clientId=clientId;
		this.clientName=this.getclientIdObject(clientId).getName();
		this.clientDocIde=this.getclientIdObject(clientId).getDocIde();
		this.documento=documento;
		this.fecha = new Date();
		this.status= true;
		this.productId=productId;
		this.producName= this.getProductIdObject(productId).getName();
		this.productMedida=this.getProductIdObject(productId).getMedida();
		this.cantidad = cantidad;
		this.precioU = precioU;
		this.costoUV = this.getProductIdObject(productId).getCostoU();// antes de actualizarse con la venta
		
		this.numRegistro=++contador;
		
	}

	public double getCostoUV() {
		return costoUV;
	}

	public void setCostoUV(double costoUV) {
		this.costoUV = costoUV;
	}

	public void actualizarRegistro(Long clientId, String documento, Long productId, int cantidad, double precioU){
		
		this.setclientId(clientId);
		this.setclientName(this.getclientIdObject(clientId).getName());
		this.setclientDocIde(this.getclientIdObject(clientId).getDocIde());
		this.setDocumento(documento);
		this.setProductId(productId);
		this.setProducName(this.getProductIdObject(productId).getName());
		this.setProductMedida(this.getProductIdObject(productId).getMedida());
		this.setCantidad(cantidad);
		this.setPrecioU(precioU);
		
	}
	public int getNumRegistro(){
		return this.numRegistro;
	}
	public Long getventasId() {
		return ventasId;
	}
	public String getId() {
		return Long.toString(ventasId);
	}

	public void setId(String ventasId) {
		Long clave =Long.parseLong(ventasId);
		this.ventasId = clave;
	}

	public Client getclientIdObject(Long clientId){
		try{
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			Key k = KeyFactory.createKey(Client.class.getSimpleName(), new Long(clientId).longValue()); //aqui
			   Client a = pm.getObjectById(Client.class, k); // no esta encotrando el rol eliminadoooooo
			   if(a==null)
					return null;
				else{
			return a;
			}
			}catch(Exception e){
				return null;
			}
	}
	
	
	public Long getclientId() {
		return clientId;
	}

	public void setclientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getclientDocIde() {
		return clientDocIde;
	}

	public void setclientDocIde(String clientDocIde) {
		this.clientDocIde = clientDocIde;
	}

	public String getclientName() {
		return clientName;
	}

	public void setclientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductMedida() {
		return productMedida;
	}

	public void setProductMedida(String productMedida) {
		this.productMedida = productMedida;
	}

	public String getProducName() {
		return producName;
	}

	public void setProducName(String producName) {
		this.producName = producName;
	}

	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}

	public Mercaderia getProductIdObject(Long productId){
		try{
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			Key k = KeyFactory.createKey(Mercaderia.class.getSimpleName(), new Long(productId).longValue()); //aqui
			  Mercaderia a = pm.getObjectById(Mercaderia.class, k); // no esta encotrando el rol eliminadoooooo
			   if(a==null)
					return null;
				else{
			return a;
			}
			}catch(Exception e){
				return null;
			}
	}
	
	
	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public double getPrecioU() {
		return precioU;
	}



	public void setPrecioU(double precioU) {
		this.precioU = precioU;
	}
	public Date getFecha() {
		return fecha;
	}
	
	public String getFechaFormatted() {
		return formatter.format(fecha);
	}
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStatusMostrar() {
		if (this.status==true)
			return "Registrado";
		else
			return "Anulado";
	}
	
	public double Ganancia(){
		return this.precioVT()-costoVT();
	}
	public double precioVT(){
		return (double)cantidad*this.precioU;
	}
	public double costoVT(){
		return (double)cantidad*this.costoUV;
	}
	
	
	
}