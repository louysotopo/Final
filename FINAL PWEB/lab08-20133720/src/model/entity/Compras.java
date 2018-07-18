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
public class Compras {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long comprasId;
	@Persistent private Long providerId;
	@Persistent private int numRegistro;
	@Persistent private String providerDocIde; 
	@Persistent private String providerName; 
	@Persistent private String documento; //01-F00321-00123
	@Persistent private Date fecha;
	@Persistent	private boolean status;
	int contador=0;
 	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
	//datos para la mercadería adquirida
 	@Persistent private Long productId;
	@Persistent private String productMedida;
	@Persistent private String producName; 
	@Persistent private int cantidad; // independiente del objeto producto
	@Persistent private double costoU; // independiente del objeto producto
	
	public Compras(Long providerId, String documento, Long productId, int cantidad, double costoU) {
		this.providerId=providerId;
		this.providerName=this.getProviderIdObject(providerId).getName();
		this.providerDocIde=this.getProviderIdObject(providerId).getDocIde();
		this.documento=documento;
		this.fecha = new Date();
		this.status= true;
		this.productId=productId;
		this.producName= this.getProductIdObject(productId).getName();
		this.productMedida=this.getProductIdObject(productId).getMedida();
		this.cantidad = cantidad;
		this.costoU = costoU;
		this.numRegistro=++contador;
		
	}

	public void actualizarRegistro(Long providerId, String documento, Long productId, int cantidad, double costoU){
		
		this.setProviderId(providerId);
		this.setProviderName(this.getProviderIdObject(providerId).getName());
		this.setProviderDocIde(this.getProviderIdObject(providerId).getDocIde());
		this.setDocumento(documento);
		this.setProductId(productId);
		this.setProducName(this.getProductIdObject(productId).getName());
		this.setProductMedida(this.getProductIdObject(productId).getMedida());
		this.setCantidad(cantidad);
		this.setCostoU(costoU);
		
	}
	public int getNumRegistro(){
		return this.numRegistro;
	}
	public Long getComprasid() {
		return comprasId;
	}
	public String getId() {
		return Long.toString(comprasId);
	}

	public void setId(String comprasId) {
		Long clave =Long.parseLong(comprasId);
		this.comprasId = clave;
	}

	public Provider getProviderIdObject(Long providerId){
		try{
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			Key k = KeyFactory.createKey(Provider.class.getSimpleName(), new Long(providerId).longValue()); //aqui
			   Provider a = pm.getObjectById(Provider.class, k); // no esta encotrando el rol eliminadoooooo
			   if(a==null)
					return null;
				else{
			return a;
			}
			}catch(Exception e){
				return null;
			}
	}
	
	
	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getProviderDocIde() {
		return providerDocIde;
	}

	public void setProviderDocIde(String providerDocIde) {
		this.providerDocIde = providerDocIde;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
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



	public double getCostoU() {
		return costoU;
	}



	public void setCostoU(double costoU) {
		this.costoU = costoU;
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
	
	public double costoTCompra(){
		return (double)cantidad*costoU;
	}
	
}