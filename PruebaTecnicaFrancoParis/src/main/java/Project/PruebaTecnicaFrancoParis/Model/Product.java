package Project.PruebaTecnicaFrancoParis.Model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Productos")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;
    @Column(name="UPCPLU",unique=true)
    @Nullable
    private Long UPCPLU ;
    private String Marca;
    private String Descripcion;
    private Integer Unidades;
    private Double Precio;

    public Product() {
    }

    public Product(Long idProducto, @Nullable Long UPCPLU, String marca, String descripcion, Integer unidades, Double precio) {
        this.idProducto = idProducto;
        this.UPCPLU = UPCPLU;
        Marca = marca;
        Descripcion = descripcion;
        Unidades = unidades;
        Precio = precio;
    }

    public Product(Long UPCPLU, String marca, String descripcion, Integer unidades, Double precio) {
        this.UPCPLU = UPCPLU;
        Marca = marca;
        Descripcion = descripcion;
        Unidades = unidades;
        Precio = precio;
    }

    public Long getUPCPLU() {
        return UPCPLU;
    }

    public void setUPCPLU(Long UPCPLU) {
        this.UPCPLU = UPCPLU;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Integer getUnidades() {
        return Unidades;
    }

    public void setUnidades(Integer unidades) {
        Unidades = unidades;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
}