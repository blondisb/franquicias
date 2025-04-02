package main.java.com.franquiciasApi.franquicias.services;

import com.example.demo.model.ProductoModel;
import com.example.demo.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final IProductoRepository productoRepository;

    @Autowired
    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Consultar todos los productos
    public List<ProductoModel> getAllProductos() {
        return productoRepository.findAll();
    }

    // Agregar un nuevo producto
    public ProductoModel addProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    // Eliminar un producto por su ID
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Consultar un producto por su ID
    public Optional<ProductoModel> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    // Actualizar el nombre de un producto
    public ProductoModel updateProductoName(Long id, String nuevoNombre) {
        Optional<ProductoModel> optProducto = productoRepository.findById(id);
        if (optProducto.isPresent()) {
            ProductoModel producto = optProducto.get();
            producto.setNombre(nuevoNombre);
            return productoRepository.save(producto);
        }
        return null;
    }

    // Obtener el producto con mayor stock por sucursal para una franquicia específica
    public List<ProductoModel> getProductoConMayorStockPorFranquicia(Long franquiciaId) {
        return productoRepository.findProductoConMayorStockPorSucursal(franquiciaId);
    }
}
