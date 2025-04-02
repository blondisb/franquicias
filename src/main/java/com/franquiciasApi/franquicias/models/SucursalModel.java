package main.java.com.franquiciasApi.franquicias.models;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sucursales")
public class SucursalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "franquicia_id")
    private FranquiciaModel franquicia;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoModel> productos;

    // Constructor sin argumentos
    public SucursalModel() {
    }

    // Constructor con parámetros
    public SucursalModel(Long id, String nombre, FranquiciaModel franquicia, List<ProductoModel> productos) {
        this.id = id;
        this.nombre = nombre;
        this.franquicia = franquicia;
        this.productos = productos;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public FranquiciaModel getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(FranquiciaModel franquicia) {
        this.franquicia = franquicia;
    }

    public List<ProductoModel> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoModel> productos) {
        this.productos = productos;
    }
}
