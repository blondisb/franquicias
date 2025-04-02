package main.java.com.franquiciasApi.franquicias.services;

import com.example.demo.model.SucursalModel;
import com.example.demo.repository.ISucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    private final ISucursalRepository sucursalRepository;

    @Autowired
    public SucursalService(ISucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    // Consultar todas las sucursales
    public List<SucursalModel> getAllSucursales() {
        return sucursalRepository.findAll();
    }

    // Agregar una nueva sucursal
    public SucursalModel addSucursal(SucursalModel sucursal) {
        return sucursalRepository.save(sucursal);
    }

    // Eliminar una sucursal por su ID
    public void deleteSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }

    // Consultar una sucursal por ID
    public Optional<SucursalModel> getSucursalById(Long id) {
        return sucursalRepository.findById(id);
    }

    // Actualizar el nombre de una sucursal existente
    public SucursalModel updateSucursalName(Long id, String nuevoNombre) {
        Optional<SucursalModel> optSucursal = sucursalRepository.findById(id);
        if (optSucursal.isPresent()) {
            SucursalModel sucursal = optSucursal.get();
            sucursal.setNombre(nuevoNombre);
            return sucursalRepository.save(sucursal);
        }
        return null;
    }
}
