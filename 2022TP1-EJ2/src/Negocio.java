import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Negocio {
	
	private String nombre;
	private String cuit;
	private ArrayList<Factura> facturas; 
	private ArrayList<Articulo> articulos;
	private ArrayList<Cliente> clientes;
	
	public Negocio(String nombre, String cuit) {
		super();
		this.nombre = nombre;
		this.cuit = cuit;
		this.clientes = new ArrayList<Cliente>();
		this.facturas = new ArrayList<Factura>();
		this.articulos = new ArrayList<Articulo>();
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	public Factura agregarFactura (int nro, LocalDate fecha_venta, Articulo articulo, int cantidad_vendida) {
		Factura f = new Factura (nro, fecha_venta, articulo, cantidad_vendida);
		facturas.add(f);
		return f;
	
	}

	public Factura agregarFactura (int nro, LocalDate fecha_venta, Cliente cliente, Articulo articulo, int cantidad_vendida, boolean ctaCorriente) {
		Factura f = new Factura (nro, fecha_venta, cliente, articulo, cantidad_vendida, ctaCorriente);
		facturas.add(f);
		return f;
	
	}
	
	public Articulo agregarArticulo (int codigo, String descripcion, double precio, int cantidad) {
		Articulo a = new Articulo (codigo, descripcion, precio, cantidad);
		articulos.add(a);
		return a;
			
	}
	
	public Cliente agregarCliente (int codigo, String nombre, String cuit, String telefono, String direccion) {
		Cliente c = new Cliente (codigo, nombre, cuit, telefono, direccion); 
		clientes.add(c);
		return c;
		
	}
	
	public double totalFacturado (LocalDate fecha1, LocalDate fecha2) {
		double total = 0.0;
		for (Factura f : facturas) {
			if (((f.getFecha_venta().isAfter(fecha1)) || (f.getFecha_venta().equals(fecha1))) && ((f.getFecha_venta().isBefore(fecha2))) || (f.getFecha_venta().equals(fecha2))) {
				total = total + f.importeTotal(); 	
			}
		}
		return total;
		
	}
	
	public double totalFacturadoCtaCte (LocalDate fecha1, LocalDate fecha2) {
		double total = 0.0;
		for (Factura f : facturas) {
			if (((f.getFecha_venta().isAfter(fecha1)) || (f.getFecha_venta().equals(fecha1))) && ((f.getFecha_venta().isBefore(fecha2))) || (f.getFecha_venta().equals(fecha2))) {
				if (f.isCtaCorriente() && f.getCliente() != null) {
				total = total + f.importeTotal(); 	
				}
			}
		}
		return total;
	}

	public double totalFacturadoCliente (LocalDate fecha1, LocalDate fecha2, Cliente cliente){ 
		double total = 0.0;
		for (Factura f: facturas ) {
			if (((f.getFecha_venta().isAfter(fecha1)) || (f.getFecha_venta().equals(fecha1))) && ((f.getFecha_venta().isBefore(fecha2))) || (f.getFecha_venta().equals(fecha2))) {
				if (f.getCliente() == cliente) {
				total = total + f.importeTotal(); 	
				}
			}
		}
		return total;
		
	}
	
	public double totalFacturadoClienteCtaCte (LocalDate fecha1, LocalDate fecha2, Cliente cliente) {
		double total = 0.0;
		for (Factura f: facturas ) {
			if (((f.getFecha_venta().isAfter(fecha1)) || (f.getFecha_venta().equals(fecha1))) && ((f.getFecha_venta().isBefore(fecha2))) || (f.getFecha_venta().equals(fecha2))) {
				if (f.getCliente()!= null &&  f.getCliente().equals(cliente) && f.isCtaCorriente()) {
				total = total + f.importeTotal(); 	
				}
			}
		}
		return total;
		
	}
	
	public double stockValorizado () {
		double valorTotal = 0.0;
		for (Articulo a : articulos) {
			valorTotal = valorTotal + a.getCantidad()*a.getPrecio();
		}
		return valorTotal;
		
	}
	
	public void cambiarPrecio (int porcentaje) {
		double aumento = 0;
		for (Articulo a: articulos) {
			aumento = (a.getPrecio() * porcentaje) / 100;
			a.setPrecio(a.getPrecio() + aumento);
		}
	}

	@Override
	public String toString() {
		return "Negocio [nombre=" + nombre + ", cuit=" + cuit + ", facturas=" + facturas + ", articulos=" + articulos
				+ "]";
	}

	
	


	
	
	
	
	

}
