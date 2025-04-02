package main.java.com.franquiciasApi.franquicias.controllers;

import com.example.demo.model.FranquiciaModel;
import com.example.demo.service.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    @Autowired
    public FranquiciaController(FranquiciaService franquiciaService) {
        this.franquiciaService = franquiciaService;
    }

    // Endpoint para consultar todas las franquicias
    @GetMapping
    public ResponseEntity<List<FranquiciaModel>> getAllFranquicias() {
        List<FranquiciaModel> franquicias = franquiciaService.getAllFranquicias();
        return ResponseEntity.ok(franquicias);
    }

    // Endpoint para agregar una nueva franquicia
    @PostMapping
    public ResponseEntity<FranquiciaModel> addFranquicia(@RequestBody FranquiciaModel franquicia) {
        FranquiciaModel nuevaFranquicia = franquiciaService.addFranquicia(franquicia);
        return ResponseEntity.ok(nuevaFranquicia);
    }

    // Endpoint para eliminar una franquicia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranquicia(@PathVariable Long id) {
        franquiciaService.deleteFranquicia(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para actualizar el nombre de una franquicia
    @PutMapping("/{id}")
    public ResponseEntity<FranquiciaModel> updateFranquiciaName(@PathVariable Long id, @RequestParam String nombre) {
        FranquiciaModel updated = franquiciaService.updateFranquiciaName(id, nombre);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}
