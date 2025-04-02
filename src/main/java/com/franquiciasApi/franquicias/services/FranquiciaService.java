package com.franquiciasApi.franquicias.services;

import com.franquiciasApi.franquicias.models.FranquiciaModel;
import com.franquiciasApi.franquicias.repositories.IFranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranquiciaService {

    private final IFranquiciaRepository franquiciaRepository;

    @Autowired
    public FranquiciaService(IFranquiciaRepository franquiciaRepository) {
        this.franquiciaRepository = franquiciaRepository;
    }

    // Consultar todas las franquicias
    public List<FranquiciaModel> getAllFranquicias() {
        return franquiciaRepository.findAll();
    }

    // Agregar una nueva franquicia
    public FranquiciaModel addFranquicia(FranquiciaModel franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    // Eliminar una franquicia por su ID
    public void deleteFranquicia(Long id) {
        franquiciaRepository.deleteById(id);
    }

    // Consultar una franquicia por ID
    public Optional<FranquiciaModel> getFranquiciaById(Long id) {
        return franquiciaRepository.findById(id);
    }

    // Actualizar el nombre de una franquicia existente
    public FranquiciaModel updateFranquiciaName(Long id, String nuevoNombre) {
        Optional<FranquiciaModel> optFranquicia = franquiciaRepository.findById(id);
        if (optFranquicia.isPresent()) {
            FranquiciaModel franquicia = optFranquicia.get();
            franquicia.setNombre(nuevoNombre);
            return franquiciaRepository.save(franquicia);
        }
        return null;
    }
}
