package com.tesis.tiendavirtualbackend.controller;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.UsuariosRequestDTO;
import com.tesis.tiendavirtualbackend.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/usuarios", produces = { "application/json" })
public class UsuariosController {

    @Autowired
    private final UsuariosService service;

    public UsuariosController(UsuariosService service) {
        this.service = service;
    }

    public UsuariosService getService() {return service;}


    @GetMapping(path = "/getById")
    @ResponseBody
    public Usuarios getById(@RequestParam Long id){
        Usuarios obj = service.getById(id);
        return obj;
    }

    @GetMapping(path = "/getByUsuario")
    @ResponseBody
    public Usuarios getByUsuario(@RequestParam String usuario){
        Usuarios obj = service.getByUsuario(usuario);
        return obj;
    }

    @GetMapping(path = "/login")
    @ResponseBody
    public Usuarios login(@RequestParam String usuarioOCorreo, @RequestParam String contrasenia){
        Usuarios obj = service.getByUsuarioOrCorreoAndContrasenia(usuarioOCorreo, contrasenia);
        return obj;
    }

    @GetMapping(path = "/existeUsuarioPrincipal")
    @ResponseBody
    public Usuarios existeUsuarioPrincipal(){
        Usuarios obj = service.getByPrincipal();
        return obj;
    }

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<Usuarios> getAll(){
        return service.getAll();
    }

    //HABILITAR Y MODIFICAR SOLO SI HUBIERA UN MANTENIMIENTO DE USUARIOS
//    @PostMapping(path = "/getByPage")
//    @ResponseBody
//    public Page<Usuarios> getByPage(@RequestBody UsuariosRequestDTO request) {
//        return service.getByPage(request);
//    }

    @PostMapping
    @ResponseBody
    public Usuarios save(@RequestBody Usuarios obj){
        return service.save(obj);
    }

    @PutMapping
    @ResponseBody
    public Usuarios update(@RequestBody Usuarios obj){
        return service.save(obj);
    }

    @DeleteMapping
    @ResponseBody
    public void delete(@RequestParam Long id){
        service.delete(id);
    }

    @PostMapping(path = "/getValidadorUniques")
    @ResponseBody
    public String getValidadorUniques(@RequestBody UsuariosRequestDTO requestDTO){
        String response = service.getUniqueValidator(requestDTO);
        return response;
    }
}
