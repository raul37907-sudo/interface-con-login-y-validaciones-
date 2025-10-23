package iguVentas;

public class Producto {
	
	private String nombre;
	private double precio;
	private int cantidad;
	
	
	//constructor
	
	public Producto () {
	}
	
	public Producto (String nombre, double precio,int cantidad ) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	
	public double getSubtotal() {
        return precio * cantidad;
    }

	
	// Geterrs
	public String getNombre()  { return nombre; }
	public double getPrecio()  { return precio; }
	public int    getCantidad(){ return cantidad;}
	
	//setters
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setPrecio(double precio) { this.precio = precio; }
	public void serCantidad (int cantidad) { this.cantidad = cantidad; }
	
    public String toString() {
        return nombre + " | $" + precio + " | x" + cantidad;
    }

}
 