package com.tesis.tiendavirtualbackend.controller;

import com.tesis.tiendavirtualbackend.bo.Productos;
import com.tesis.tiendavirtualbackend.dto.ProductosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.ProductosResponseDTO;
import com.tesis.tiendavirtualbackend.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/productos", produces = { "application/json" })
public class ProductosController {

    @Autowired
    private final ProductosService service;

    public ProductosController(ProductosService service) {
        this.service = service;
    }

    public ProductosService getService() {return service;}


    @GetMapping(path = "/getById")
    @ResponseBody
    public Productos getById(@RequestParam Long id){
        Productos obj = service.getById(id);
        return obj;
    }

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<Productos> getAll(){
        return service.getAll();
    }

    @PostMapping(path = "/getByPage")
    @ResponseBody
    public Page<Productos> getByPage(@RequestBody ProductosRequestDTO request) {
        return service.getByPage(request);
    }

    @PostMapping
    @ResponseBody
    public ProductosResponseDTO save(@RequestBody Productos obj){
        return service.save(obj, "agregado");
    }

    @PutMapping
    @ResponseBody
    public ProductosResponseDTO update(@RequestBody Productos obj){
        return service.save(obj, "editado");
    }

    @DeleteMapping
    @ResponseBody
    public ProductosResponseDTO delete(@RequestParam Long id){
        return service.delete(id);
    }

}
