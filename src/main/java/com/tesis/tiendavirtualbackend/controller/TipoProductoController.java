package com.tesis.tiendavirtualbackend.controller;

import com.tesis.tiendavirtualbackend.bo.TipoProducto;
import com.tesis.tiendavirtualbackend.dto.TipoProductoRequestDTO;
import com.tesis.tiendavirtualbackend.dto.TipoProductoResponseDTO;
import com.tesis.tiendavirtualbackend.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/tipoProducto", produces = { "application/json" })
public class TipoProductoController {

    @Autowired
    private final TipoProductoService service;

    public TipoProductoController(TipoProductoService service) {
        this.service = service;
    }

    public TipoProductoService getService() {return service;}


    @GetMapping(path = "/getById")
    @ResponseBody
    public TipoProducto getById(@RequestParam Long id){
        TipoProducto obj = service.getById(id);
        return obj;
    }

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<TipoProducto> getAll(){
        return service.getAll();
    }

    @PostMapping(path = "/getByPage")
    @ResponseBody
    public Page<TipoProducto> getByPage(@RequestBody TipoProductoRequestDTO request) {
        return service.getByPage(request);
    }

    @PostMapping
    @ResponseBody
    public TipoProductoResponseDTO save(@RequestBody TipoProducto obj){
        return service.save(obj, "agregado");
    }

    @PutMapping
    @ResponseBody
    public TipoProductoResponseDTO update(@RequestBody TipoProducto obj){
        return service.save(obj, "editado");
    }

    @DeleteMapping
    @ResponseBody
    public TipoProductoResponseDTO delete(@RequestParam Long id){
        return service.delete(id);
    }

    @PostMapping(path = "/getValidadorUniques")
    @ResponseBody
    public String getValidadorUniques(@RequestBody TipoProductoRequestDTO requestDTO){
        String response = service.getUniqueValidator(requestDTO);
        return response;
    }
}
