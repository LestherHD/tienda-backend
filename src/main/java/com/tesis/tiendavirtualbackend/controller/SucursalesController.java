package com.tesis.tiendavirtualbackend.controller;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import com.tesis.tiendavirtualbackend.dto.SucursalesRequestDTO;
import com.tesis.tiendavirtualbackend.dto.SucursalesResponseDTO;
import com.tesis.tiendavirtualbackend.service.SucursalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/sucursales", produces = { "application/json" })
public class SucursalesController {

    @Autowired
    private final SucursalesService service;

    public SucursalesController(SucursalesService service) {
        this.service = service;
    }

    public SucursalesService getService() {return service;}


    @GetMapping(path = "/getById")
    @ResponseBody
    public Sucursales getById(@RequestParam Long id){
        Sucursales obj = service.getById(id);
        return obj;
    }

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<Sucursales> getAll(){
        return service.getAll();
    }

    @PostMapping(path = "/getByPage")
    @ResponseBody
    public Page<Sucursales> getByPage(@RequestBody SucursalesRequestDTO request) {
        return service.getByPage(request);
    }

    @PostMapping
    @ResponseBody
    public SucursalesResponseDTO save(@RequestBody Sucursales obj){
        return service.save(obj, "agregado");
    }

    @PutMapping
    @ResponseBody
    public SucursalesResponseDTO update(@RequestBody Sucursales obj){
        return service.save(obj, "editado");
    }

    @DeleteMapping
    @ResponseBody
    public SucursalesResponseDTO delete(@RequestParam Long id){
        return service.delete(id);
    }

}
