package com.tesis.tiendavirtualbackend.controller;

import com.tesis.tiendavirtualbackend.bo.Caracteristicas;
import com.tesis.tiendavirtualbackend.dto.CaracteristicasRequestDTO;
import com.tesis.tiendavirtualbackend.dto.CaracteristicasResponseDTO;
import com.tesis.tiendavirtualbackend.service.CaracteristicasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/caracteristicas", produces = { "application/json" })
public class CaracteristicasController {

    @Autowired
    private final CaracteristicasService service;

    public CaracteristicasController(CaracteristicasService service) {
        this.service = service;
    }

    public CaracteristicasService getService() {return service;}


    @GetMapping(path = "/getById")
    @ResponseBody
    public Caracteristicas getById(@RequestParam Long id){
        Caracteristicas obj = service.getById(id);
        return obj;
    }

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<Caracteristicas> getAll(){
        return service.getAll();
    }

    @PostMapping(path = "/getByPage")
    @ResponseBody
    public Page<Caracteristicas> getByPage(@RequestBody CaracteristicasRequestDTO request) {
        return service.getByPage(request);
    }

    @PostMapping
    @ResponseBody
    public CaracteristicasResponseDTO save(@RequestBody Caracteristicas obj){
        return service.save(obj, "agregado");
    }

    @PutMapping
    @ResponseBody
    public CaracteristicasResponseDTO update(@RequestBody Caracteristicas obj){
        return service.save(obj, "editado");
    }

    @DeleteMapping
    @ResponseBody
    public CaracteristicasResponseDTO delete(@RequestParam Long id){
        return service.delete(id);
    }

}
