package com.tesis.tiendavirtualbackend.controller;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.dto.PedidosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO;
import com.tesis.tiendavirtualbackend.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/pedidos", produces = { "application/json" })
public class PedidosController {

    @Autowired
    private final PedidosService service;

    public PedidosController(PedidosService service) {
        this.service = service;
    }

    public PedidosService getService() {return service;}


    @PostMapping(path = "/getById")
    @ResponseBody
    public Pedidos getById(@RequestBody PedidosRequestDTO request){
        Pedidos obj = service.getById(request.getPedidos().getId());
        return obj;
    }

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<Pedidos> getAll(){
        return service.getAll();
    }

    @PostMapping(path = "/getByPage")
    @ResponseBody
    public Page<Pedidos> getByPage(@RequestBody PedidosRequestDTO request) {
        return service.getByPage(request);
    }

    @PostMapping(path = "/getByFilter")
    @ResponseBody
    public Page<Pedidos> getByFilter(@RequestBody PedidosRequestDTO request) {
        return service.getByFilter(request);
    }

    @PostMapping(path = "/getInfoBranchSales")
    @ResponseBody
    List<PedidosResponseDTO> getInfoBranchSales(@RequestBody PedidosRequestDTO request){
        return service.getInfoBranchSales(request);
    }

    @PostMapping(path = "/getInfoMostSelledProducts")
    @ResponseBody
    List<PedidosResponseDTO> getInfoMostSelledProducts(@RequestBody PedidosRequestDTO request){
        return service.getInfoMostSelledProducts(request);
    }

    @PostMapping
    @ResponseBody
    public PedidosResponseDTO save(@RequestBody Pedidos obj){
        return service.save(obj, "A");
    }

    @PutMapping
    @ResponseBody
    public PedidosResponseDTO update(@RequestBody Pedidos obj){
        return service.save(obj, "E");
    }

    @DeleteMapping
    @ResponseBody
    public PedidosResponseDTO delete(@RequestParam Long id){
        return service.delete(id);
    }

}
