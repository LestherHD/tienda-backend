package com.tesis.tiendavirtualbackend.controller;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.SucursalesRequestDTO;
import com.tesis.tiendavirtualbackend.dto.UsuariosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.UsuariosResponseDTO;
import com.tesis.tiendavirtualbackend.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/usuarios", produces = { "application/json" })
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

    @PostMapping(path = "/getByUsuarioOrCorreo")
    @ResponseBody
    public UsuariosResponseDTO getByUsuarioOrCorreo(@RequestBody UsuariosRequestDTO request){
        UsuariosResponseDTO obj = service.getByUsuarioOrCorreo(request.getUsuario(), request.getCorreo());
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

    @PostMapping(path = "/getByPage")
    @ResponseBody
    public Page<Usuarios> getByPage(@RequestBody UsuariosRequestDTO request) {
        return service.getByPage(request);
    }

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<Usuarios> getAll(){
        return service.getAll();
    }

    @PostMapping
    @ResponseBody
    public UsuariosResponseDTO save(@RequestBody Usuarios obj){
        return service.save(obj, "registrado");
    }

    @PatchMapping
    @ResponseBody
    public UsuariosResponseDTO update(@RequestBody Usuarios obj){
        return service.save(obj, "editado");
    }

    @DeleteMapping
    @ResponseBody
    public UsuariosResponseDTO delete(@RequestParam Long id){
        return service.delete(id);
    }

    @PostMapping(path = "/recover-password")
    @ResponseBody
    public UsuariosResponseDTO recoverPassword(@RequestBody UsuariosRequestDTO request){
        UsuariosResponseDTO obj = service.generarCodigoCambiarContrasenia(request);
        return obj;
    }

    @PostMapping(path = "/principal-user")
    @ResponseBody
    public UsuariosResponseDTO principalUser(@RequestBody UsuariosRequestDTO request){
        UsuariosResponseDTO obj = service.generarCodigoConfirmarUsuarioPrincipal(request);
        return obj;
    }

    @PostMapping(path = "/update-user-password")
    @ResponseBody
    public UsuariosResponseDTO actualizarContraseña(@RequestBody UsuariosRequestDTO request){
        UsuariosResponseDTO obj = service.actualizarContrasenia(request);
        return obj;
    }

    @PostMapping(path = "/getByInfoUsuarioOrCorreo")
    @ResponseBody
    public UsuariosResponseDTO getByInfoUsuarioOrCorreo(@RequestBody UsuariosRequestDTO request){
        UsuariosResponseDTO obj = service.getByInfoUsuarioOrCorreo(request.getUsuario(), request.getCorreo());
        return obj;
    }


}
