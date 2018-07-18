package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Index;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Mercaderia {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent @Index private String name;
	@Persistent private String medida;
	@Persistent private int cantidad;
	@Persistent private double costoU;
	
	public Mercaderia(String name, String medida) {
		this.name = name;
		this.medida = medida;
		this.cantidad = 0;
		this.costoU = 0.0;
	}
	public Mercaderia(String name, String medida, String cantidad, String costoU) {
		this.name = name;
		this.medida = medida;
		this.cantidad = Integer.parseInt(cantidad);
		this.costoU = Double.parseDouble(costoU);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void aumentarStock(double costoU, int cantidad){
	double nuevoCostoTotal =  CostoTotal() +(costoU*cantidad);
	this.setCantidad(this.cantidad+cantidad);
	if(this.cantidad==0)
		this.setCostoU(0.0);
	else
	this.setCostoU(nuevoCostoTotal/(double)this.cantidad);
	
	}
	public void disminuirStockVenta(int cantidad){
		if(this.cantidad>=cantidad){
			this.setCantidad(this.cantidad-cantidad);
		}
	}
	public void disminuirStockCompra(double costo, int cantidad){
		if(this.cantidad>=cantidad){
			double costoT=this.CostoTotal()-(costo*cantidad);
			this.setCantidad(this.cantidad-cantidad);
			
			if(this.cantidad==0)
				this.setCostoU(0.0);
			else
				this.setCostoU(costoT/this.cantidad);
		}
		
		
	}

	public double getCostoU() {
		return costoU;
	}
	
	public double CostoTotal(){
		return costoU*cantidad;
	}
	public void setCostoU(double costoU) {
		this.costoU = costoU;
	}
	
}