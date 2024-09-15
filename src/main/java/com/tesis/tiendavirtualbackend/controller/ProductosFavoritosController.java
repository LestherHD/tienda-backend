package com.tesis.tiendavirtualbackend.controller;

import com.tesis.tiendavirtualbackend.bo.ProductosFavoritos;
import com.tesis.tiendavirtualbackend.dto.ProductosFavoritosResponseDTO;
import com.tesis.tiendavirtualbackend.service.ProductosFavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/productosFavoritos", produces = { "application/json" })
public class ProductosFavoritosController {

    @Autowired
    private final ProductosFavoritosService service;

    public ProductosFavoritosController(ProductosFavoritosService service) {
        this.service = service;
    }

    public ProductosFavoritosService getService() {return service;}

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<ProductosFavoritos> getAll(){
        return service.getAll();
    }

    @PostMapping
    @ResponseBody
    public ProductosFavoritosResponseDTO save(@RequestBody ProductosFavoritos obj){
        return service.save(obj, "agregado");
    }

    @PutMapping
    @ResponseBody
    public ProductosFavoritosResponseDTO update(@RequestBody ProductosFavoritos obj){
        return service.save(obj, "editado");
    }

    @DeleteMapping
    @ResponseBody
    public ProductosFavoritosResponseDTO delete(@RequestParam Long id){
        return service.delete(id);
    }

}
